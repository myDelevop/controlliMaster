package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Campione
 *
 */
@Entity
@Table(name = "APP_ANAGRAFICA")
public class Campione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Long _id;
	
	@Column(name = "ID_CAMPIONE")
	private Long _idCampione;
	
	@Column(name = "CUAA")
	private String _cuaa;

	@Column(name = "CAMPAGNA")
	private int _campagna;
	
	@Column(name = "ID_DOMANDA")
	private Long _idDomanda;
	
	@Column(name = "NUMERO_DOMANDA")
	private String _numeroDomanda;

	@Column(name = "CATEGORIA_CAMPIONE")
	private String _categoriaCampione;
	
	@Column(name = "TIPO_CAMPIONE")
	private String _tipoCampione;
	
	@Column(name = "FLAG_CAMPIONE")
	private Integer _flagCampione;
	
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
	 * Costruttore
	 */
	public Campione() {
		_id=0L;
		_idCampione=0L;
		_idDomanda=0L;
		_cuaa="";
		_numeroDomanda="";
		_categoriaCampione="";
		_tipoCampione="";
		_flagCampione=0;		
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

	public Long get_idCampione() {
		return _idCampione;
	}

	public void set_idCampione(Long _idCampione) {
		this._idCampione = _idCampione;
	}

	public String get_cuaa() {
		return _cuaa;
	}

	public void set_cuaa(String _cuaa) {
		this._cuaa = _cuaa;
	}

	public int get_campagna() {
		return _campagna;
	}

	public void set_campagna(int _campagna) {
		this._campagna = _campagna;
	}

	public Long get_idDomanda() {
		return _idDomanda;
	}

	public void set_idDomanda(Long _idDomanda) {
		this._idDomanda = _idDomanda;
	}

	public String get_numeroDomanda() {
		return _numeroDomanda;
	}

	public void set_numeroDomanda(String _numeroDomanda) {
		this._numeroDomanda = _numeroDomanda;
	}

	public String get_categoriaCampione() {
		return _categoriaCampione;
	}

	public void set_categoriaCampione(String _categoriaCampione) {
		this._categoriaCampione = _categoriaCampione;
	}

	public String get_tipoCampione() {
		return _tipoCampione;
	}

	public void set_tipoCampione(String _tipoCampione) {
		this._tipoCampione = _tipoCampione;
	}

	public Integer get_flagCampione() {
		return _flagCampione;
	}

	public void set_flagCampione(Integer _flagCampione) {
		this._flagCampione = _flagCampione;
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
