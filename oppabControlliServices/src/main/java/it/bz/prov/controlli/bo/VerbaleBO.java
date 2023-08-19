package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.bz.prov.controlli.entities.Verbale;

/**
 * Oggetto BO che rappresenta l'entità di gestione dei verbali ad alto livello
 * @author bpettazzoni
 *
 */
public class VerbaleBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idVerbale")
	private Integer _idVerbale;
	
	@JsonProperty("idControllo")
	private Integer _idControllo;

	@JsonProperty("idAzienda")
	private Integer _idAzienda;

	@JsonProperty("anno")
	private Integer _anno;
	
	@JsonProperty("campagna")
	private Integer _campagna;
	
	@JsonProperty("cuaa")
	private String _cuaa;
	
	@JsonProperty("stato")
	private String _stato;
	
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

	

	/**************************************************/
	/*				COSTRUTTORI						  */
	/**************************************************/
	
	
	/**
	 * Costruttore
	 */
	public VerbaleBO() {
		_cuaa="";
		_stato="";
		_note="";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	/**
	 * costruttore con parametri
	 * @param verbale
	 */
	public VerbaleBO(Verbale verbale) {
		_id = verbale.get_id();
		_idVerbale= verbale.get_idVerbale();
		_idControllo = verbale.get_idControllo();
		_idAzienda = verbale.get_idAzienda();
		_anno = verbale.get_anno();
		_campagna = verbale.get_campagna();
		_cuaa = verbale.get_cuaa();
		_stato = verbale.get_stato();
		_note= verbale.get_note();
		_dataCreazione = verbale.get_dataCreazione();
		_userCreazione = verbale.get_userCreazione();
		_dataModifica = verbale.get_dataModifica();
		_userModifica = verbale.get_userModifica();
		_dataCancellazione = verbale.get_dataCancellazione();
		_userCancellazione = verbale.get_userCancellazione();
		_flagCancellato = verbale.get_flagCancellato();
		_flagValido = verbale.get_flagValido();
	}
	

	/**************************************************/
	/*					UTILITY					  	  */
	/**************************************************/
	
	/**
	 * conversione dell'oggetto in entity
	 * @return Verbale
	 */
	public Verbale convertToEntity() {
		Verbale entity = new Verbale();
		entity.set_id(_id);
		entity.set_idVerbale(_idVerbale);
		entity.set_idControllo(_idControllo);
		entity.set_idAzienda(_idAzienda);
		entity.set_anno(_anno);
		entity.set_campagna(_campagna);
		entity.set_cuaa(_cuaa);
		entity.set_stato(_stato);
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
