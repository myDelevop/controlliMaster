package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Controllore;


/**
 * Oggetto BO che rappresenta il controllore incaricato di eseguire il controllo
 * @author bpettazzoni
 *
 */
public class ControlloreBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idControllore")
	private Long _idControllore;
	
	@JsonProperty("username")
	private String _username;
	
	@JsonProperty("nome")
	private String _nome;
	
	@JsonProperty("cognome")
	private String _cognome;
	
	@JsonProperty("annoValiditaInizio")
	private Integer _annoValiditaInizio;
	
	@JsonProperty("annoValiditaFine")
	private Integer _annoValiditaFine;
	
	@JsonProperty("note")
	private String _note;

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
	

	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/
	
	/**
	 * Costruttore
	 */
	public ControlloreBO() {
		_username= "";
		_nome = "";
		_cognome = "";
		_annoValiditaInizio = 0;
		_annoValiditaFine = 0;
		_note="";
		_userCreazione = "";
		_userModifica = "";
		_userCancellazione = "";
		_flagCancellato = 0;
		_flagValido = 0;
	}
	
	
	/**
	 * Costruttore
	 * @param controllore é l'oggetto entity
	 */
	public ControlloreBO(Controllore controllore) {
		_id = controllore.get_id();
		_idControllore = controllore.get_idControllore();
		_username = controllore.get_username();
		_nome = controllore.get_nome();
		_cognome = controllore.get_cognome();
		_annoValiditaInizio = controllore.get_annoValiditaInizio();
		_annoValiditaFine = controllore.get_annoValiditaFine();
		_note = controllore.get_note();
		_dataCreazione = controllore.get_dataCreazione();
		_userCreazione = controllore.get_userCreazione();
		_dataModifica = controllore.get_dataModifica();
		_userModifica = controllore.get_userModifica();
		_dataCancellazione = controllore.get_dataCancellazione();
		_userCancellazione = controllore.get_userCancellazione();
		_flagCancellato = controllore.get_flagCancellato();
		_flagValido = controllore.get_flagValido();
	}
	

	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return Controllore
	 */
	public Controllore convertToEntity() {
		Controllore entity = new Controllore();
		
		entity.set_id(_id);
		entity.set_idControllore(_idControllore);
		entity.set_username(_username);
		entity.set_nome(_nome);
		entity.set_cognome(_cognome);
		entity.set_annoValiditaInizio(_annoValiditaInizio);
		entity.set_annoValiditaFine(_annoValiditaFine);
		entity.set_note(_note);
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
	
	@Override
	public String toString() {
		StringBuilder strBilder = new StringBuilder();

		strBilder.append("id: ");
		strBilder.append(this._id);
		strBilder.append("\n");
		strBilder.append("idControllore: ");
		strBilder.append(this._idControllore);
		strBilder.append("\n");
		strBilder.append("username: ");
		strBilder.append(this._username);
		strBilder.append("\n");
		strBilder.append("nome: ");
		strBilder.append(this._nome);
		strBilder.append("\n");
		strBilder.append("cognome: ");
		strBilder.append(this._cognome);
		strBilder.append("\n");
		strBilder.append("annoValiditaInizio: ");
		strBilder.append(this._annoValiditaInizio);
		strBilder.append("\n");
		strBilder.append("annoValiditaFine: ");
		strBilder.append(this._annoValiditaFine);
		strBilder.append("\n");
		strBilder.append("dataCreazione: ");
		strBilder.append(this._dataCreazione);
		strBilder.append("\n");
		strBilder.append("userCreazione: ");
		strBilder.append(this._userCreazione);
		strBilder.append("\n");
		strBilder.append("dataModifica: ");
		strBilder.append(this._dataModifica);
		strBilder.append("\n");
		strBilder.append("userModifica: ");
		strBilder.append(this._userModifica);
		strBilder.append("\n");
		strBilder.append("dataCancellazione: ");
		strBilder.append(this._dataCancellazione);
		strBilder.append("\n");
		strBilder.append("userCancellazione: ");
		strBilder.append(this._userCancellazione);
		strBilder.append("\n");
		strBilder.append("flagCancellato: ");
		strBilder.append(this._flagCancellato);
		strBilder.append("\n");
		strBilder.append("flagValido: ");
		strBilder.append(this._flagValido);
		strBilder.append("\n");
		
		return strBilder.toString();
	}
	

	/***************************************************************/
	/*						GETTER E SETTER			   			   */
	/***************************************************************/

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public Long get_idControllore() {
		return _idControllore;
	}

	public void set_idControllore(Long _idControllore) {
		this._idControllore = _idControllore;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public String get_cognome() {
		return _cognome;
	}

	public void set_cognome(String _cognome) {
		this._cognome = _cognome;
	}

	public Timestamp get_dataCreazione() {
		return _dataCreazione;
	}
	
	public int get_annoValiditaInizio() {
		return _annoValiditaInizio;
	}

	public void set_annoValiditaInizio(int _annoValiditaInizio) {
		this._annoValiditaInizio = _annoValiditaInizio;
	}

	public int get_annoValiditaFine() {
		return _annoValiditaFine;
	}

	public void set_annoValiditaFine(int _annoValiditaFine) {
		this._annoValiditaFine = _annoValiditaFine;
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


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}
	
}
