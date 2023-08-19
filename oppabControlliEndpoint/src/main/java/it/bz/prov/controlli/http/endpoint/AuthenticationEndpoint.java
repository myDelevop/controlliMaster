package it.bz.prov.controlli.http.endpoint;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.security.authentication.OppabLdapAuthenticationProvider;
import it.bz.prov.controlli.util.JWTTokenUtils;
import it.bz.prov.controlli.util.Utils;

@RestController
@RequestMapping({"/authentication"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class AuthenticationEndpoint {

	private Logger _logger = Utils.getLogger();
    
    @Autowired
    private IGestioneUtentiService _gestioneUtentiService;

    @Autowired
    private OppabLdapAuthenticationProvider _authProvider;

    @Autowired
    private JWTTokenUtils _jwtTokenUtils;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getToken(HttpServletRequest request) {
        String username = "";
        String password = "";
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);
            username = values[0];
            password = values[1];
        }
        UsernamePasswordAuthenticationToken simpleToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            Authentication auth = _authProvider.authenticate(simpleToken);
            LdapUserDetailsImpl userDetails = (LdapUserDetailsImpl) auth.getPrincipal();
            UtenteBO user = _gestioneUtentiService.findUtenteByUsername(userDetails.getUsername());
            return new ResponseEntity<String>("{\"jwt\":\"" + 
            		_jwtTokenUtils.generateToken(user) + "\"}", HttpStatus.OK);
            } catch(AuthenticationServiceException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch(ServiceException se){
            _logger.error(this.getClass().getSimpleName() + " - getToken: " + "Errore nel reperimento utente.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/regenerateToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> regenerateToken(HttpServletRequest request) { 
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = _jwtTokenUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                _logger.error(this.getClass().getSimpleName() + " - regenerateToken: " + "Unable to get JWT Token");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } catch (ExpiredJwtException e) {
                _logger.error(this.getClass().getSimpleName() + " - regenerateToken: " + "JWT Token has expired");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            _logger.warn(this.getClass().getSimpleName() + " - regenerateToken: " + "JWT Token does not begin with Bearer String");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UtenteBO user = null;
        if (username != null) {
            try{
                user = _gestioneUtentiService.findUtenteByUsername(username);
                if (_jwtTokenUtils.validateToken(jwtToken, user.get_username())) {
                    return new ResponseEntity<String>("{\"jwt\":\"" + _jwtTokenUtils.generateToken(user) + "\"}", HttpStatus.OK);
                } else {
                    _logger.warn(this.getClass().getSimpleName() + " - regenerateToken: " + "JWT Token is not valid.");
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } catch(ServiceException se){
                _logger.error(this.getClass().getSimpleName() + " - regenerateToken: " + "Errore nel reperimento utente a DB");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            _logger.warn(this.getClass().getSimpleName() + " - regenerateToken: " + "JWT Token does not contains username field.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
