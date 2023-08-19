package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ChecklistRegola
 *
 */
@Entity
@Table(name = "CHECKLIST_REGOLE")
@NamedQuery(name="ChecklistRegola.findAll", query="SELECT a FROM ChecklistRegola a")
public class ChecklistRegola implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_CHECKLIST_REGOLA")
	private Long _idChecklistRegola;
	
	@Column(name = "ID_CONTROLLO")
	private Long _idControllo;
	
	@Column(name = "ANNO")
	private Integer _anno;
	
	@Column(name = "CAMPAGNA")
	private Integer _campagna;	
	
	@Column(name = "MISURA")
	private String _misura;	
	
	@Column(name = "INTERVENTO")
	private String _intervento;	

	@Column(name = "SOTTOINTERVENTO")
	private String _sottointervento;

	@Column(name = "TIPO_CAMPIONE")
	private String _tipoCampione;

	@Column(name = "TIPO_CONTROLLO")
	private String _tipoControllo;
	
	@Column(name = "ID_CHECKLIST_TEMPLATE")
	private Integer _idChecklistTemplate;
		
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
	public ChecklistRegola() {
		_id=0L;
		_idChecklistRegola=0L;
		_idControllo=0L;
		_anno=0;
		_campagna=0;
		_misura="";
		_intervento="";
		_sottointervento="";
		_tipoCampione="";
		_tipoControllo="";
		_idChecklistTemplate=0;	
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
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

	public Long get_idControllo() {
		return _idControllo;
	}

	public void set_idControllo(Long _idControllo) {
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
