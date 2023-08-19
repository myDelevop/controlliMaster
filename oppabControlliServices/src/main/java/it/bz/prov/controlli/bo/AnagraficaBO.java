package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Anagrafica;

/**
 * Oggetto BO che rappresenta l'entità di gestione delle anagrafiche
 * @author bpettazzoni
 *
 */
public class AnagraficaBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idAnagrafica")
	private Integer _idAnagrafica;
	
	@JsonProperty("chiave")
	private String _chiave;
	
	@JsonProperty("valoreIT")
	private String _valoreIT;
	
	@JsonProperty("valoreDE")
	private String _valoreDE;
	
	@JsonProperty("annoValiditaInizio")
	private Integer _annoValiditaInizio;
	
	@JsonProperty("annoValiditaFine")
	private Integer _annoValiditaFine;

	@JsonProperty("dataCreazione")
	private Timestamp _dataCreazione;
	
	@JsonProperty("userCreazione")
	private String _userCreazione;
	
	@JsonProperty("dataModifica")
	private Timestamp _dataModifica;
	
	@JsonProperty("userModifica")
	private String _userModifica;
	
	@JsonProperty("dataCancellazione")
	private Timestamp _dataCancellazione;
	
	@JsonProperty("userCancellazione")
	private String _userCancellazione;
	
	@JsonProperty("flagCancellato")
	private Integer _flagCancellato;
	
	@JsonProperty("flagValido")
	private Integer _flagValido;

	
	/**************************************************/
	/*				COSTRUTTORI						  */
	/**************************************************/
	
	/**
	 * Costruttore
	 */
	public AnagraficaBO() {
		_chiave = "";
		_valoreIT = "";
		_valoreDE = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	/**
	 * Costruttore con parametri
	 * @param anagrafica
	 */
	public AnagraficaBO(Anagrafica anagrafica) {
		_id = anagrafica.get_id();
		_idAnagrafica = anagrafica.get_idAnagrafica();
		_chiave = anagrafica.get_chiave();
		_valoreIT = anagrafica.get_valoreIT();
		_valoreDE = anagrafica.get_valoreDE();
		_annoValiditaInizio = anagrafica.get_annoValiditaInizio();
		_annoValiditaFine = anagrafica.get_annoValiditaFine();
		_dataCreazione = anagrafica.get_dataCreazione();
		_userCreazione = anagrafica.get_userCreazione();
		_dataModifica = anagrafica.get_dataModifica();
		_userModifica = anagrafica.get_userModifica();
		_dataCancellazione = anagrafica.get_dataCancellazione();
		_userCancellazione = anagrafica.get_userCancellazione();
		_flagCancellato = anagrafica.get_flagCancellato();
		_flagValido = anagrafica.get_flagValido();
	}
	
	
	/**************************************************/
	/*					UTILITY					  	  */
	/**************************************************/
	
	public Anagrafica convertToEntity() {
		Anagrafica entity = new Anagrafica();
		entity.set_id(_id);
		entity.set_idAnagrafica(_idAnagrafica);
		entity.set_chiave(_chiave);
		entity.set_valoreIT(_valoreIT);
		entity.set_valoreDE(_valoreDE);
		entity.set_annoValiditaInizio(_annoValiditaInizio);
		entity.set_annoValiditaFine(_annoValiditaFine);		
		entity.set_dataCreazione(_dataCreazione);
		entity.set_userCreazione(_userCreazione);
		entity.set_dataModifica(_dataModifica);
		entity.set_userModifica(_userModifica);
		entity.set_dataCancellazione(_dataCancellazione);
		entity.set_userCancellazione(_userCancellazione);
		entity.set_flagCancellato(_flagCancellato);
		entity.set_flagValido(_flagValido);
		return entity;		
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
