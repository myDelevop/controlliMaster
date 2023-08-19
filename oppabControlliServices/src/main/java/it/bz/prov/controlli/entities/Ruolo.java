package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Ruolo
 *
 */
@Entity
@Table(name = "RUOLI")
@NamedQuery(name="Ruolo.findAll", query="SELECT r FROM Ruolo r")
public class Ruolo implements Serializable {

    private static final long serialVersionUID = 460590603106473837L;

    @Id
    @Column(name = "ID")
    private Long _id;

    @Column(name = "ID_RUOLO")
    private Long _idRuolo;

    @Column(name = "TIPO")
    private String _tipo;

    @Column(name = "RUOLO")
    private String _ruolo;

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

	public Ruolo(){
		_id = 0L;
		_idRuolo = 0L;
		_tipo = "";
		_ruolo = "";
		_userCreazione = "";
		_userModifica = "";
		_userCancellazione = "";
		_flagCancellato = 0;
		_flagValido = 0;
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

	public Long get_idRuolo() {
		return _idRuolo;
	}

	public void set_idRuolo(Long _idRuolo) {
		this._idRuolo = _idRuolo;
	}

	public String get_tipo() {
		return _tipo;
	}

	public void set_tipo(String _tipo) {
		this._tipo = _tipo;
	}

	public String get_ruolo() {
		return _ruolo;
	}

	public void set_ruolo(String _ruolo) {
		this._ruolo = _ruolo;
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