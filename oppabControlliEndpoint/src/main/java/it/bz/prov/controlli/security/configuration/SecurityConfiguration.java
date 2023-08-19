package it.bz.prov.controlli.security.configuration;

import java.util.Collections;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.bz.prov.controlli.bo.AppSettingsBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAppSettingsService;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.security.authentication.JWTFilter;
import it.bz.prov.controlli.security.authentication.OppabAuthenticationEntryPoint;
import it.bz.prov.controlli.security.authentication.OppabLdapAuthenticationProvider;
import it.bz.prov.controlli.security.authentication.OppabLdapFilterUserSearch;
import it.bz.prov.controlli.security.authorization.OppabLdapAuthoritiesPopulator;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;

/**
 * Classe per la configurazione del motore di security e della security sugli endpoints.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger _oppabLogger = Utils.getLogger();

    @Autowired
    private IAppSettingsService _appService;

    @Autowired
    private IGestioneUtentiService _utentiService;

    @Autowired
    private OppabLdapAuthenticationProvider _authProvider;
    
    
    @Autowired
    private JWTFilter _jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .headers()
                .and()
                    .csrf()
                        .disable()
                    .authorizeRequests()
                        .antMatchers("/authentication/login").permitAll()
                .and()
                    .addFilterBefore(_jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                        .authenticationEntryPoint(getAuthenticationEntryPoint())
                .and()
                    .cors()
                ;
    }

    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint(){
        return new OppabAuthenticationEntryPoint();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DefaultSpringSecurityContextSource contextSource = contextSource();
        contextSource.afterPropertiesSet();
        BindAuthenticator ba = new BindAuthenticator(contextSource);
        ba.setUserSearch(getOppabLdapFilterUserSearch(contextSource));
        _authProvider.setAuthenticator(ba);
        _authProvider.setAuthoritiesPopulator(getOppabLdapAuthoritiesPopulator(contextSource));
        auth.authenticationProvider(_authProvider);
    }

    private DefaultSpringSecurityContextSource contextSource() {
        try{
            AppSettingsBO ldapContextSource = _appService.findSetting(Constants.LDAP_SERVER_URL);
            AppSettingsBO ldapBaseDn = _appService.findSetting(Constants.LDAP_SERVER_BASEDN);
            return new DefaultSpringSecurityContextSource(Collections.singletonList(ldapContextSource.get_value()), ldapBaseDn.get_value());
        } catch(ServiceException se){
            _oppabLogger.error(SecurityConfiguration.class.getSimpleName() + " - contextSource: " + se.getMessage());
            return null;
        }
    }

    private LdapUserSearch getOppabLdapFilterUserSearch(DefaultSpringSecurityContextSource contextSource){
        try{
            AppSettingsBO ldapSearchScope = _appService.findSetting(Constants.LDAP_SEARCH_SCOPE);
            AppSettingsBO ldapFilter = _appService.findSetting(Constants.LDAP_FILTER);
            AppSettingsBO ldapDomain = _appService.findSetting(Constants.LDAP_DOMAIN);
            OppabLdapFilterUserSearch userSearch = new OppabLdapFilterUserSearch(ldapSearchScope.get_value(), ldapFilter.get_value(), ldapDomain.get_value(), contextSource);
            userSearch.setSearchSubtree(true);
            return userSearch;
        } catch(ServiceException se){
            _oppabLogger.error(SecurityConfiguration.class.getSimpleName() + " - getOppabLdapFilterUserSearch: " + se.getMessage());
            return null;
        }
    }

    private LdapAuthoritiesPopulator getOppabLdapAuthoritiesPopulator(DefaultSpringSecurityContextSource contextSource){
        OppabLdapAuthoritiesPopulator authoritiesPopulator = new OppabLdapAuthoritiesPopulator(contextSource, "o=jobs,ou=People");
        authoritiesPopulator.set_utentiService(_utentiService);
        authoritiesPopulator.setGroupRoleAttribute("sn");
        authoritiesPopulator.setGroupSearchFilter("uid={0}");
        authoritiesPopulator.setSearchSubtree(false);
        return authoritiesPopulator;
    }
}