package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.AziendaStazForControllore;

public class AziendaStazForControlloreBO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idAziStazForContr")
	private Long _idAziStazForContr;
	
	@JsonProperty("idAziStazFor")
	private Long _idAziStazFor;
	
	@JsonProperty("idContrStazFor")
	private Long _idContrStazFor;
	
	@JsonProperty("annoCampagna")
	private Integer _annoCampagna;
	
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
	
	/* campi aggiuntivi rispetto ad Entity*/
	@JsonProperty("flag_Change")
	private Integer _flag_Change; /* 1 se oggetto modificato, 0 altrimenti */
	
	
	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/
	
	/**
	 * costruttore
	 */
	public AziendaStazForControlloreBO() {
		_note = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}

	
	/**
	 * Costruttore con parametri
	 * @param aziendaStazForControllore é l'oggetto entity
	 */
	public AziendaStazForControlloreBO(AziendaStazForControllore aziendaStazForControllore) {
		_id = aziendaStazForControllore.get_id();
		_idAziStazForContr = aziendaStazForControllore.get_idAziStazForContr();
		_idAziStazFor = aziendaStazForControllore.get_idAziStazFor();
		_idContrStazFor = aziendaStazForControllore.get_idContrStazFor();
		_annoCampagna = aziendaStazForControllore.get_annoCampagna();
		_note = aziendaStazForControllore.get_note();
		_dataCreazione = aziendaStazForControllore.get_dataCreazione();
		_userCreazione = aziendaStazForControllore.get_userCreazione();
		_dataModifica = aziendaStazForControllore.get_dataModifica();
		_userModifica = aziendaStazForControllore.get_userModifica();
		_dataCancellazione = aziendaStazForControllore.get_dataCancellazione();
		_userCancellazione = aziendaStazForControllore.get_userCancellazione();
		_flagCancellato = aziendaStazForControllore.get_flagCancellato();
		_flagValido = aziendaStazForControllore.get_flagValido();
	}
	

	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return AziendaStazForControllore
	 */
	public AziendaStazForControllore convertToEntity() {
		AziendaStazForControllore aziStazForContr = new AziendaStazForControllore();
		
		aziStazForContr.set_id(_id);
		aziStazForContr.set_idAziStazForContr(_idAziStazForContr);
		aziStazForContr.set_idAziStazFor(_idAziStazFor);
		aziStazForContr.set_idContrStazFor(_idContrStazFor);
		aziStazForContr.set_annoCampagna(_annoCampagna);
		aziStazForContr.set_note(_note);
		aziStazForContr.set_dataCreazione(_dataCreazione);
		aziStazForContr.set_userCreazione(_userCreazione);
		aziStazForContr.set_dataModifica(_dataModifica);
		aziStazForContr.set_userModifica(_userModifica);
		aziStazForContr.set_dataCancellazione(_dataCancellazione);
		aziStazForContr.set_userCancellazione(_userCancellazione);
		aziStazForContr.set_flagCancellato(_flagCancellato);
		aziStazForContr.set_flagValido(_flagValido);
		
		return aziStazForContr;
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

	public Long get_idAziStazForContr() {
		return _idAziStazForContr;
	}

	public void set_idAziStazForContr(Long _idAziStazForContr) {
		this._idAziStazForContr = _idAziStazForContr;
	}

	public Long get_idAziStazFor() {
		return _idAziStazFor;
	}

	public void set_idAziStazFor(Long _idAziStazFor) {
		this._idAziStazFor = _idAziStazFor;
	}

	public Long get_idContrStazFor() {
		return _idContrStazFor;
	}

	public void set_idContrStazFor(Long _idContrStazFor) {
		this._idContrStazFor = _idContrStazFor;
	}

	public Integer get_annoCampagna() {
		return _annoCampagna;
	}

	public void set_annoCampagna(Integer _annoCampagna) {
		this._annoCampagna = _annoCampagna;
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


	public Integer get_flag_Change() {
		return _flag_Change;
	}


	public void set_flag_Change(Integer _flag_Change) {
		this._flag_Change = _flag_Change;
	}


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}
}
