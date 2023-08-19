package it.bz.prov.controlli.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.bz.prov.controlli.bo.AppSettingsBO;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAppSettingsService;


@Component
public class JWTTokenUtils implements Serializable {

    private Logger _logger = Utils.getLogger();

    private static final long serialVersionUID = -2550185165626007488L;

    public static long JWT_TOKEN_VALIDITY;
    private static String secret;

    @Autowired
    private IAppSettingsService _appSettingsService;

    @PostConstruct
    private void postConstruct(){
        try{
            AppSettingsBO token_validity = _appSettingsService.findSetting(Constants.JWT_MINUTES_VALIDITY);
            AppSettingsBO secret = _appSettingsService.findSetting(Constants.JWT_SECRET);
            JWTTokenUtils.JWT_TOKEN_VALIDITY = Long.parseLong(token_validity.get_value());
            JWTTokenUtils.secret = secret.get_value();

        } catch(ServiceException se){
            _logger.error(this.getClass().getSimpleName() + " - postConstruct: " + "Errore nel reperimento parametri.");
            JWTTokenUtils.JWT_TOKEN_VALIDITY = 0;
            JWTTokenUtils.secret = "";
        }
    }

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, this::getUsername);

    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private String getUsername(Claims claims){
        if(claims.containsKey("username")){
            return claims.get("username").toString();
        } else return null;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        try{
            return Jwts.parser().setSigningKey(JWTTokenUtils.secret).parseClaimsJws(token).getBody();
        } catch(Exception e){
            _logger.error(this.getClass().getSimpleName() +" - getAllClaimsFromToken: " + "Errore nel parsing. Token malformato?" );
            return Jwts.claims(); 
        }
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(UtenteBO user) {
        return doGenerateToken(user.toMap());
    }

    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWTTokenUtils.JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, JWTTokenUtils.secret).compact();
    }

    // validate token
    public Boolean validateToken(String token, String username) {
        final String usernameToken = getUsernameFromToken(token);
        return (usernameToken.equals(username) && !isTokenExpired(token));
    }
}