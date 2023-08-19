package it.bz.prov.controlli.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


import it.bz.prov.controlli.entities.Utente;

public class UtenteBO {

	@JsonProperty("id")
	private Long _id;	

	@JsonProperty("idUtente")
	private Long _idUtente;	
	
	@JsonProperty("cognome")
	private String _cognome;
	
	@JsonProperty("dataCancellazione")
	private Date _dataCancellazione;
	
	@JsonProperty("dataCreazione")
	private Date _dataCreazione;
	
	@JsonProperty("dataModifica")
	private Date _dataModifica;
	
	@JsonProperty("dominio")
	private String _dominio;
	
	@JsonProperty("flagCancellato")
	private Boolean _flagCancellato;
	
	@JsonProperty("flagValido")
	private Boolean _flagValido;
	
	@JsonProperty("nome")
	private String _nome;
	
	@JsonProperty("userCancellazione")
	private String _userCancellazione;
	
	@JsonProperty("userCreazione")
	private String _userCreazione;
	
	@JsonProperty("userModifica")
	private String _userModifica;
	
	@JsonProperty("username")
	private String _username;

	@JsonProperty("email")
	private String _email;
	
	
	
	/***************************************************************/
	/*						COSTRUTTORI				   			   */
	/***************************************************************/

	/**
	 * costruttore
	 */
	public UtenteBO() {
		this._id = 0L;
		this._cognome = "";
		this._dataCancellazione = null;
		this._dataCreazione = null;
		this._dataModifica = null;
		this._dominio = "";
		this._flagCancellato = false;
		this._flagValido = false;
		this._idUtente = 0L;
		this._nome = "";
		this._userCancellazione = "";
		this._userCreazione = "";
		this._userModifica = "";
		this._username = "";
		this._email = "";
	}
	
	/**
	 * Costruttore con parametri
	 * @param _id
	 * @param _idUtente
	 * @param _cognome
	 * @param _dataCancellazione
	 * @param _dataCreazione
	 * @param _dataModifica
	 * @param _dominio
	 * @param _flagCancellato
	 * @param _nome
	 * @param _userCancellazione
	 * @param _userCreazione
	 * @param _userModifica
	 * @param _username
	 * @param _email
	 */
	public UtenteBO(long _id, Long _idUtente, String _cognome, Date _dataCancellazione, Date _dataCreazione,
			Date _dataModifica, String _dominio, Boolean _flagCancellato, String _nome, String _userCancellazione,
			String _userCreazione, String _userModifica, String _username, String _email) {
		super();
		this._id = _id;
		this._idUtente = _idUtente;
		this._cognome = _cognome;
		this._dataCancellazione = _dataCancellazione;
		this._dataCreazione = _dataCreazione;
		this._dataModifica = _dataModifica;
		this._dominio = _dominio;
		this._flagCancellato = _flagCancellato;
		this._nome = _nome;
		this._userCancellazione = _userCancellazione;
		this._userCreazione = _userCreazione;
		this._userModifica = _userModifica;
		this._username = _username;
		this._email = _email;
	}
	
	/**
	 * Costruttore con parametri
	 * @param utenteEntity
	 */
	public UtenteBO(Utente utenteEntity) {
		if(utenteEntity.get_id() != null)
			this.set_id(utenteEntity.get_id()); 
		if(utenteEntity.get_idUtente() != null)
			this.set_idUtente(utenteEntity.get_idUtente());
		if(utenteEntity.get_username() != null)
			this.set_username(utenteEntity.get_username());
		if(utenteEntity.get_nome() != null)
			this.set_nome(utenteEntity.get_nome());
		if(utenteEntity.get_cognome() != null)
			this.set_cognome(utenteEntity.get_cognome());
		if(utenteEntity.get_dominio() != null)
			this.set_dominio(utenteEntity.get_dominio());
		if(utenteEntity.get_email() != null)
			this.set_email(utenteEntity.get_email());
		if(utenteEntity.get_dataCreazione() != null)
			this.set_dataCreazione(new Timestamp(utenteEntity.get_dataCreazione().getTime()));
		if(utenteEntity.get_userCreazione() != null)
			this.set_userCreazione(utenteEntity.get_userCreazione());
		if(utenteEntity.get_dataCancellazione() != null)
			this.set_dataCancellazione(new Timestamp(utenteEntity.get_dataCancellazione().getTime()));
		if(utenteEntity.get_userCancellazione() != null)
			this.set_userCancellazione(utenteEntity.get_userCancellazione());
		if(utenteEntity.get_dataModifica() != null)
			this.set_dataModifica(new Timestamp(utenteEntity.get_dataModifica().getTime()));
		if(utenteEntity.get_userModifica() != null)
			this.set_userModifica(utenteEntity.get_userModifica());
		if(utenteEntity.get_flagCancellato() != null && utenteEntity.get_flagCancellato() == 1) {
			this.set_flagCancellato(true);
		} else {
			this.set_flagCancellato(false);
		}
		if(utenteEntity.get_flagValido() != null && utenteEntity.get_flagValido() == 1) {
			this.set_flagValido(true);
		} else {
			this.set_flagValido(false);
		}
		
	}
	
	
	/***************************************************************/
	/*						UTILITY					   			   */
	/***************************************************************/

	/**
	 * conversione dell'oggetto BO in entity
	 * @return Utente
	 */
	public Utente convertToEntity() {
		Utente utenteEntity = new Utente();

		if(this.get_id() != null)
			utenteEntity.set_id(this.get_id()); 
		if(this.get_idUtente() != null)
			utenteEntity.set_idUtente(this.get_idUtente());
		if(this.get_username() != null)
			utenteEntity.set_username(this.get_username());
		if(this.get_nome() != null)
			utenteEntity.set_nome(this.get_nome());
		if(this.get_cognome() != null)
			utenteEntity.set_cognome(this.get_cognome());
		if(this.get_dominio() != null)
			utenteEntity.set_dominio(this.get_dominio());
		if(this.get_email() != null)
			utenteEntity.set_email(this.get_email());
		if(this.get_dataCreazione() != null)
			utenteEntity.set_dataCreazione(new Timestamp(this.get_dataCreazione().getTime()));
		if(this.get_userCreazione() != null)
			utenteEntity.set_userCreazione(this.get_userCreazione());
		if(this.get_dataCancellazione() != null)
			utenteEntity.set_dataCancellazione(new Timestamp(this.get_dataCancellazione().getTime()));
		if(this.get_userCancellazione() != null)
			utenteEntity.set_userCancellazione(this.get_userCancellazione());
		if(this.get_dataModifica() != null)
			utenteEntity.set_dataModifica(new Timestamp(this.get_dataModifica().getTime()));
		if(this.get_userModifica() != null)
			utenteEntity.set_userModifica(this.get_userModifica());
		if(this.get_flagCancellato() != null && this.get_flagCancellato()) {
			utenteEntity.set_flagCancellato(1);
		} else {
			utenteEntity.set_flagCancellato(0);
		}
		if(this.get_flagValido() != null && this.get_flagValido()) {
			utenteEntity.set_flagValido(1);
		} else {
			utenteEntity.set_flagValido(0);
		}
		
		return utenteEntity;
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

	public Long get_idUtente() {
		return _idUtente;
	}

	public void set_idUtente(Long _idUtente) {
		this._idUtente = _idUtente;
	}
	
	
	public String get_cognome() {
		return _cognome;
	}

	public void set_cognome(String _cognome) {
		this._cognome = _cognome;
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

	public String get_dominio() {
		return _dominio;
	}

	public void set_dominio(String _dominio) {
		this._dominio = _dominio;
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

	
	public String get_nome() {
		return _nome;
	}

	public void set_nome(String _nome) {
		this._nome = _nome;
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

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}
	
	public List<String> getRoleList(){
		List<String> ga = new ArrayList<String>();
		ga.add("ROLE_ADMIN");
		return ga; 
	}
	
	@Override
	public String toString() {
		StringBuilder strBilder = new StringBuilder();

		strBilder.append("id: ");
		strBilder.append(this._id);
		strBilder.append("\n");
		strBilder.append("cognome: ");
		strBilder.append(this._cognome);
		strBilder.append("\n");
		strBilder.append("dataCancellazione: ");
		strBilder.append(this._dataCancellazione);
		strBilder.append("\n");
		strBilder.append("dataCreazione: ");
		strBilder.append(this._dataCreazione);
		strBilder.append("\n");
		strBilder.append("dataModifica: ");
		strBilder.append(this._dataModifica);
		strBilder.append("\n");
		strBilder.append("dominio: ");
		strBilder.append(this._dominio);
		strBilder.append("\n");
		strBilder.append("flagCancellato: ");
		strBilder.append(this._flagCancellato);
		strBilder.append("\n");
		strBilder.append("flagValido: ");
		strBilder.append(this._flagValido);
		strBilder.append("\n");
		strBilder.append("idUtente: ");
		strBilder.append(this._idUtente);
		strBilder.append("\n");
		strBilder.append("nome: ");
		strBilder.append(this._nome);
		strBilder.append("\n");
		strBilder.append("userCancellazione: ");
		strBilder.append(this._userCancellazione);
		strBilder.append("\n");
		strBilder.append("userCreazione: ");
		strBilder.append(this._userCreazione);
		strBilder.append("\n");
		strBilder.append("userModifica: ");
		strBilder.append(this._userModifica);
		strBilder.append("\n");
		strBilder.append("username: ");
		strBilder.append(this._username);
		strBilder.append("\n");
		
		return strBilder.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();

		map.put("id", this._id);
		map.put("idUtente", this._idUtente);
		map.put("nome", this._nome);
		map.put("cognome", this._cognome);
		map.put("dominio", this._dominio);
		map.put("username", this._username);
		map.put("userCreazione", this._userCreazione);
		map.put("userModifica", this._userModifica);
		map.put("userCancellazione", this._userCancellazione);
		map.put("dataCancellazione", this._dataCancellazione);
		map.put("dataCreazione", this._dataCreazione);
		map.put("dataModifica", this._dataModifica);
		map.put("flagCancellato", this._flagCancellato);
		map.put("flagValido", this._flagValido);
		map.put("roles", this.getRoleList());
		
		return map;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}
}
