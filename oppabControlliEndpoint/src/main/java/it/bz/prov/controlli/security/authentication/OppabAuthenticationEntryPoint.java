package it.bz.prov.controlli.security.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public final class OppabAuthenticationEntryPoint implements AuthenticationEntryPoint {
 
    // private Logger _logger = Utils.getLogger();

    /**
     * Metodo richiamto in caso di fallita autenticazione. Ritorna HTTP status cod 401.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}