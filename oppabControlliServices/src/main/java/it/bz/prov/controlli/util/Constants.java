package it.bz.prov.controlli.util;

import java.util.Calendar;
import java.util.Date;

public class Constants {
	
	private static final Calendar _cal = Calendar.getInstance();
	static {
		_cal.set(Calendar.YEAR, 9999);
		_cal.set(Calendar.MONTH, Calendar.DECEMBER);
		_cal.set(Calendar.DAY_OF_MONTH, 12);
	}
	
	public static final String LDAP_DOMAIN = "LDAP_DOMAIN";
	public static final String LDAP_SERVER_URL = "LDAP_SERVER_URL";	
	public static final String LDAP_SEARCH_SCOPE = "LDAP_SEARCH_SCOPE";
	public static final String LDAP_FILTER = "LDAP_FILTER";	
	public static final String LDAP_COMPLETE_NAME = "LDAP_COMPLETE_NAME";
	public static final String LDAP_SERVER_BASEDN = "LDAP_SERVER_BASEDN";
	public static final String JWT_MINUTES_VALIDITY = "JWT_MINUTES_VALIDITY";
	public static final String JWT_SECRET = "JWT_SECRET";
	public static final Date LAST_DATE = _cal.getTime();
	public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	public static final int LAST_YEAR = _cal.get(Calendar.YEAR);

	//lingua
	public static final String LinguaIT = "IT";
	public static final String LinguaDE = "DE";
		
}
