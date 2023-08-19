package it.bz.prov.controlli.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AppSettings
 *
 */
@Entity
@Table(name = "APP_SETTINGS")
@NamedQuery(name="AppSettings.findAll", query="SELECT a FROM AppSettings a")
public class AppSettings implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "KEY")
	private String _key;

	@Column(name = "VALUE")
	private String _value;
	
	/**************************************************/
	/*				COSTRUTTORE						  */
	/**************************************************/
	
	/**
	 * costruttore
	 */
	public AppSettings() {
		_key="";
		_value="";
	}
	
	/**************************************************/
	/*				GETTER E SETTER					  */
	/**************************************************/
	

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
