package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Azienda
 *
 */
@Entity
@Table(name = "DWH_AZIENDA")
@NamedQuery(name="Azienda.findAll", query="SELECT a FROM Azienda a")
public class Azienda implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "ID")
	private Long _id;

	@Column(name = "ID_AZIENDA")
	private Long _idAzienda;
	
	@Column(name = "CAMPAGNA")
	private int _campagna;
	
	@Column(name = "CUAA")
	private String _cuaa;
	
	@Column(name = "RAGIONE_SOCIALE")
	private String _ragioneSociale;
	
	@Column(name = "NOME_COGNOME")
	private String _nomeCognome;
	
	@Column(name = "INDIRIZZO")
	private String _indirizzo;
	
	@Column(name = "CAP")
	private int _cap;

	@Column(name = "COMUNE")
	private String _comune;

	@Column(name = "LOCALITA")
	private String _localita;

	@Column(name = "LINGUA_PREFERITA_COD")
	private String _linguaPreferitaCod;

	@Column(name = "LINGUA_PREFERITA_DESCR_IT")
	private String _linguaPreferitaDescrIT;

	@Column(name = "LINGUA_PREFERITA_DESCR_DE")
	private String _linguaPreferitaDescrDE;	
	
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
	public Azienda() {
		_id=0L;
		_idAzienda=0L;
		_campagna=0;
		_cuaa="";
		_ragioneSociale="";
		_nomeCognome="";
		_indirizzo="";
		_cap=0;
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
	
	/**************************************************/
	/*				GETTER E SETTER					  */
	/**************************************************/


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
