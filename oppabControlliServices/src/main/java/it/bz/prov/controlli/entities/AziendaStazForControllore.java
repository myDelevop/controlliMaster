package it.bz.prov.controlli.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AziendaStazForControllore
 *
 */
@Entity
@Table(name = "AZIENDA_STAZ_FOR_CONTROLLORE")
@NamedQuery(name="AziendaStazForControllore.findAll", query="SELECT a FROM AziendaStazForControllore a")
public class AziendaStazForControllore implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "ID")
	private Long _id;

	@Column(name = "ID_AZI_STAZ_FOR_CONTR")
	private Long _idAziStazForContr;
	
	@Column(name = "ID_AZI_STAZ_FOR")
	private Long _idAziStazFor;
	
	@Column(name = "ID_CONTR_STAZ_FOR")
	private Long _idContrStazFor;
	
	@Column(name = "ANNO_CAMPAGNA")
	private Integer _annoCampagna;
	
	@Column(name = "NOTE")
	private String _note;
		
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
	public AziendaStazForControllore() {
		_id=0L;
		_idAziStazForContr=0L;
		_idAziStazFor=0L;
		_idContrStazFor=0L;
		_annoCampagna=0;	
		_note = "";
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

	public String get_note() {
		return _note;
	}

	public void set_note(String _note) {
		this._note = _note;
	}
   
}
