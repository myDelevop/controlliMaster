package it.bz.prov.controlli.bo;

import java.io.Serializable;

import it.bz.prov.controlli.entities.AppSettings;;


/**
 * Oggetto BO che rappresenta l'azienda
 * @author bpettazzoni
 *
 */
public class AppSettingsBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String _key;	
	private String _value;
	
	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/
	
	/**
	 * Costruttore senza parametri
	 */
	public AppSettingsBO() {
		_key = "";
		_value = "";
	}
	
	/**
	 * Costruttore con parametri
	 * @param appSettings é l'oggetto entity
	 */
	public AppSettingsBO(AppSettings appSettings) {
		_key = appSettings.get_key();
		_value = appSettings.get_value();
	}
	
	
	/***************************************************************/
	/*						GETTER E SETTER			   			   */
	/***************************************************************/

	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}

	public String get_value() {
		return _value;
	}

	public void set_value(String _value) {
		this._value = _value;
	}
}
