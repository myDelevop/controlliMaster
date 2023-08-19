package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.StazioneForestale;

/**
 * Oggetto BO che rappresenta la stazione forestale
 * @author bpettazzoni
 *
 */

public class StazioneForestaleBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idStazioneForestale")
	private Long _idStazioneForestale;
	
	@JsonProperty("numero")
	private String _numero;
	
	@JsonProperty("ispettoratoForestaleIT")
	private String _ispettoratoForestaleIT;
	
	@JsonProperty("ispettoratoForestaleDE")
	private String _ispettoratoForestaleDE;

	
	@JsonProperty("nome")
	private String _nome;
	
	@JsonProperty("descrIT")
	private String _descrIT;
	
	@JsonProperty("descrDE")
	private String _descrDE;
	
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
	 * costruttore
	 */
	public StazioneForestaleBO() {
		_id=0L;
		_idStazioneForestale=0L;
		_numero = "";
		_ispettoratoForestaleIT = "";
		_ispettoratoForestaleDE = "";
		_nome="";
		_descrIT="";
		_descrDE = "";
		_annoValiditaInizio = 0;
		_annoValiditaFine = 0;
		_note = "";
		_userCreazione = "";
		_userModifica = "";
		_userCancellazione = "";
		_flagCancellato= 0;
		_flagValido=0;
	}

	/**
	 * Costruttore con parametri
	 * @param stazioneForestale é l'oggetto entity
	 */
	public StazioneForestaleBO(StazioneForestale stazioneForestale) {
		_id = stazioneForestale.get_id();
		_idStazioneForestale = stazioneForestale.get_idStazioneForestale();
		_numero = stazioneForestale.get_numero();
		_ispettoratoForestaleIT = stazioneForestale.get_ispettoratoForestaleIT();
		_ispettoratoForestaleDE = stazioneForestale.get_ispettoratoForestaleDE();
		_nome= stazioneForestale.get_nome();
		_descrIT = stazioneForestale.get_descrIT();
		_descrDE = stazioneForestale.get_descrDE();
		_annoValiditaInizio = stazioneForestale.get_annoValiditaInizio();
		_annoValiditaFine = stazioneForestale.get_annoValiditaFine();
		_note = stazioneForestale.get_note();
		_dataCreazione = stazioneForestale.get_dataCreazione();
		_userCreazione = stazioneForestale.get_userCreazione();
		_dataModifica = stazioneForestale.get_dataModifica();
		_userModifica = stazioneForestale.get_userModifica();
		_dataCreazione = stazioneForestale.get_dataCancellazione();
		_userCancellazione = stazioneForestale.get_userCancellazione();
		_flagCancellato = stazioneForestale.get_flagCancellato();
		_flagValido = stazioneForestale.get_flagValido();
	}


	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/
	
	
	/**
	 * Converte l'oggetto BO in entity
	 * @return StazioneForestale
	 */
	public StazioneForestale convertToEntity() {
		StazioneForestale entity = new StazioneForestale();
		
		entity.set_id(_id);
		entity.set_idStazioneForestale(_idStazioneForestale);
		entity.set_numero(_numero);
		entity.set_ispettoratoForestaleIT(_ispettoratoForestaleIT);
		entity.set_ispettoratoForestaleDE(_ispettoratoForestaleDE);
		entity.set_nome(_nome);
		entity.set_descrIT(_descrIT);
		entity.set_descrDE(_descrDE);
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
		strBilder.append("idStazioneForestale: ");
		strBilder.append(this._idStazioneForestale);
		strBilder.append("\n");
		strBilder.append("numero: ");
		strBilder.append(this._numero);
		strBilder.append("\n");
		strBilder.append("ispettoratoForestaleDE: ");
		strBilder.append(this._ispettoratoForestaleIT);
		strBilder.append("\n");
		strBilder.append("ispettoratoForestaleIT: ");
		strBilder.append(this._ispettoratoForestaleDE);
		strBilder.append("\n");
		strBilder.append("nome: ");
		strBilder.append(this._nome);
		strBilder.append("\n");
		strBilder.append("descrIT: ");
		strBilder.append(this._descrIT);
		strBilder.append("\n");
		strBilder.append("descrDE: ");
		strBilder.append(this._descrDE);
		strBilder.append("\n");
		strBilder.append("annoValiditaInizio: ");
		strBilder.append(this._annoValiditaInizio);
		strBilder.append("\n");
		strBilder.append("annoValiditaFine: ");
		strBilder.append(this._annoValiditaFine);
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



	public Long get_idStazioneForestale() {
		return _idStazioneForestale;
	}



	public void set_idStazioneForestale(Long _idStazioneForestale) {
		this._idStazioneForestale = _idStazioneForestale;
	}

	public String get_numero() {
		return _numero;
	}

	public void set_numero(String _numero) {
		this._numero = _numero;
	}

	public String get_ispettoratoForestaleIT() {
		return _ispettoratoForestaleIT;
	}

	public void set_ispettoratoForestaleIT(String _ispettoratoForestaleIT) {
		this._ispettoratoForestaleIT = _ispettoratoForestaleIT;
	}

	public String get_ispettoratoForestaleDE() {
		return _ispettoratoForestaleDE;
	}

	public void set_ispettoratoForestaleDE(String _ispettoratoForestaleDE) {
		this._ispettoratoForestaleDE = _ispettoratoForestaleDE;
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

	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
	}

	public String get_note() {
		return _note;
	}

	public void set_note(String _note) {
		this._note = _note;
	}
}
