package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the STAZIONE_FORESTALE_MAPPING database table.
 * 
 */
@Entity
@Table(name = "STAZIONE_FORESTALE_MAPPING")
public class StazioneForestaleMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_MAPPING")
	private Long _idMapping;
	
	@Column(name = "COMUNE_IT")
	private String _comuneIT;
	
	@Column(name = "COMUNE_DE")
	private String _comuneDE;
	
	@Column(name = "STAZIONE_FORESTALE")
	private String _stazioneForestale;
	
	@Column(name = "STAZIONE_FORESTALE_IT")
	private String _stazioneForestaleIT;
	
	@Column(name = "STAZIONE_FORESTALE_DE")
	private String _stazioneForestaleDE;
	
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
	public StazioneForestaleMapping() {
		_id=0L;
		_idMapping=0L;
		_comuneIT = "";
		_comuneDE="";
		_stazioneForestale="";
		_stazioneForestaleIT="";
		_stazioneForestaleDE="";		
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

	public Long get_idMapping() {
		return _idMapping;
	}

	public void set_idMapping(Long _idMapping) {
		this._idMapping = _idMapping;
	}

	public String get_comuneIT() {
		return _comuneIT;
	}

	public void set_comuneIT(String _comuneIT) {
		this._comuneIT = _comuneIT;
	}

	public String get_comuneDE() {
		return _comuneDE;
	}

	public void set_comuneDE(String _comuneDE) {
		this._comuneDE = _comuneDE;
	}

	public String get_stazioneForestale() {
		return _stazioneForestale;
	}

	public void set_stazioneForestale(String _stazioneForestale) {
		this._stazioneForestale = _stazioneForestale;
	}

	public String get_stazioneForestaleIT() {
		return _stazioneForestaleIT;
	}

	public void set_stazioneForestaleIT(String _stazioneForestaleIT) {
		this._stazioneForestaleIT = _stazioneForestaleIT;
	}

	public String get_stazioneForestaleDE() {
		return _stazioneForestaleDE;
	}

	public void set_stazioneForestaleDE(String _stazioneForestaleDE) {
		this._stazioneForestaleDE = _stazioneForestaleDE;
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
