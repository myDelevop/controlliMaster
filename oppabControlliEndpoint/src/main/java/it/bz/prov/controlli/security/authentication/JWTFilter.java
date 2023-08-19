package it.bz.prov.controlli.security.authentication;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.util.JWTTokenUtils;
import it.bz.prov.controlli.util.Utils;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private Logger _logger = Utils.getLogger();

    @Autowired
    private JWTTokenUtils _jwtTokenUtils;

    @Autowired
    private IGestioneUtentiService _gestioneUtentiService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = _jwtTokenUtils.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                _logger.error(this.getClass().getSimpleName() + " - doFilterInternal: " + "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                _logger.error(this.getClass().getSimpleName() + " - doFilterInternal: " + "JWT Token has expired");
            }
        } else {
            _logger.warn(this.getClass().getSimpleName() + " - doFilterInternal: " + "JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        UtenteBO utente = null;
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try{
                utente = _gestioneUtentiService.findUtenteByUsername(username);
            } catch(ServiceException se){
                _logger.error(this.getClass().getSimpleName() + " - doFilterInternal: " + "Errore nel reperimento utente a DB");
            }
            if (_jwtTokenUtils.validateToken(jwtToken, utente.get_username())) {
                Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
                for (String ga : utente.getRoleList()) authorities.add(new SimpleGrantedAuthority(ga));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    utente.get_username(), null, authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}