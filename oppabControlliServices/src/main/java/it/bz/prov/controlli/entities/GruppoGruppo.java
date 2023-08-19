package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the GRUPPO_GRUPPO database table.
 * 
 */
@Entity
@Table(name="GRUPPO_GRUPPO")
public class GruppoGruppo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Long _id;
	
	@Column(name="ID_REL")
	private Long _idRel;
	
	@Column(name="ID_GRUPPO_PARENT")
	private Long _idGruppoParent;

	@Column(name="ID_GRUPPO_CHILD")
	private Long _idGruppoChild;
	
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
	 * Costruttore
	 */
	public GruppoGruppo() {
		_id=0L;
		_idRel=0L;
		_idGruppoParent=0L;
		_idGruppoChild=0L;
		_note = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	
	/**************************************************/
	/*				COSTRUTTORE						  */
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

}
