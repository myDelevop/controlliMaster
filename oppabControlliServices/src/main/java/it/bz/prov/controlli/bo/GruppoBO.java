package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Gruppo;


public class GruppoBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long _id;
	
	@JsonProperty("idGruppo")
	private Long _idGruppo;

	@JsonProperty("dataCancellazione")
	private Date _dataCancellazione;
	
	@JsonProperty("dataCreazione")
	private Date _dataCreazione;
	
	@JsonProperty("dataModifica")
	private Date _dataModifica;
	
	@JsonProperty("descrizioneDE")
	private String _descrizioneGruppoDe;
	
	@JsonProperty("descrizioneIT")
	private String _descrizioneGruppoIt;
	
	@JsonProperty("flagCancellato")
	private Boolean _flagCancellato;
	
	@JsonProperty("flagValido")
	private Boolean _flagValido;
	
	@JsonProperty("nome")
	private String _nomeGruppo;
	
	@JsonProperty("userCancellazione")
	private String _userCancellazione;
	
	@JsonProperty("userCreazione")
	private String _userCreazione;
	
	@JsonProperty("userModifica")
	private String _userModifica;
	

	public GruppoBO() {
		this._id = 0L;
		this._dataCancellazione = null;
		this._dataCreazione = null;
		this._dataModifica = null;
		this._descrizioneGruppoDe = "";
		this._descrizioneGruppoIt = "";
		this._flagCancellato = false;
		this._flagValido = false;
		this._idGruppo = 0L;
		this._nomeGruppo = "";
		this._userCancellazione = "";
		this._userCreazione = "";
		this._userModifica = "";
	}
	

	public GruppoBO(Long _id, Long _idGruppo, Date _dataCancellazione, Date _dataCreazione, Date _dataModifica,
			String _descrizioneGruppoDe, String _descrizioneGruppoIt, Boolean _flagCancellato, String _nomeGruppo,
			String _userCancellazione, String _userCreazione, String _userModifica) {
		super();
		this._id = _id;
		this._idGruppo = _idGruppo;
		this._dataCancellazione = _dataCancellazione;
		this._dataCreazione = _dataCreazione;
		this._dataModifica = _dataModifica;
		this._descrizioneGruppoDe = _descrizioneGruppoDe;
		this._descrizioneGruppoIt = _descrizioneGruppoIt;
		this._flagCancellato = _flagCancellato;
		this._nomeGruppo = _nomeGruppo;
		this._userCancellazione = _userCancellazione;
		this._userCreazione = _userCreazione;
		this._userModifica = _userModifica;
	}
	
	public GruppoBO(Gruppo gruppoEntity) {
		
		if(gruppoEntity.get_id() != null)
			this.set_id(gruppoEntity.get_id());
		if(gruppoEntity.get_idGruppo() != null)
			this.set_idGruppo(gruppoEntity.get_idGruppo());
		if(gruppoEntity.get_descrizioneGruppoIt() != null)
			this.set_descrizioneGruppoIt(gruppoEntity.get_descrizioneGruppoIt());
		if(gruppoEntity.get_descrizioneGruppoDe() != null)
			this.set_descrizioneGruppoDe(gruppoEntity.get_descrizioneGruppoDe());
		if(gruppoEntity.get_dataCancellazione() != null)
			this.set_dataCancellazione(new Timestamp(gruppoEntity.get_dataCancellazione().getTime()));
		if(gruppoEntity.get_dataCreazione() != null)
			this.set_dataCreazione(new Timestamp(gruppoEntity.get_dataCreazione().getTime()));
		if(gruppoEntity.get_dataModifica() != null)
			this.set_dataModifica(new Timestamp(gruppoEntity.get_dataModifica().getTime()));
		if(gruppoEntity.get_flagCancellato() != null && gruppoEntity.get_flagCancellato() == 1) {
			this.set_flagCancellato(true);
		} else {
			this.set_flagCancellato(false);
		}
		if(gruppoEntity.get_flagValido() != null && gruppoEntity.get_flagValido() == 1) {
			this.set_flagValido(true);
		} else {
			this.set_flagValido(false);
		}
		if(gruppoEntity.get_nomeGruppo() != null)
			this.set_nomeGruppo(gruppoEntity.get_nomeGruppo());
		if(gruppoEntity.get_userCancellazione() != null)
			this.set_userCancellazione(gruppoEntity.get_userCancellazione());
		if(gruppoEntity.get_userCreazione() != null)
			this.set_userCreazione(gruppoEntity.get_userCreazione());
		if(gruppoEntity.get_userModifica() != null)
			this.set_userModifica(gruppoEntity.get_userModifica());
	}
	
	public Gruppo convertToEntity() {
		Gruppo gruppoEntity = new Gruppo();

		if(this.get_id() != null)
			gruppoEntity.set_id(this.get_id());
		if(this.get_idGruppo() != null)
			gruppoEntity.set_idGruppo(this.get_idGruppo());
		if(this.get_descrizioneGruppoIt() != null)
			gruppoEntity.set_descrizioneGruppoIt(this.get_descrizioneGruppoIt());
		if(this.get_descrizioneGruppoDe() != null)
			gruppoEntity.set_descrizioneGruppoDe(this.get_descrizioneGruppoDe());
		if(this.get_dataCancellazione() != null)
			gruppoEntity.set_dataCancellazione(new Timestamp(this.get_dataCancellazione().getTime()));
		if(this.get_dataCreazione() != null)
			gruppoEntity.set_dataCreazione(new Timestamp(this.get_dataCreazione().getTime()));
		if(this.get_dataModifica() != null)
			gruppoEntity.set_dataModifica(new Timestamp(this.get_dataModifica().getTime()));
		if(this.get_flagCancellato() != null && this.get_flagCancellato()) {
			gruppoEntity.set_flagCancellato(1);
		} else {
			gruppoEntity.set_flagCancellato(0);
		}	
		if(this.get_flagValido() != null && this.get_flagValido()) {
			gruppoEntity.set_flagValido(1);
		} else {
			gruppoEntity.set_flagValido(0);
		}
		if(this.get_nomeGruppo() != null)
			gruppoEntity.set_nomeGruppo(this.get_nomeGruppo());
		if(this.get_userCancellazione() != null)
			gruppoEntity.set_userCancellazione(this.get_userCancellazione());
		if(this.get_userCreazione() != null)
			gruppoEntity.set_userCreazione(this.get_userCreazione());
		if(this.get_userModifica() != null)
			gruppoEntity.set_userModifica(this.get_userModifica());
		
		return gruppoEntity;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id= _id;
	}
	
	public Long get_idGruppo() {
		return _idGruppo;
	}

	public void set_idGruppo(Long _idGruppo) {
		this._idGruppo = _idGruppo;
	}
	public Date get_dataCancellazione() {
		return _dataCancellazione;
	}

	public void set_dataCancellazione(Date _dataCancellazione) {
		this._dataCancellazione = _dataCancellazione;
	}

	public Date get_dataCreazione() {
		return _dataCreazione;
	}

	public void set_dataCreazione(Date _dataCreazione) {
		this._dataCreazione = _dataCreazione;
	}
	
	public Date get_dataModifica() {
		return _dataModifica;
	}

	public void set_dataModifica(Date _dataModifica) {
		this._dataModifica = _dataModifica;
	}

	public String get_descrizioneGruppoDe() {
		return _descrizioneGruppoDe;
	}

	public void set_descrizioneGruppoDe(String _descrizioneGruppoDe) {
		this._descrizioneGruppoDe = _descrizioneGruppoDe;
	}

	public String get_descrizioneGruppoIt() {
		return _descrizioneGruppoIt;
	}

	public void set_descrizioneGruppoIt(String _descrizioneGruppoIt) {
		this._descrizioneGruppoIt = _descrizioneGruppoIt;
	}

	public Boolean get_flagCancellato() {
		return _flagCancellato;
	}

	public void set_flagCancellato(Boolean _flagCancellato) {
		this._flagCancellato = _flagCancellato;
	}

	public Boolean get_flagValido() {
		return _flagValido;
	}

	public void set_flagValido(Boolean _flagValido) {
		this._flagValido = _flagValido;
	}

	public String get_nomeGruppo() {
		return _nomeGruppo;
	}

	public void set_nomeGruppo(String _nomeGruppo) {
		this._nomeGruppo = _nomeGruppo;
	}

	public String get_userCancellazione() {
		return _userCancellazione;
	}

	public void set_userCancellazione(String _userCancellazione) {
		this._userCancellazione = _userCancellazione;
	}

	public String get_userCreazione() {
		return _userCreazione;
	}

	public void set_userCreazione(String _userCreazione) {
		this._userCreazione = _userCreazione;
	}

	public String get_userModifica() {
		return _userModifica;
	}

	public void set_userModifica(String _userModifica) {
		this._userModifica = _userModifica;
	}

	
}