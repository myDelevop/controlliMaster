package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.ChecklistTemplate;

/**
 * Oggetto BO che rappresenta il template della checklist
 * @author bpettazzoni
 *
 */
public class ChecklistTemplateBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idChecklistTemplate")
	private Integer _idChecklistTemplate;
	
	@JsonProperty("idControllo")
	private Integer _idControllo;
	
	@JsonProperty("anno")
	private Integer _anno;
	
	@JsonProperty("campagna")
	private Integer _campagna;
	
	@JsonProperty("nomeIT")
	private String _nomeIT;
	
	@JsonProperty("nomeDE")
	private String _nomeDE;
	
	@JsonProperty("descrIT")
	private String _descrIT;
	
	@JsonProperty("descrDE")
	private String _descrDE;
	
	@JsonProperty("tipoModelloIT")
	private String _tipoModelloIT;

	@JsonProperty("tipoModelloDE")
	private String _tipoModelloDE;

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
	
	
	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/
	
	/**
	 * Costruttore
	 */
	public ChecklistTemplateBO() {		
		_nomeIT = "";
		_nomeDE = "";
		_descrIT = "";
		_descrDE = "";
		_tipoModelloIT = "";
		_tipoModelloDE = "";
		_userCreazione = "";
		_userModifica = "";
		_userCancellazione = "";
		_flagCancellato = 0;
		_flagValido = 0;
	}

	/**
	 * Costruttore con parametri
	 * @param checklistTemplate
	 */
	public ChecklistTemplateBO(ChecklistTemplate checklistTemplate) {	
		_id = checklistTemplate.get_id();
		_idChecklistTemplate = checklistTemplate.get_idChecklistTemplate();
		_idControllo = checklistTemplate.get_idControllo();
		_anno = checklistTemplate.get_anno();
		_campagna = checklistTemplate.get_campagna();
		_nomeIT = checklistTemplate.get_nomeIT();
		_nomeDE = checklistTemplate.get_nomeDE();
		_descrIT = checklistTemplate.get_descrIT();
		_descrDE = checklistTemplate.get_descrDE();
		_tipoModelloIT = checklistTemplate.get_tipoModelloIT();
		_tipoModelloDE = checklistTemplate.get_tipoModelloDE();	
		_dataCreazione = checklistTemplate.get_dataCreazione();
		_userCreazione = checklistTemplate.get_userCreazione();
		_dataModifica = checklistTemplate.get_dataModifica();
		_userModifica = checklistTemplate.get_userModifica();
		_dataCancellazione = checklistTemplate.get_dataCancellazione();
		_userCancellazione = checklistTemplate.get_userCancellazione();
		_flagCancellato = checklistTemplate.get_flagCancellato();
		_flagValido = checklistTemplate.get_flagValido();
	}
	
	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return ChecklistTemplate
	 */
	public ChecklistTemplate convertToEntity() {
		ChecklistTemplate entity = new ChecklistTemplate();
		entity.set_id(_id);
		entity.set_idChecklistTemplate(_idChecklistTemplate);
		entity.set_idControllo(_idControllo);
		entity.set_anno(_anno);
		entity.set_campagna(_campagna);
		entity.set_nomeIT(_nomeIT);
		entity.set_nomeDE(_nomeDE);
		entity.set_descrIT(_descrIT);
		entity.set_descrDE(_descrDE);
		entity.set_tipoModelloIT(_tipoModelloIT);
		entity.set_tipoModelloDE(_tipoModelloDE);		
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
	
	

	/***************************************************************/
	/*						GETTER E SETTER			   			   */
	/***************************************************************/
	
	
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
