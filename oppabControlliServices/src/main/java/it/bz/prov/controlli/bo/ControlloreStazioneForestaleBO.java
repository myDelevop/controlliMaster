package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Controllore;
import it.bz.prov.controlli.entities.ControlloreStazioneForestale;
import it.bz.prov.controlli.entities.StazioneForestale;


/**
 * Oggetto BO che rappresenta la relazione tra la stazione forestale e il controllore
 * @author bpettazzoni
 *
 */
public class ControlloreStazioneForestaleBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idContrStazFor")
	private Long _idContrStazFor;
	
	@JsonProperty("controllore")
	private ControlloreBO _controllore;
	
	@JsonProperty("stazioneForestale")
	private StazioneForestaleBO _stazioneForestale;

	@JsonProperty("annoValiditaInizio")
	private Integer _annoValiditaInizio;
	
	@JsonProperty("annoValiditaFine")
	private Integer _annoValiditaFine;
	
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
	 * Costruttore
	 */
	public ControlloreStazioneForestaleBO() {
		_controllore = new ControlloreBO();
		_stazioneForestale = new StazioneForestaleBO();
		_annoValiditaInizio = 0;
		_annoValiditaFine = 0;
		_note = "";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	
	/**
	 * Costruttore con parametri
	 * @param controlloreStazioneForestale é l'oggetto entity
	 */
	public ControlloreStazioneForestaleBO(ControlloreStazioneForestale controlloreStazioneForestale) {
		_controllore = new ControlloreBO();
		_stazioneForestale = new StazioneForestaleBO();
		_id = controlloreStazioneForestale.get_id();
		_idContrStazFor = controlloreStazioneForestale.get_idContrStazFor();
		_annoValiditaInizio = controlloreStazioneForestale.get_annoValiditaInizio();
		_annoValiditaFine = controlloreStazioneForestale.get_annoValiditaFine();
		_note = controlloreStazioneForestale.get_note();
		_dataCreazione = controlloreStazioneForestale.get_dataCreazione();
		_userCreazione = controlloreStazioneForestale.get_userCreazione();
		_dataModifica = controlloreStazioneForestale.get_dataModifica();
		_userModifica = controlloreStazioneForestale.get_userModifica();
		_dataCancellazione = controlloreStazioneForestale.get_dataCancellazione();
		_userCancellazione = controlloreStazioneForestale.get_userCancellazione();
		_flagCancellato = controlloreStazioneForestale.get_flagCancellato();
		_flagValido = controlloreStazioneForestale.get_flagValido();
	}
	
	public ControlloreStazioneForestaleBO(ControlloreStazioneForestale entity, 
			Controllore dettagliControllore, StazioneForestale dettagliStazioneForestale) {
		this(entity);
		
		if(dettagliControllore != null)
			this._controllore = new ControlloreBO(dettagliControllore);
		if(dettagliStazioneForestale != null) 
			this._stazioneForestale = new StazioneForestaleBO(dettagliStazioneForestale);

	}

	
	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return ControlloreStazioneForestale
	 */
	public ControlloreStazioneForestale convertToEntity() {
		ControlloreStazioneForestale entity = new ControlloreStazioneForestale();
		
		entity.set_id(_id);
		entity.set_idContrStazFor(_idContrStazFor);
		entity.set_idControllore(this.get_controllore().get_idControllore());
		entity.set_idStazioneForestale(this.get_stazioneForestale().get_idStazioneForestale());
		entity.set_annoValiditaInizio(_annoValiditaInizio);
		entity.set_annoValiditaFine(_annoValiditaFine);
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
	
	
	@Override
	public String toString() {
		StringBuilder strBilder = new StringBuilder();

		strBilder.append("id: ");
		strBilder.append(this._id);
		strBilder.append("\n");
		strBilder.append("idContrStazFor: ");
		strBilder.append(this._idContrStazFor);
		strBilder.append("\n");
		strBilder.append("Controllore: ");
		strBilder.append(this.get_controllore().toString());
		strBilder.append("\n");
		strBilder.append("StazioneForestale: ");
		strBilder.append(this.get_stazioneForestale().toString());
		strBilder.append("\n");
		strBilder.append("annoValiditaInizio: ");
		strBilder.append(this._annoValiditaInizio);
		strBilder.append("\n");
		strBilder.append("annoValiditaFine: ");
		strBilder.append(this._annoValiditaFine);
		strBilder.append("\n");
		strBilder.append("note: ");
		strBilder.append(this._note);
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

	public Long get_idContrStazFor() {
		return _idContrStazFor;
	}

	public void set_idContrStazFor(Long _idContrStazFor) {
		this._idContrStazFor = _idContrStazFor;
	}


	public ControlloreBO get_controllore() {
		return _controllore;
	}


	public void set_controllore(ControlloreBO _controllore) {
		this._controllore = _controllore;
	}


	public StazioneForestaleBO get_stazioneForestale() {
		return _stazioneForestale;
	}


	public void set_stazioneForestale(StazioneForestaleBO _stazioneForestale) {
		this._stazioneForestale = _stazioneForestale;
	}
	

	public Integer get_annoValiditaInizio() {
		return _annoValiditaInizio;
	}


	public void set_annoValiditaInizio(Integer _annoValiditaInizio) {
		this._annoValiditaInizio = _annoValiditaInizio;
	}



	public Integer get_annoValiditaFine() {
		return _annoValiditaFine;
	}



	public void set_annoValiditaFine(Integer _annoValiditaFine) {
		this._annoValiditaFine = _annoValiditaFine;
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


	public String get_note() {
		return _note;
	}


	public void set_note(String _note) {
		this._note = _note;
	}

}
