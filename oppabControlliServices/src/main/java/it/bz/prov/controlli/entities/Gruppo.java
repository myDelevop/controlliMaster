package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the GRUPPO database table.
 * 
 */
@Entity
@Table(name="GRUPPO")
@NamedQuery(name="Gruppo.findAll", query="SELECT g FROM Gruppo g")
public class Gruppo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long _id;

	@Column(name="DATA_CANCELLAZIONE")
	private Timestamp _dataCancellazione;

	@Column(name="DATA_CREAZIONE")
	private Timestamp _dataCreazione;

	@Column(name="DATA_MODIFICA")
	private Timestamp _dataModifica;

	@Column(name="DESCRIZIONE_GRUPPO_DE")
	private String _descrizioneGruppoDe;

	@Column(name="DESCRIZIONE_GRUPPO_IT")
	private String _descrizioneGruppoIt;

	@Column(name="FLAG_CANCELLATO")
	private Integer _flagCancellato;

	@Column(name="FLAG_VALIDO")
	private Integer _flagValido;

	@Column(name="ID_GRUPPO")
	private Long _idGruppo;

	@Column(name="NOME_GRUPPO")
	private String _nomeGruppo;

	@Column(name="USER_CANCELLAZIONE")
	private String _userCancellazione;

	@Column(name="USER_CREAZIONE")
	private String _userCreazione;

	@Column(name="USER_MODIFICA")
	private String _userModifica;
	
	
	/**************************************************/
	/*				COSTRUTTORE						  */
	/**************************************************/
	
	/**
	 * costruttore
	 */
	public Gruppo() {
		this._id = 0L;
		this._dataCancellazione = null;
		this._dataCreazione = null;
		this._dataModifica = null;
		this._descrizioneGruppoDe = "";
		this._descrizioneGruppoIt = "";
		this._flagCancellato = 0;
		this._flagValido = 0;
		this._idGruppo = 0L;
		this._nomeGruppo = "";
		this._userCancellazione = "";
		this._userCreazione = "";
		this._userModifica = "";
	}
	
	
	/**************************************************/
	/*				GETTER E SETTER					  */
	/**************************************************/

	public Long get_id() {
		return this._id;
	}

	public void set_id(Long _id) {
		this._id = _id;
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

	public String get_descrizioneGruppoDe() {
		return this._descrizioneGruppoDe;
	}

	public void set_descrizioneGruppoDe(String _descrizioneGruppoDe) {
		this._descrizioneGruppoDe = _descrizioneGruppoDe;
	}

	public String get_descrizioneGruppoIt() {
		return this._descrizioneGruppoIt;
	}

	public void set_descrizioneGruppoIt(String _descrizioneGruppoIt) {
		this._descrizioneGruppoIt = _descrizioneGruppoIt;
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

	public Long get_idGruppo() {
		return this._idGruppo;
	}

	public void set_idGruppo(Long _idGruppo) {
		this._idGruppo = _idGruppo;
	}

	public String get_nomeGruppo() {
		return this._nomeGruppo;
	}

	public void set_nomeGruppo(String _nomeGruppo) {
		this._nomeGruppo = _nomeGruppo;
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

}