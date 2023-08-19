package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the UTENTE database table.
 * 
 */
@Entity
@Table(name="UTENTE")
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long _id;

	@Column(name="COGNOME")
	private String _cognome;

	@Column(name="DATA_CANCELLAZIONE")
	private Timestamp _dataCancellazione;

	@Column(name="DATA_CREAZIONE")
	private Timestamp _dataCreazione;

	@Column(name="DATA_MODIFICA")
	private Timestamp _dataModifica;

	@Column(name="DOMINIO")
	private String _dominio;

	@Column(name="FLAG_CANCELLATO")
	private Integer _flagCancellato;

	@Column(name="FLAG_VALIDO")
	private Integer _flagValido;

	@Column(name="ID_UTENTE")
	private Long _idUtente;

	@Column(name="NOME")
	private String _nome;

	@Column(name="USER_CANCELLAZIONE")
	private String _userCancellazione;

	@Column(name="USER_CREAZIONE")
	private String _userCreazione;

	@Column(name="USER_MODIFICA")
	private String _userModifica;

	@Column(name="USERNAME")
	private String _username;
	
	@Column(name="EMAIL")
	private String _email;
	
	
	
	/**********************************************/
	/*					COSTRUTTORE				  */	
	/**********************************************/

	public Utente() {
		this._id = 0L;
		this._cognome = "";
		this._dataCancellazione = null;
		this._dataCreazione = null;
		this._dataModifica = null;
		this._dominio = "";
		this._flagCancellato = 0;
		this._flagValido = 0;
		this._idUtente = 0L;
		this._nome = "";
		this._userCancellazione = "";
		this._userCreazione = "";
		this._userModifica = "";
		this._username = "";
		this._email = "";
	}
	

	/**********************************************/
	/*				GETTER E SETTER				  */	
	/**********************************************/

	public Long get_id() {
		return this._id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String get_cognome() {
		return this._cognome;
	}

	public void set_cognome(String _cognome) {
		this._cognome = _cognome;
	}

	public Timestamp get_dataCancellazione() {
		return this._dataCancellazione;
	}

	public void set_dataCancellazione(Timestamp _dataCancellazione) {
		this._dataCancellazione = _dataCancellazione;
	}

	public Timestamp get_dataCreazione() {
		return this._dataCreazione;
	}

	public void set_dataCreazione(Timestamp _dataCreazione) {
		this._dataCreazione = _dataCreazione;
	}

	public Timestamp get_dataModifica() {
		return this._dataModifica;
	}

	public void set_dataModifica(Timestamp _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public String get_dominio() {
		return this._dominio;
	}

	public void set_dominio(String _dominio) {
		this._dominio = _dominio;
	}

	public Integer get_flagCancellato() {
		return this._flagCancellato;
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

	public Long get_idUtente() {
		return this._idUtente;
	}

	public void set_idUtente(Long _idUtente) {
		this._idUtente = _idUtente;
	}

	public String get_nome() {
		return this._nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public String get_userCancellazione() {
		return this._userCancellazione;
	}

	public void set_userCancellazione(String _userCancellazione) {
		this._userCancellazione = _userCancellazione;
	}

	public String get_userCreazione() {
		return this._userCreazione;
	}

	public void set_userCreazione(String _userCreazione) {
		this._userCreazione = _userCreazione;
	}

	public String get_userModifica() {
		return this._userModifica;
	}

	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	public String get_username() {
		return this._username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

}