package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_LOG")
@NamedQuery(name="SysLog.findAll", query="SELECT s FROM SysLog s")
public class SysLog implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Long _id;

	@Column(name="UTENTE")
	private String _utente;

	@Column(name="DATA")
	private Timestamp _data;

	@Column(name="TIPO_OPERAZIONE")
	private String _tipoOperazione;

	@Column(name="DESCRIZIONE")
	private String _descrizione;
	
	

	/**************************************************/
	/*				COSTRUTTORE						  */
	/**************************************************/

	public SysLog() {
		_id = 0L;
		_utente = "";
		_tipoOperazione = "";
		_descrizione = "";
		_data = new Timestamp(System.currentTimeMillis());

	}
	
	/**
	 * creazione di una riga di log
	 * @param utente
	 * @param tipoOperazione
	 */
	public SysLog(String utente, String tipoOperazione) {
		_id = 0L;
		_utente = utente;
		_tipoOperazione = tipoOperazione;
		_descrizione = "";
		_data = new Timestamp(System.currentTimeMillis());
	}
	
	
	/**
	 * creazione di una riga di log
	 * @param utente
	 * @param tipoOperazione
	 * @param descrizione
	 */
	public SysLog(String utente, String tipoOperazione, String descrizione) {
		_id = 0L;
		_utente = utente;
		_tipoOperazione = tipoOperazione;
		_descrizione = descrizione;
		_data = new Timestamp(System.currentTimeMillis());
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


	public String get_utente() {
		return _utente;
	}


	public void set_utente(String _utente) {
		this._utente = _utente;
	}


	public Timestamp get_data() {
		return _data;
	}


	public void set_data(Timestamp _data) {
		this._data = _data;
	}


	public String get_tipoOperazione() {
		return _tipoOperazione;
	}


	public void set_tipoOperazione(String _tipoOperazione) {
		this._tipoOperazione = _tipoOperazione;
	}


	public String get_descrizione() {
		return _descrizione;
	}


	public void set_descrizione(String _descrizione) {
		this._descrizione = _descrizione;
	}
	
	
	
}
