package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Azienda;
import it.bz.prov.controlli.entities.AziendaStazioneForestale;
import it.bz.prov.controlli.entities.StazioneForestale;

/**
 * Oggetto BO che rappresenta la relazione tra un'azienda e la stazione forestale incaricata di fare il controllo
 * @author bpettazzoni
 *
 */
public class AziendaStazioneForestaleBO implements Serializable {
	private static final long serialVersionUID = 1L;


	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idAziStazFor")
	private Long _idAziStazFor;
	
	@JsonProperty("azienda")
	private AziendaBO _azienda;
	
	@JsonProperty("stazioneForestale")
	private StazioneForestaleBO _stazioneForestale;
	
	@JsonProperty("campagna")
	private Integer _campagna;
	
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
	public AziendaStazioneForestaleBO() {
		_id = 0L;
		_idAziStazFor = 0L;
		_azienda = new AziendaBO();
		_stazioneForestale = new StazioneForestaleBO();
		_note = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
		_flag_Change = 0;
	}
	
	/**
	 * Costruttore con parametri
	 * @param aziendaStazioneForestale é l'oggetto entity
	 */
	public AziendaStazioneForestaleBO(AziendaStazioneForestale aziendaStazioneForestale) {
		_azienda = new AziendaBO();
		_stazioneForestale = new StazioneForestaleBO();
		_id = aziendaStazioneForestale.get_id();
		_idAziStazFor = aziendaStazioneForestale.get_idAziStazFor();
		set_campagna(aziendaStazioneForestale.get_campagna());
		_note = aziendaStazioneForestale.get_note();
		_dataCreazione = aziendaStazioneForestale.get_dataCreazione();
		_userCreazione = aziendaStazioneForestale.get_userCreazione();
		_dataModifica = aziendaStazioneForestale.get_dataModifica();
		_userModifica = aziendaStazioneForestale.get_userModifica();
		_dataCancellazione = aziendaStazioneForestale.get_dataCancellazione();
		_userCancellazione = aziendaStazioneForestale.get_userCancellazione();
		_flagCancellato = aziendaStazioneForestale.get_flagCancellato();
		_flagValido = aziendaStazioneForestale.get_flagValido();
		_flag_Change=0;
	}
	
	public AziendaStazioneForestaleBO(AziendaStazioneForestale entity, Azienda dettagliAzienda, StazioneForestale dettagliStazFor) {
		this(entity);
		
		if(dettagliAzienda != null)
			this._azienda = new AziendaBO(dettagliAzienda);
		if(dettagliStazFor != null) 
			this._stazioneForestale = new StazioneForestaleBO(dettagliStazFor);
	}
	
	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return AziendaStazioneForestale
	 */
	public AziendaStazioneForestale convertToEntity() {
		AziendaStazioneForestale aziStazFor = new AziendaStazioneForestale();
		
		aziStazFor.set_id(_id);
		aziStazFor.set_idAziStazFor(_idAziStazFor);
		if(this.get_azienda().get_idAzienda() != null)
			aziStazFor.set_idAzienda(this.get_azienda().get_idAzienda());
		if(this.get_stazioneForestale().get_idStazioneForestale() != null)
			aziStazFor.set_idStazioneForestale(this.get_stazioneForestale().get_idStazioneForestale());
		aziStazFor.set_campagna(get_campagna());
		aziStazFor.set_note(_note);
		aziStazFor.set_dataCreazione(_dataCreazione);
		aziStazFor.set_userCreazione(_userCreazione);
		aziStazFor.set_dataModifica(_dataModifica);
		aziStazFor.set_userModifica(_userModifica);
		aziStazFor.set_dataCancellazione(_dataCancellazione);
		aziStazFor.set_userCancellazione(_userCancellazione);
		aziStazFor.set_flagCancellato(_flagCancellato);
		aziStazFor.set_flagValido(_flagValido);
		
		return aziStazFor;
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




	public Long get_idAziStazFor() {
		return _idAziStazFor;
	}




	public void set_idAziStazFor(Long _idAziStazFor) {
		this._idAziStazFor = _idAziStazFor;
	}




	public AziendaBO get_azienda() {
		return _azienda;
	}
	
	

	public void set_azienda(AziendaBO _azienda) {
		this._azienda = _azienda;
	}

	
	
	public StazioneForestaleBO get_stazioneForestale() {
		return _stazioneForestale;
	}

	public void set_stazioneForestale(StazioneForestaleBO _stazioneForestale) {
		this._stazioneForestale = _stazioneForestale;
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

	public Integer get_campagna() {
		return _campagna;
	}

	public void set_campagna(Integer _campagna) {
		this._campagna = _campagna;
	}
}
