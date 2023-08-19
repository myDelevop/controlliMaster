package it.bz.prov.controlli.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.bz.prov.controlli.entities.Gruppo;
import it.bz.prov.controlli.entities.Utente;
import it.bz.prov.controlli.entities.UtenteGruppo;

public class UtenteGruppoBO implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long _id;

	@JsonProperty("idRel")
	private Long _idRel;
	
	@JsonProperty("utente")
	private UtenteBO _utente;

	@JsonProperty("gruppo")
	private GruppoBO _gruppo;
	
	@JsonProperty("dataCancellazione")
	private Date _dataCancellazione;
	
	@JsonProperty("dataCreazione")
	private Date _dataCreazione;
	
	@JsonProperty("dataModifica")
	private Date _dataModifica;
	
	@JsonProperty("flagCancellato")
	private Boolean _flagCancellato;
	
	@JsonProperty("flagValido")
	private Boolean _flagValido;
	
	@JsonProperty("userCancellazione")
	private String _userCancellazione;
	
	@JsonProperty("userCreazione")
	private String _userCreazione;
	
	@JsonProperty("userModifica")
	private String _userModifica;

	
	public UtenteGruppoBO() {
		this._id = 0L;
		this._idRel = 0L;
		this._utente = new UtenteBO();
		this._gruppo = new GruppoBO();
		this._dataCancellazione = null;
		this._dataCreazione = null;
		this._dataModifica = null;
		this._flagCancellato = false;
		this._flagValido = false;
		this._userCancellazione = "";
		this._userCreazione = "";
		this._userModifica = "";
	}

	public UtenteGruppoBO(UtenteGruppo entity) {
		this._utente = new UtenteBO();
		this._gruppo = new GruppoBO();
		
		if(entity.get_id() != null)
			this.set_id(entity.get_id());
		if(entity.get_idRel() != null)
			this.set_idRel(entity.get_idRel());
		if(entity.get_dataCancellazione() != null)
			this.set_dataCancellazione(new Timestamp(entity.get_dataCancellazione().getTime()));
		if(entity.get_dataCreazione() != null)
			this.set_dataCreazione(new Timestamp(entity.get_dataCreazione().getTime()));
		if(entity.get_dataModifica() != null)
			this.set_dataModifica(new Timestamp(entity.get_dataModifica().getTime()));
		if(entity.get_flagCancellato() != null && entity.get_flagCancellato() == 1) {
			this.set_flagCancellato(true);
		} else {
			this.set_flagCancellato(false);
		}
		if(entity.get_flagValido() != null && entity.get_flagValido() == 1) {
			this.set_flagValido(true);
		} else {
			this.set_flagValido(false);
		}
		if(entity.get_userCancellazione() != null)
			this.set_userCancellazione(entity.get_userCancellazione());
		if(entity.get_userCreazione() != null)
			this.set_userCreazione(entity.get_userCreazione());
		if(entity.get_userModifica() != null)
			this.set_userModifica(entity.get_userModifica());
	}

	
	public UtenteGruppoBO(UtenteGruppo entity, Utente dettagliUtente, Gruppo dettagliGruppo) {
		this(entity);
		
		if(dettagliUtente != null)
			this._utente = new UtenteBO(dettagliUtente);
		if(dettagliGruppo != null) 
			this._gruppo = new GruppoBO(dettagliGruppo);

	}

	public UtenteGruppo convertToEntity() {
		UtenteGruppo utenteGruppoEntity = new UtenteGruppo();

		if(this.get_id() != null)
			utenteGruppoEntity.set_id(this.get_id());
		if(this.get_idRel() != null)
			utenteGruppoEntity.set_idRel(this.get_idRel());
		if(this.get_gruppo().get_idGruppo() != null)
			utenteGruppoEntity.set_idGruppo(this.get_gruppo().get_idGruppo());
		if(this.get_utente().get_idUtente() != null)
			utenteGruppoEntity.set_idUtente(this.get_utente().get_idUtente());
		if(this.get_dataCancellazione() != null)
			utenteGruppoEntity.set_dataCancellazione(new Timestamp(this.get_dataCancellazione().getTime()));
		if(this.get_dataCreazione() != null)
			utenteGruppoEntity.set_dataCreazione(new Timestamp(this.get_dataCreazione().getTime()));
		if(this.get_dataModifica() != null)
			utenteGruppoEntity.set_dataModifica(new Timestamp(this.get_dataModifica().getTime()));
		if(this.get_flagCancellato() != null && this.get_flagCancellato()) {
			utenteGruppoEntity.set_flagCancellato(1);
		} else {
			utenteGruppoEntity.set_flagCancellato(0);
		}
		if(this.get_flagValido() != null && this.get_flagValido()) {
			utenteGruppoEntity.set_flagValido(1);
		} else {
			utenteGruppoEntity.set_flagValido(0);
		}
		if(this.get_userCancellazione() != null)
			utenteGruppoEntity.set_userCancellazione(this.get_userCancellazione());
		if(this.get_userCreazione() != null)
			utenteGruppoEntity.set_userCreazione(this.get_userCreazione());
		if(this.get_userModifica() != null)
			utenteGruppoEntity.set_userModifica(this.get_userModifica());
		
		return utenteGruppoEntity;
	}


	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public Long get_idRel() {
		return _idRel;
	}

	public void set_idRel(Long _idRel) {
		this._idRel = _idRel;
	}

	public UtenteBO get_utente() {
		return _utente;
	}

	public void set_utente(UtenteBO _utente) {
		this._utente = _utente;
	}

	public GruppoBO get_gruppo() {
		return _gruppo;
	}

	public void set_gruppo(GruppoBO _gruppo) {
		this._gruppo = _gruppo;
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