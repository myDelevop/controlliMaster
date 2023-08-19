package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the STAZIONE_FORESTALE database table.
 * 
 */
@Entity
@Table(name = "STAZIONE_FORESTALE")
@NamedQuery(name="StazioneForestale.findAll", query="SELECT s FROM StazioneForestale s")
public class StazioneForestale implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_STAZIONE_FORESTALE")
	private Long _idStazioneForestale;
	
	@Column(name = "NUMERO")
	private String _numero;
	
	@Column(name = "ISPETTORATO_FORESTALE_IT")
	private String _ispettoratoForestaleIT;
	
	@Column(name = "ISPETTORATO_FORESTALE_DE")
	private String _ispettoratoForestaleDE;
	
	@Column(name = "NOME")
	private String _nome;
	
	@Column(name = "DESCR_IT")
	private String _descrIT;

	@Column(name = "DESCR_DE")
	private String _descrDE;
	
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
	public StazioneForestale() {
		_id=0L;
		_idStazioneForestale=0L;
		_numero = "";
		_ispettoratoForestaleIT = "";
		_ispettoratoForestaleDE = "";
		_nome = "";
		_descrIT="";
		_descrDE="";
		_annoValiditaInizio=0;
		_annoValiditaFine=0;
		_note = "";
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



	public Long get_idStazioneForestale() {
		return _idStazioneForestale;
	}



	public void set_idStazioneForestale(Long _idStazioneForestale) {
		this._idStazioneForestale = _idStazioneForestale;
	}


	public String get_numero() {
		return _numero;
	}


	public void set_numero(String _numero) {
		this._numero = _numero;
	}


	public String get_ispettoratoForestaleIT() {
		return _ispettoratoForestaleIT;
	}


	public void set_ispettoratoForestaleIT(String _ispettoratoForestaleIT) {
		this._ispettoratoForestaleIT = _ispettoratoForestaleIT;
	}


	public String get_ispettoratoForestaleDE() {
		return _ispettoratoForestaleDE;
	}


	public void set_ispettoratoForestaleDE(String _ispettoratoForestaleDE) {
		this._ispettoratoForestaleDE = _ispettoratoForestaleDE;
	}


	public String get_descrIT() {
		return _descrIT;
	}



	public void set_descrIT(String _descrIT) {
		this._descrIT = _descrIT;
	}



	public String get_descrDE() {
		return _descrDE;
	}



	public void set_descrDE(String _descrDE) {
		this._descrDE = _descrDE;
	}



	public int get_annoValiditaInizio() {
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


	public String get_nome() {
		return _nome;
	}


	public void set_nome(String _nome) {
		this._nome = _nome;
	}


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}
   
}
