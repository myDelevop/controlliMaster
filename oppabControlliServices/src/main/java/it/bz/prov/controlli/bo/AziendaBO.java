package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Azienda;


/**
 * Oggetto BO che rappresenta l'azienda
 * @author bpettazzoni
 *
 */
public class AziendaBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idAzienda")
	private Long _idAzienda;
	
	@JsonProperty("campagna")
	private int _campagna;
	
	@JsonProperty("cuaa")
	private String _cuaa;
	
	@JsonProperty("ragioneSociale")
	private String _ragioneSociale;
	
	@JsonProperty("nomeCognome")
	private String _nomeCognome;
	
	@JsonProperty("indirizzo")
	private String _indirizzo;
	
	@JsonProperty("cap")
	private int _cap;
	
	@JsonProperty("comune")
	private String _comune;
	
	@JsonProperty("localita")
	private String _localita;
	
	@JsonProperty("linguaPreferitaCod")
	private String _linguaPreferitaCod;
	
	@JsonProperty("linguaPreferitaDescrIT")
	private String _linguaPreferitaDescrIT;
	
	@JsonProperty("linguaPreferitaDescrDE")
	private String _linguaPreferitaDescrDE;
	
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
	public AziendaBO() {
		_campagna=0;
		_cuaa="";
		_ragioneSociale="";
		_nomeCognome="";
		_indirizzo="";
		_comune="";
		_localita="";
		_linguaPreferitaCod="";
		_linguaPreferitaDescrIT="";
		_linguaPreferitaDescrDE="";
		_userCreazione="";
		_userModifica="";
		_userCancellazione="";
		_flagCancellato=0;
		_flagValido=0;
	}
	
	/**
	 * Costruttore con parametri
	 * @param azienda é l'oggetto entity
	 */
	public AziendaBO(Azienda azienda) {
		_id = azienda.get_id();
		_idAzienda = azienda.get_idAzienda();
		_campagna = azienda.get_campagna();
		_cuaa= azienda.get_cuaa();
		_ragioneSociale= azienda.get_ragioneSociale();
		_nomeCognome= azienda.get_nomeCognome();
		_indirizzo= azienda.get_indirizzo();
		_cap = azienda.get_cap();
		_comune= azienda.get_comune();
		_localita= azienda.get_localita();
		_linguaPreferitaCod= azienda.get_linguaPreferitaCod();
		_linguaPreferitaDescrIT= azienda.get_linguaPreferitaDescrIT();
		_linguaPreferitaDescrDE= azienda.get_linguaPreferitaDescrDE();
		_userCreazione= azienda.get_userCreazione();
		_userModifica= azienda.get_userModifica();
		_userCancellazione= azienda.get_userCancellazione();
		_dataCreazione = azienda.get_dataCreazione();
		_dataModifica = azienda.get_dataModifica();
		_dataCancellazione = azienda.get_dataCancellazione();
		_flagCancellato= azienda.get_flagCancellato();
		_flagValido= azienda.get_flagValido();
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


	public Long get_idAzienda() {
		return _idAzienda;
	}


	public void set_idAzienda(Long _idAzienda) {
		this._idAzienda = _idAzienda;
	}


	public String get_cuaa() {
		return _cuaa;
	}


	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}


	public String get_ragioneSociale() {
		return _ragioneSociale;
	}


	public void set_ragioneSociale(String _ragioneSociale) {
		this._ragioneSociale = _ragioneSociale;
	}


	public String get_nomeCognome() {
		return _nomeCognome;
	}


	public void set_nomeCognome(String _nomeCognome) {
		this._nomeCognome = _nomeCognome;
	}


	public String get_indirizzo() {
		return _indirizzo;
	}


	public void set_indirizzo(String _indirizzo) {
		this._indirizzo = _indirizzo;
	}


	public int get_cap() {
		return _cap;
	}


	public void set_cap(int _cap) {
		this._cap = _cap;
	}


	public String get_comune() {
		return _comune;
	}


	public void set_comune(String _comune) {
		this._comune = _comune;
	}


	public String get_localita() {
		return _localita;
	}


	public void set_localita(String _localita) {
		this._localita = _localita;
	}


	public String get_linguaPreferitaCod() {
		return _linguaPreferitaCod;
	}


	public void set_linguaPreferitaCod(String _linguaPreferitaCod) {
		this._linguaPreferitaCod = _linguaPreferitaCod;
	}


	public String get_linguaPreferitaDescrIT() {
		return _linguaPreferitaDescrIT;
	}


	public void set_linguaPreferitaDescrIT(String _linguaPreferitaDescrIT) {
		this._linguaPreferitaDescrIT = _linguaPreferitaDescrIT;
	}


	public String get_linguaPreferitaDescrDE() {
		return _linguaPreferitaDescrDE;
	}


	public void set_linguaPreferitaDescrDE(String _linguaPreferitaDescrDE) {
		this._linguaPreferitaDescrDE = _linguaPreferitaDescrDE;
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

	public int get_campagna() {
		return _campagna;
	}

	public void set_campagna(int _campagna) {
		this._campagna = _campagna;
	}

}
