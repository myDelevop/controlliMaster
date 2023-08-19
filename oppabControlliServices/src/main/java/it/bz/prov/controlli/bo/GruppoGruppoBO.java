package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Gruppo;
import it.bz.prov.controlli.entities.GruppoGruppo;

/**
 * oggetto BO che rappresenta la relazione tra due gruppi
 * @author bpettazzoni
 *
 */
public class GruppoGruppoBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idRel")
	private Long _idRel;
	
	@JsonProperty("idGruppoParent")
	private Long _idGruppoParent;

	@JsonProperty("idGruppoChild")
	private Long _idGruppoChild;
	
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
	
	@JsonProperty("utente")
	private GruppoBO _gruppoParent;

	@JsonProperty("gruppo")
	private GruppoBO _gruppoChild;
	
	/**************************************************/
	/*				COSTRUTTORI						  */
	/**************************************************/
	
	/**
	 * Costruttore
	 */
	public GruppoGruppoBO() {
		_id=0L;
		_idRel=0L;
		_idGruppoParent=0L;
		_idGruppoChild=0L;
		_note="";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
		_gruppoChild = new GruppoBO();
		_gruppoParent = new GruppoBO();
	}
	
	/**
	 * Costruttore con parametri
	 * @param gg é l'oggetto entity
	 */
	public GruppoGruppoBO(GruppoGruppo gg) {
		_id=gg.get_id();
		_idRel=gg.get_idRel();
		_idGruppoParent=gg.get_idGruppoParent();
		_idGruppoChild=gg.get_idGruppoChild();
		_note=gg.get_note();
		_userCreazione=gg.get_userCreazione();
		_dataCreazione= gg.get_dataCreazione();
		_userModifica=gg.get_userModifica();
		_dataModifica= gg.get_dataModifica();
		_userCancellazione=gg.get_userCancellazione();
		_dataCancellazione= gg.get_dataCancellazione();
		_flagCancellato=gg.get_flagCancellato();
		_flagValido=gg.get_flagValido();
		_gruppoChild = new GruppoBO();
		_gruppoParent = new GruppoBO();
	}
	
	
	/**
	 * Costruttore con parametri
	 * @param gg é l'entità gruppoGruppo
	 * @param parent é il gruppo parent
	 * @param child é il gruppo child
	 */
	public GruppoGruppoBO(GruppoGruppo gg, Gruppo parent, Gruppo child) {
		this(gg);
		_gruppoChild = new GruppoBO(parent);
		_gruppoParent = new GruppoBO(child);
	}
	
	
	/**************************************************/
	/*					UTILITY					  	  */
	/**************************************************/

	/**
	 * conversione dell'oggetto BO in entity
	 * @return GruppoGruppo
	 */
	public GruppoGruppo convertToEntity() {
		GruppoGruppo entity = new GruppoGruppo();
		entity.set_id(_id);
		entity.set_idRel(_idRel);
		entity.set_idGruppoParent(_idGruppoParent);
		entity.set_idGruppoChild(_idGruppoChild);
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

	public Long get_idRel() {
		return _idRel;
	}

	public void set_idRel(Long _idRel) {
		this._idRel = _idRel;
	}

	public Long get_idGruppoParent() {
		return _idGruppoParent;
	}

	public void set_idGruppoParent(Long _idGruppoParent) {
		this._idGruppoParent = _idGruppoParent;
	}

	public Long get_idGruppoChild() {
		return _idGruppoChild;
	}

	public void set_idGruppoChild(Long _idGruppoChild) {
		this._idGruppoChild = _idGruppoChild;
	}

	public String get_note() {
		return _note;
	}

	public void set_note(String _note) {
		this._note = _note;
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

	public GruppoBO get_gruppoParent() {
		return _gruppoParent;
	}

	public void set_gruppoParent(GruppoBO _gruppoParent) {
		this._gruppoParent = _gruppoParent;
	}

	public GruppoBO get_gruppoChild() {
		return _gruppoChild;
	}

	public void set_gruppoChild(GruppoBO _gruppoChild) {
		this._gruppoChild = _gruppoChild;
	}
	

}
