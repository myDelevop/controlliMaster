package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity implementation class for Entity: Verbale
 *
 */
@Entity
@Table(name = "VERBALE")
public class Verbale implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_VERBALE")
	private Integer _idVerbale;
	
	@Column(name = "ID_CONTROLLO")
	private Integer _idControllo;
	
	@Column(name = "ID_aZIENDA")
	private Integer _idAzienda;

	@Column(name = "ANNO")
	private Integer _anno;
	
	@Column(name = "CAMPAGNA")
	private Integer _campagna;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Column(name = "STATO")
	private String _stato;
	

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
	public Verbale() {
		_id=0L;
		_idVerbale=0;
		_idControllo=0;
		_idAzienda=0;
		_anno=0;
		_campagna=0;
		_cuaa="";
		_stato="";	
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

	public Integer get_idVerbale() {
		return _idVerbale;
	}

	public void set_idVerbale(Integer _idVerbale) {
		this._idVerbale = _idVerbale;
	}

	public Integer get_idControllo() {
		return _idControllo;
	}

	public void set_idControllo(Integer _idControllo) {
		this._idControllo = _idControllo;
	}

	public Integer get_anno() {
		return _anno;
	}

	public void set_anno(Integer _anno) {
		this._anno = _anno;
	}

	public Integer get_campagna() {
		return _campagna;
	}

	public void set_campagna(Integer _campagna) {
		this._campagna = _campagna;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public String get_stato() {
		return _stato;
	}

	public void set_stato(String _stato) {
		this._stato = _stato;
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

	public Integer get_idAzienda() {
		return _idAzienda;
	}

	public void set_idAzienda(Integer _idAzienda) {
		this._idAzienda = _idAzienda;
	}


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}

	


}
