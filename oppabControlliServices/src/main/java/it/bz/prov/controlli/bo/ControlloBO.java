package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Controllo;

/**
 * Oggetto BO che rappresenta il controllo
 * @author bpettazzoni
 *
 */
public class ControlloBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idControllo")
	private Long _idControllo;
	
	@JsonProperty("anno")
	private Integer _anno;
	
	@JsonProperty("campagna")
	private Integer _campagna;
	
	@JsonProperty("descrIT")
	private String _descrIT;
	
	@JsonProperty("descrDE")
	private String _descrDE;
	
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

	
	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/
	
	/**
	 * Costruttore senza parametri
	 */
	public ControlloBO() {
		_descrIT="";
		_descrDE="";
		_note = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	/**
	 * Costruttore con parametri
	 * @param controllo é l'oggetto entity
	 */
	public ControlloBO(Controllo controllo) {
		_id = controllo.get_id();
		_idControllo = controllo.get_idControllo();
		_anno= controllo.get_anno();
		_campagna = controllo.get_campagna();
		_descrIT=controllo.get_descrIT();
		_descrDE=controllo.get_descrDE();
		_note = controllo.get_note();
		_userCreazione= controllo.get_userCreazione();
		_userModifica= controllo.get_userModifica();
		_userCancellazione= controllo.get_userCancellazione();
		_dataCreazione = controllo.get_dataCreazione();
		_dataModifica = controllo.get_dataModifica();
		_dataCancellazione = controllo.get_dataCancellazione();
		_flagCancellato= controllo.get_flagCancellato();
		_flagValido= controllo.get_flagValido();
	}
	
	
	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return Controllo
	 */
	public Controllo convertToEntity() {
		Controllo controllo = new Controllo();		
		controllo.set_id(_id);
		controllo.set_idControllo(_idControllo);
		controllo.set_anno(_anno);
		controllo.set_campagna(_campagna);
		controllo.set_descrIT(_descrIT);
		controllo.set_descrDE(_descrDE);
		controllo.set_note(_note);
		controllo.set_dataCreazione(_dataCreazione);
		controllo.set_userCreazione(_userCreazione);
		controllo.set_dataModifica(_dataModifica);
		controllo.set_userModifica(_userModifica);
		controllo.set_dataCancellazione(_dataCancellazione);
		controllo.set_userCancellazione(_userCancellazione);
		controllo.set_flagCancellato(_flagCancellato);
		controllo.set_flagValido(_flagValido);
		
		return controllo;
	}
	
	
	@Override
	public String toString() {
		StringBuilder strBilder = new StringBuilder();

		strBilder.append("id: ");
		strBilder.append(this._id);
		strBilder.append("\n");
		strBilder.append("idControllo: ");
		strBilder.append(this._idControllo);
		strBilder.append("\n");
		strBilder.append("anno: ");
		strBilder.append(this._anno);
		strBilder.append("\n");
		strBilder.append("campagna: ");
		strBilder.append(this._campagna);
		strBilder.append("\n");
		strBilder.append("descrIT: ");
		strBilder.append(this._descrIT);
		strBilder.append("\n");
		strBilder.append("descrDE: ");
		strBilder.append(this._descrDE);
		strBilder.append("\n");
		strBilder.append("dataCreazione: ");
		strBilder.append(this._dataCreazione);
		strBilder.append("\n");
		strBilder.append("userCreazione: ");
		strBilder.append(this._userCreazione);
		strBilder.append("\n");
		strBilder.append("dataModifica: ");
		strBilder.append(this._dataModifica);
		strBilder.append("\n");
		strBilder.append("userModifica: ");
		strBilder.append(this._userModifica);
		strBilder.append("\n");
		strBilder.append("dataCancellazione: ");
		strBilder.append(this._dataCancellazione);
		strBilder.append("\n");
		strBilder.append("userCancellazione: ");
		strBilder.append(this._userCancellazione);
		strBilder.append("\n");
		strBilder.append("flagCancellato: ");
		strBilder.append(this._flagCancellato);
		strBilder.append("\n");
		strBilder.append("flagValido: ");
		strBilder.append(this._flagValido);
		strBilder.append("\n");
		
		return strBilder.toString();
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

	public Long get_idControllo() {
		return _idControllo;
	}

	public void set_idControllo(Long _idControllo) {
		this._idControllo = _idControllo;
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

	public String get_note() {
		return _note;
	}

	public void set_note(String _note) {
		this._note = _note;
	}
}
