package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AziendaStazioneForestale
 *
 */
@Entity
@Table(name = "AZIENDA_STAZ_FOR")
@NamedQuery(name="AziendaStazioneForestale.findAll", query="SELECT a FROM AziendaStazioneForestale a")
public class AziendaStazioneForestale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long _id;

	@Column(name = "ID_AZI_STAZ_FOR")
	private Long _idAziStazFor;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Column(name = "ID_AZIENDA")
	private Long _idAzienda;
	
	@Column(name = "ID_STAZIONE_FORESTALE")
	private Long _idStazioneForestale;
	
	@Column(name = "CAMPAGNA")
	private Integer _campagna;
	
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
	public AziendaStazioneForestale() {
		_id=0L;
		_idAziStazFor=0L;
		_cuaa="";
		_idAzienda=0L;
		_idStazioneForestale=0L;
		_campagna=0;	
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




	public Long get_idAziStazFor() {
		return _idAziStazFor;
	}




	public void set_idAziStazFor(Long _idAziStazFor) {
		this._idAziStazFor = _idAziStazFor;
	}




	public String get_cuaa() {
		return _cuaa;
	}




	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}




	public Long get_idAzienda() {
		return _idAzienda;
	}




	public void set_idAzienda(Long _idAzienda) {
		this._idAzienda = _idAzienda;
	}




	public Long get_idStazioneForestale() {
		return _idStazioneForestale;
	}




	public void set_idStazioneForestale(Long _idStazioneForestale) {
		this._idStazioneForestale = _idStazioneForestale;
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

	public Integer get_campagna() {
		return _campagna;
	}

	public void set_campagna(Integer _campagna) {
		this._campagna = _campagna;
	}
   
}
