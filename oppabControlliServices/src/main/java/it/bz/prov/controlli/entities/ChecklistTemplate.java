package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ChecklistTemplate
 *
 */
@Entity
@Table(name = "CHECKLIST_TEMPLATE")
@NamedQuery(name="ChecklistTemplate.findAll", query="SELECT a FROM ChecklistTemplate a")
public class ChecklistTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long _id;
		
	@Column(name = "ID_CHECKLIST_TEMPLATE")
	private Integer _idChecklistTemplate;
	
	@Column(name = "ID_CONTROLLO")
	private Integer _idControllo;
	
	@Column(name = "ANNO")
	private Integer _anno;
	
	@Column(name = "CAMPAGNA")
	private Integer _campagna;	
	
	@Column(name = "NOME_IT")
	private String _nomeIT;

	@Column(name = "NOME_DE")
	private String _nomeDE;
	
	@Column(name = "DESCR_IT")
	private String _descrIT;

	@Column(name = "DESCR_DE")
	private String _descrDE;
	
	@Column(name = "TIPO_MODELLO_IT")
	private String _tipoModelloIT;

	@Column(name = "TIPO_MODELLO_DE")
	private String _tipoModelloDE;
	
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
	public ChecklistTemplate() {
		_id=0L;
		_idChecklistTemplate=0;
		_idControllo=0;
		_anno=0;
		_campagna=0;
		_nomeIT="";
		_nomeDE="";
		_descrIT="";
		_descrDE="";
		_tipoModelloIT="";
		_tipoModelloDE="";				
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

	public Integer get_idChecklistTemplate() {
		return _idChecklistTemplate;
	}

	public void set_idChecklistTemplate(Integer _idChecklistTemplate) {
		this._idChecklistTemplate = _idChecklistTemplate;
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

	public String get_nomeIT() {
		return _nomeIT;
	}

	public void set_nomeIT(String _nomeIT) {
		this._nomeIT = _nomeIT;
	}

	public String get_nomeDE() {
		return _nomeDE;
	}

	public void set_nomeDE(String _nomeDE) {
		this._nomeDE = _nomeDE;
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

	public String get_tipoModelloIT() {
		return _tipoModelloIT;
	}

	public void set_tipoModelloIT(String _tipoModelloIT) {
		this._tipoModelloIT = _tipoModelloIT;
	}

	public String get_tipoModelloDE() {
		return _tipoModelloDE;
	}

	public void set_tipoModelloDE(String _tipoModelloDE) {
		this._tipoModelloDE = _tipoModelloDE;
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
