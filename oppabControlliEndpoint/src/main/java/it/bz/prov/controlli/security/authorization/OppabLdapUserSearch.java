package it.bz.prov.controlli.security.authorization;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAppSettingsService;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;

public class OppabLdapUserSearch {

	private static String _contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	private static String _ldapServerUrl = "";
	private static String _principal = "";
	private static String _credential = "";
	private static String _searchScope = "";
	private static String _filter = "";
	private static String _domain = "";
	private static String _completeName = "";

	@Autowired
	private static IAppSettingsService _appServiceSettings;
	
	private static final Logger oppabLogger = Utils.getLogger();
	/**
	 * ricerca dell'utente
	 * @param username
	 * @return String
	 */
	public static String searchForUser(String username){
		
		readParameters();
		DirContext ctx = null;

		Hashtable<String, String>env = new Hashtable<String, String>();
		env.put("java.naming.ldap.version", "3");
		env.put(Context.REFERRAL, "follow");
		env.put(Context.INITIAL_CONTEXT_FACTORY, _contextFactory);
		env.put(Context.PROVIDER_URL, _ldapServerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, authenticationType());
		env.put(Context.SECURITY_PRINCIPAL, _principal);
		env.put(Context.SECURITY_CREDENTIALS, _credential);
		
		Attributes attributes = null;
		String[] sAttributesToRetrieve = searchAttributes();
		SearchControls ctls = null;
		SearchResult sr = null;
		NamingEnumeration<SearchResult> ne = null;
		String completeName = null;
		try{
			ctx = new InitialDirContext(env);
			if (sAttributesToRetrieve.length > 0) {
				ctls = new SearchControls();
				ctls.setReturningAttributes(sAttributesToRetrieve);
				ctls.setSearchScope(searchScope());
				String filter = "(" + _filter + "=" + username + ")";
				ne = ctx.search(_searchScope, filter, ctls);

				sr = (SearchResult) ne.next();
				attributes = sr.getAttributes();
			}

			NamingEnumeration<? extends Attribute>nn = attributes.getAll();
			Attribute attribute = null;
			while(nn.hasMoreElements()){
				attribute = (Attribute)nn.next();
				if(attribute.getID().equals(_completeName)){ // name
					completeName = (String)attribute.get();
				}
			}
		}
		catch (Throwable e) {
			return null;
		}

		return completeName;
	}

	/**
	 * Tipo di autenticazione (default: simple)
	 */
	private static String authenticationType() {
		return "simple";
	}

	/**
	 * attributi di ricerca
	 * @return String[] 
	 */
	private static String[] searchAttributes() {
		String[] a = new String[1];
		a[0] = _completeName;
		return a;
	}

	/**
	 * scope di ricerca
	 * @return int
	 */
	private static int searchScope() {
		return SearchControls.SUBTREE_SCOPE;
	}
	
	/**
	 * lettura dei parametri dal file stat.properties per la connessione a ldap
	 */
	private static void readParameters(){
		try
		{			    
		    _ldapServerUrl = _appServiceSettings.findSetting(Constants.LDAP_SERVER_URL).get_value() + _appServiceSettings.findSetting(Constants.LDAP_SERVER_BASEDN).get_value();
		    _domain = _appServiceSettings.findSetting(Constants.LDAP_DOMAIN).get_value();
		    _searchScope = _appServiceSettings.findSetting(Constants.LDAP_SEARCH_SCOPE).get_value();
		    _filter = _appServiceSettings.findSetting(Constants.LDAP_FILTER).get_value();
		    _completeName = _appServiceSettings.findSetting(Constants.LDAP_COMPLETE_NAME).get_value();
		    
			_principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			_credential = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getCredentials());

			if(!_principal.contains("\\"))
			_principal = _domain + "\\" + _principal;

		}
		catch(ServiceException e){
			oppabLogger.error(OppabLdapUserSearch.class.getSimpleName() + " - readParameters: " + "Lettura parametri fallita");
		}
	}
}
