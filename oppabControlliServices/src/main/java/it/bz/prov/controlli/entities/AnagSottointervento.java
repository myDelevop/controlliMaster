package it.bz.prov.controlli.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: AnagSottointervento
 *
 */
@Entity
@Table(name = "ANAG_SOTTOINTERVENTO")
public class AnagSottointervento implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "MISURA")
	private String _misura;

	@Column(name = "INTERVENTO")
	private String _intervento;

	@Column(name = "SOTTOINTERVENTO")
	private String _sottointervento;
	
	@Column(name="FLAG_VALIDO")
	private Integer _flagValido;
	
	
	/**************************************************/
	/*				COSTRUTTORE						  */
	/**************************************************/
	
	/**
	 * costruttore
	 */
	public AnagSottointervento() {
		_id = 0L;
		_misura = "";
		_intervento = "";
		_sottointervento = "";
		_flagValido = 0;
	}
	
	/**************************************************/
	/*				GETTER E SETTER					  */
	/**************************************************/
	
	
	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_misura() {
		return _misura;
	}

	public void set_misura(String _misura) {
		this._misura = _misura;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_sottointervento() {
		return _sottointervento;
	}

	public void set_sottointervento(String _sottointervento) {
		this._sottointervento = _sottointervento;
	}

	public Integer get_flagValido() {
		return _flagValido;
	}

	public void set_flagValido(Integer _flagValido) {
		this._flagValido = _flagValido;
	}

}
