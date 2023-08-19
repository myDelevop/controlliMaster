package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.ChecklistRegola;
import it.bz.prov.controlli.entities.Controllo;


/**
 * Oggetto BO che rappresenta le regole di applicazione delle checklist
 * @author bpettazzoni
 *
 */
public class ChecklistRegolaBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idChecklistRegola")
	private Long _idChecklistRegola;
	
	@JsonProperty("controllo")
	private ControlloBO _controllo;
	
	@JsonProperty("anno")
	private Integer _anno;
	
	@JsonProperty("campagna")
	private Integer _campagna;	
	
	@JsonProperty("misura")
	private String _misura;	
	
	@JsonProperty("intervento")
	private String _intervento;
	
	@JsonProperty("sottoIntervento")
	private String _sottointervento;

	@JsonProperty("tipoCampione")
	private String _tipoCampione;

	@JsonProperty("tipoControllo")
	private String _tipoControllo;
	
	@JsonProperty("idChecklistTemplate")
	private Integer _idChecklistTemplate;	
	
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
	public ChecklistRegolaBO() {
		_controllo = new ControlloBO();
		_misura = "";
		_intervento = "";
		_sottointervento = "";
		_tipoCampione = "";
		_tipoControllo = "";		
		_userCreazione = "";
		_userModifica = "";
		_userCancellazione = "";
		_flagCancellato = 0;
		_flagValido = 0;
	}
	
	/**
	 * Costruttore con parametri
	 * @param checklistRegole
	 */
	public ChecklistRegolaBO(ChecklistRegola checklistRegole) {
		_controllo = new ControlloBO();

		_id = checklistRegole.get_id();
		_idChecklistRegola  = checklistRegole.get_idChecklistRegola();
		_anno = checklistRegole.get_anno();
		_campagna = checklistRegole.get_campagna();
		_misura = checklistRegole.get_misura();
		_intervento = checklistRegole.get_intervento();
		_sottointervento = checklistRegole.get_sottointervento();
		_tipoCampione = checklistRegole.get_tipoCampione();
		_tipoControllo = checklistRegole.get_tipoControllo();
		_idChecklistTemplate = checklistRegole.get_idChecklistTemplate();
		_dataCreazione = checklistRegole.get_dataCreazione();
		_userCreazione = checklistRegole.get_userCreazione();
		_dataModifica = checklistRegole.get_dataModifica();
		_userModifica = checklistRegole.get_userModifica();
		_dataCancellazione = checklistRegole.get_dataCancellazione();
		_userCancellazione = checklistRegole.get_userCancellazione();
		_flagCancellato = checklistRegole.get_flagCancellato();
		_flagValido = checklistRegole.get_flagValido();
	}
	
	public ChecklistRegolaBO(ChecklistRegola checklistRegole, Controllo dettagliControllo) {
		this(checklistRegole);
		
		if(dettagliControllo != null)
			this._controllo = new ControlloBO(dettagliControllo);

	}

	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return ChecklistRegole
	 */
	public ChecklistRegola convertToEntity() {
		ChecklistRegola entity = new ChecklistRegola();		
		entity.set_id(_id);
		entity.set_idChecklistRegola(_idChecklistRegola);
		if(this.get_controllo().get_idControllo() != null)
			entity.set_idControllo(this.get_controllo().get_idControllo());
		entity.set_anno(_anno);
		entity.set_campagna(_campagna);
		entity.set_misura(_misura);
		entity.set_intervento(_intervento);
		entity.set_sottointervento(_sottointervento);
		entity.set_tipoCampione(_tipoCampione);
		entity.set_tipoControllo(_tipoControllo);
		entity.set_idChecklistTemplate(_idChecklistTemplate);
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

	public Long get_idChecklistRegola() {
		return _idChecklistRegola;
	}

	public void set_idChecklistRegola(Long _idChecklistRegola) {
		this._idChecklistRegola = _idChecklistRegola;
	}

	public ControlloBO get_controllo() {
		return _controllo;
	}

	public void set_controllo(ControlloBO _controllo) {
		this._controllo = _controllo;
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

	public String get_misura() {
		return _misura;
	}

	public void set_misura(String _misura) {
		this._misura = _misura;
	}

	public String get_intervento() {
		return _intervento;
	}

	public void set_intervento(String _intervento) {
		this._intervento = _intervento;
	}

	public String get_sottointervento() {
		return _sottointervento;
	}

	public void set_sottointervento(String _sottointervento) {
		this._sottointervento = _sottointervento;
	}

	public String get_tipoCampione() {
		return _tipoCampione;
	}

	public void set_tipoCampione(String _tipoCampione) {
		this._tipoCampione = _tipoCampione;
	}

	public String get_tipoControllo() {
		return _tipoControllo;
	}

	public void set_tipoControllo(String _tipoControllo) {
		this._tipoControllo = _tipoControllo;
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

	public Integer get_idChecklistTemplate() {
		return _idChecklistTemplate;
	}

	public void set_idChecklistTemplate(Integer _idChecklistTemplate) {
		this._idChecklistTemplate = _idChecklistTemplate;
	}

}
