package it.bz.prov.controlli.utility;

import org.springframework.security.core.context.SecurityContextHolder;

public class Utility {
	
	/**
	 * retituisce l'utente autenticato
	 * @return String é l'utente autenticato
	 */
	public static String getAuthenticatedUser() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
