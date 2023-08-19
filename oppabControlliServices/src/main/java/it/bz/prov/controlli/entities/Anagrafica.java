package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Anagrafica
 *
 */
@Entity
@Table(name = "APP_ANAGRAFICA")
public class Anagrafica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_ANAGRAFICA")
	private Integer _idAnagrafica;
	
	@Column(name = "CHIAVE")
	private String _chiave;
	
	@Column(name = "VALORE_IT")
	private String _valoreIT;
	
	@Column(name = "VALORE_DE")
	private String _valoreDE;
	
	@Column(name = "ANNO_VALIDITA_INIZIO")
	private Integer _annoValiditaInizio;
	
	@Column(name = "ANNO_VALIDITA_FINE")
	private Integer _annoValiditaFine;
		
	@Column(name = "DATA_CREAZIONE")
	private Timestamp _dataCreazione;
	
	@Column(name = "USER_CREAZIONE")
	private String _userCreazione;
	
	@Column(name = "DATA_MODIFICA")
	private Timestamp _dataModifica;
	
	@Column(name = "USER_MODIFICA")
	private String _userModifica;
	
	@Column(name = "DATA_CANCELLAZIONE")
	private Timestamp _dataCancellazione;
	
	@Column(name = "USER_CANCELLAZIONE")
	private String _userCancellazione;
	
	@Column(name = "FLAG_CANCELLATO")
	private Integer _flagCancellato;
	
	@Column(name = "FLAG_VALIDO")
	private Integer _flagValido;

	
	/**************************************************/
	/*				COSTRUTTORE						  */
	/**************************************************/
	
	/**
	 * costruttore
	 */
	public Anagrafica() {
		_id=0L;
		_idAnagrafica=0;
		_chiave="";
		_valoreIT="";
		_valoreDE="";
		_annoValiditaInizio=0;
		_annoValiditaFine=0;
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
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

	public Integer get_idAnagrafica() {
		return _idAnagrafica;
	}

	public void set_idAnagrafica(Integer _idAnagrafica) {
		this._idAnagrafica = _idAnagrafica;
	}

	public String get_chiave() {
		return _chiave;
	}

	public void set_chiave(String _chiave) {
		this._chiave = _chiave;
	}

	public String get_valoreIT() {
		return _valoreIT;
	}

	public void set_valoreIT(String _valoreIT) {
		this._valoreIT = _valoreIT;
	}

	public String get_valoreDE() {
		return _valoreDE;
	}

	public void set_valoreDE(String _valoreDE) {
		this._valoreDE = _valoreDE;
	}

	public Integer get_annoValiditaInizio() {
		return _annoValiditaInizio;
	}

	public void set_annoValiditaInizio(Integer _annoValiditaInizio) {
		this._annoValiditaInizio = _annoValiditaInizio;
	}

	public Integer get_annoValiditaFine() {
		return _annoValiditaFine;
	}

	public void set_annoValiditaFine(Integer _annoValiditaFine) {
		this._annoValiditaFine = _annoValiditaFine;
	}

	public Timestamp get_dataCreazione() {
		return _dataCreazione;
	}

	public void set_dataCreazione(Timestamp _dataCreazione) {
		this._dataCreazione = _dataCreazione;
	}

	public String get_userCreazione() {
		return _userCreazione;
	}

	public void set_userCreazione(String _userCreazione) {
		this._userCreazione = _userCreazione;
	}

	public Timestamp get_dataModifica() {
		return _dataModifica;
	}

	public void set_dataModifica(Timestamp _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public String get_userModifica() {
		return _userModifica;
	}

	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	public Timestamp get_dataCancellazione() {
		return _dataCancellazione;
	}

	public void set_dataCancellazione(Timestamp _dataCancellazione) {
		this._dataCancellazione = _dataCancellazione;
	}

	public String get_userCancellazione() {
		return _userCancellazione;
	}

	public void set_userCancellazione(String _userCancellazione) {
		this._userCancellazione = _userCancellazione;
	}

	public Integer get_flagCancellato() {
		return _flagCancellato;
	}

	public void set_flagCancellato(Integer _flagCancellato) {
		this._flagCancellato = _flagCancellato;
	}

	public Integer get_flagValido() {
		return _flagValido;
	}

	public void set_flagValido(Integer _flagValido) {
		this._flagValido = _flagValido;
	}

}
