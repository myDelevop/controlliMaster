package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Controllore
 *
 */
@Entity
@Table(name = "CONTROLLORE")
@NamedQuery(name="Controllore.findAll", query="SELECT c FROM Controllore c")
public class Controllore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;

	@Column(name = "ID_CONTROLLORE")
	private Long _idControllore;
	
	@Column(name = "USERNAME")
	private String _username;
	
	@Column(name = "NOME")
	private String _nome;
	
	@Column(name = "COGNOME")
	private String _cognome;
	
	@Column(name = "ANNO_VALIDITA_INIZIO")
	private Integer _annoValiditaInizio;

	@Column(name = "ANNO_VALIDITA_FINE")
	private Integer _annoValiditaFine;	
	
	@Column(name = "NOTE")
	private String _note;
	
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
	public Controllore() {
		_id=0L;
		_idControllore=0L;
		_username="";
		_nome="";
		_cognome="";
		_annoValiditaInizio=0;
		_annoValiditaFine=0;	
		_note="";
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


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}
   
}
