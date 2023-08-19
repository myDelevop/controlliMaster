package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.GruppoBO;
import it.bz.prov.controlli.bo.GruppoGruppoBO;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.bo.UtenteGruppoBO;
import it.bz.prov.controlli.entities.Gruppo;
import it.bz.prov.controlli.entities.GruppoGruppo;
import it.bz.prov.controlli.entities.Utente;
import it.bz.prov.controlli.entities.UtenteGruppo;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.repositories.IGruppoGruppoRepository;
import it.bz.prov.controlli.repositories.IGruppoRepository;
import it.bz.prov.controlli.repositories.IUtenteGruppoRepository;
import it.bz.prov.controlli.repositories.IUtenteRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class GestioneUtentiServiceImpl implements IGestioneUtentiService {
	@Autowired
	private IUtenteRepository _utenteRepo;
	@Autowired
	private IGruppoRepository _gruppoRepo;
	@Autowired
	private IUtenteGruppoRepository _utenteGruppoRepo;
	@Autowired
	private IGruppoGruppoRepository _gruppoGruppoRepo;

	private Logger _logger = Utils.getLogger();


	/****************************************************************************/
	/*									UTENTE									*/
	/****************************************************************************/
	
	@Override
	public UtenteBO insertUtente(UtenteBO utente) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertUtente: scrittura su database");
		
		utente.set_idUtente(this.getMaxIDUtente() + 1);
		utente.set_flagValido(true);
		utente.set_flagCancellato(false);
		utente.set_dataCreazione(new java.util.Date(System.currentTimeMillis()));

		// nell'update viene chiamata la insert ma id riga rimane tale
		if(utente.get_id() != 0) {
			utente.set_idUtente(this.findUtenteByID(utente.get_id()).get_idUtente()); // se si tratta di un aggiornamento non dobbiamo incrementare idUtente
			utente.set_dataModifica(new java.util.Date(System.currentTimeMillis()));
			utente.set_userModifica(utente.get_userCreazione());
		}
		
		/* idRiga rimane pari a 0 (valore di default). Il DB provvederà a creare un valore
		 incrementale in fase di inserimento, richiamando il trigger di auto_increment */
		utente.set_id(0L);

		Utente utenteEntity = utente.convertToEntity();
		Utente saved = _utenteRepo.save(utenteEntity);
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertUtente: scrittura su database terminata");
		return new UtenteBO(saved);
	}

	@Override
	public UtenteBO updateUtente(UtenteBO utente, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateUtente: scrittura su database");
		Optional<Utente> optionalEntity = _utenteRepo.findById(utente.get_id());
		Utente historicalEntity = null;
		Utente entityNew= null;
		
		if(optionalEntity.isPresent()) {
			historicalEntity = optionalEntity.get();
			
			if(historicalEntity == null)
				_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - riga non trovata su db");
			else
			{
				// 1) invalidare riga vecchia
				historicalEntity.set_dataModifica(new Timestamp(System.currentTimeMillis()));
				historicalEntity.set_userModifica(user);
				historicalEntity.set_flagValido(0);
				_utenteRepo.save(historicalEntity);		
				
				// 2) creare riga nuova 
				entityNew = utente.convertToEntity();
				entityNew.set_id(0L);
				entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
				entityNew.set_userCreazione(user); 
				entityNew.set_flagValido(1);
				_utenteRepo.save(entityNew);
			}
		}
		else 
			throw new ServiceException("Utente non presente nel sistema");
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateUtente: scrittura su database terminata");
		return new UtenteBO(entityNew);
	}

	@Override
	public Long deleteUtente(Long id, Long idUtente, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteUtente: scrittura su database");
		Optional<Utente> optionalEntity = _utenteRepo.findById(id);
		
		Utente historicalEntity = null;
		if(optionalEntity.isPresent()) {
			historicalEntity = optionalEntity.get();
			if(historicalEntity.get_username().equalsIgnoreCase(user))
				throw new ServiceException("non è possibile rimuovere l'utente loggato");
			historicalEntity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			historicalEntity.set_userCancellazione(user);
			historicalEntity.set_flagCancellato(1); // flag valido rimane ad 1
		} else {
			throw new ServiceException("utente non presente nel sistema");
		}
		
		/* Prima di rimuovere l'utente, cancello le associazioni Utente-Gruppo presenti nel DB */
		List<UtenteGruppo> ugList = _utenteGruppoRepo.findValidFromUserId(idUtente);
		for(UtenteGruppo ug: ugList) {
			this.deleteUtenteGruppo(ug.get_id(), user);
		}
		
		Utente deleted = _utenteRepo.save(historicalEntity);
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteUtente: scrittura su database terminata");
		return deleted.get_id();
	}

	@Override
	public List<UtenteBO> findAllUtentiValidi() throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllUtentiValidi: inizio lettura  su database");
		List<UtenteBO> utentiBO = new ArrayList<UtenteBO>();

		List<Utente> listaUtenti = _utenteRepo.findAllValid();
		utentiBO = new ArrayList<UtenteBO>();
		
		for(Utente u: listaUtenti) {
			utentiBO.add(new UtenteBO(u));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllUtentiValidi: fine lettura  su database");
		return utentiBO;
	}

	@Override
	public UtenteBO findUtenteByID(Long id) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteByID: inizio lettura  su database");
		UtenteBO utenteBO = null;
		Optional<Utente> utenteOptional = _utenteRepo.findById(id);
		if(utenteOptional.isPresent()) {
			utenteBO = new UtenteBO(utenteOptional.get());
		} else {
			throw new ServiceException("Utente non presente nel sistema"); 
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteByID: fine lettura  su database");
		return utenteBO;
	}

	@Override
	public UtenteBO findUtenteByUsername(String username) throws ServiceException{
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteByUsername: inizio lettura  su database");
		UtenteBO utenteBO = null;
		Optional<Utente> utenteOptional = _utenteRepo.findValidFromUsername(username);
		if(utenteOptional.isPresent()) {
			utenteBO = new UtenteBO(utenteOptional.get());
		} else {
			throw new ServiceException("Utente non presente nel sistema"); 
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteByUsername: fine lettura  su database");
		return utenteBO;
	}
	
	/****************************************************************************/
	/*									GRUPPO									*/
	/****************************************************************************/
	

	@Override
	public GruppoBO insertGruppo(GruppoBO gruppo) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertGruppo: scrittura su database");
		gruppo.set_idGruppo(this.getUserMaxIDGruppo() + 1);
		gruppo.set_flagValido(true);
		gruppo.set_flagCancellato(false);
		gruppo.set_dataCreazione(new java.util.Date(System.currentTimeMillis()));		

		// nell'update viene chiamata la insert ma id riga rimane tale
		if(gruppo.get_id() != 0) {
			gruppo.set_idGruppo(this.findGruppoByID(gruppo.get_id()).get_idGruppo()); // se si tratta di un aggiornamento non dobbiamo incrementare idUtente
			gruppo.set_dataModifica(new java.util.Date(System.currentTimeMillis()));
			gruppo.set_userModifica(gruppo.get_userCreazione());
		}
		
		/* idRiga rimane pari a 0 (valore di default). Il DB provvederà a creare un valore
		 incrementale in fase di inserimento, richiamando il trigger di auto_increment */
		gruppo.set_id(0L);

		Gruppo gruppoEntity = gruppo.convertToEntity();
		Gruppo saved = _gruppoRepo.save(gruppoEntity);
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertGruppo: scrittura su database terminata");
		return new GruppoBO(saved);
	}


	
	@Override
	public GruppoBO updateGruppo(GruppoBO gruppo, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateGruppo: scrittura su database");
		Optional<Gruppo> optionalEntity = _gruppoRepo.findById(gruppo.get_id());
		Gruppo historicalEntity = null;
		Gruppo entityNew= null;
		
		if(optionalEntity.isPresent()) {
			historicalEntity = optionalEntity.get();
			
			if(historicalEntity == null)
				_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - riga non trovata su db");
			else
			{
				// 1) invalidare riga vecchia
				historicalEntity.set_dataModifica(new Timestamp(System.currentTimeMillis()));
				historicalEntity.set_userModifica(user);
				historicalEntity.set_flagValido(0);
				_gruppoRepo.save(historicalEntity);		
				
				// 2) creare riga nuova 
				entityNew = gruppo.convertToEntity();
				entityNew.set_id(0L);
				entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
				entityNew.set_userCreazione(user); 
				entityNew.set_flagValido(1);
				_gruppoRepo.save(entityNew);
			}
		} else 
			throw new ServiceException("Gruppo non presente nel sistema");
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateGruppo: scrittura su database terminata");
		return new GruppoBO(entityNew);
	}

	@Override
	public Long deleteGruppo(Long id, Long idGruppo, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteGruppo: scrittura su database");
		Optional<Gruppo> optionalEntity = _gruppoRepo.findById(id);
		Gruppo historicalEntity = null;
		
		if(optionalEntity.isPresent()) {
			historicalEntity = optionalEntity.get();
			historicalEntity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			historicalEntity.set_userCancellazione(user);
			historicalEntity.set_flagCancellato(1); // flag valido rimane ad 1
		} else {
			throw new ServiceException("Gruppo non presente nel sistema");						
		}
		
		/* Prima di rimuovere il gruppo, cancello le associazioni Utente-Gruppo presenti nel DB */
		List<UtenteGruppo> ugList = _utenteGruppoRepo.findValidFromGroupId(idGruppo);
		for(UtenteGruppo ug: ugList) {
			this.deleteUtenteGruppo(ug.get_id(), user);
		}
		
		/* Prima di rimuovere il gruppo, cancello le associazioni Gruppo-Gruppo presenti nel DB */
		List<GruppoGruppo> ggList = _gruppoGruppoRepo.findRelForGruppo(idGruppo);
		for(GruppoGruppo gg: ggList) {
			this.deleteGruppoGruppo(gg.get_id(), user);
		}
		
		// cancellazione gruppo
		Gruppo deleted = _gruppoRepo.save(historicalEntity);
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteGruppo: scrittura su database terminata");
		return deleted.get_id();
	}

	@Override
	public List<GruppoBO> findAllGruppiValidi() throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllGruppiValidi: inizio lettura  su database");
		List<Gruppo> listaGruppi = _gruppoRepo.findAllValid();
		List<GruppoBO> gruppiBO = new ArrayList<GruppoBO>();
		
		for(Gruppo g: listaGruppi) {
			gruppiBO.add(new GruppoBO(g));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllGruppiValidi: fine lettura  su database");
		return gruppiBO;
	}

	@Override
	public GruppoBO findGruppoByID(Long id) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppoByID: inizio lettura  su database");
		GruppoBO gruppoBO = null;
		Optional<Gruppo> gruppoOptional = _gruppoRepo.findById(id);
		if(gruppoOptional.isPresent()) {
			gruppoBO = new GruppoBO(gruppoOptional.get());
		} else {
			throw new ServiceException("Gruppo non presente nel sistema"); 
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppoByID: fine lettura  su database");
		return gruppoBO;
	}
	
	
	/****************************************************************************/
	/*							UTENTE-GRUPPO									*/
	/****************************************************************************/

	@Override
	public UtenteGruppoBO insertUtenteGruppo(UtenteGruppoBO utenteGruppo) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertUtenteGruppo: scrittura su database");
		UtenteGruppoBO res = utenteGruppo;
		if(!_utenteGruppoRepo.findValidFromUserIdAndGroupId(utenteGruppo.get_utente().get_idUtente(), 
				utenteGruppo.get_gruppo().get_idGruppo()).isPresent()) {
			/* idRiga rimane pari a 0 (valore di default). Il DB provvederà a creare un valore
			 incrementale in fase di inserimento, richiamando il trigger di auto_increment */
			utenteGruppo.set_idRel(this.getMaxIDUtenteGruppo() + 1);
			utenteGruppo.set_flagValido(true);
			utenteGruppo.set_flagCancellato(false);
			utenteGruppo.set_dataCreazione(new java.util.Date(System.currentTimeMillis()));

			// nell'update viene chiamata la insert ma id riga rimane tale
			if(utenteGruppo.get_id() != 0) {
				//gruppo.set_idGruppo(this.findGruppoByID(gruppo.get_id()).get_id()); // se si tratta di un aggiornamento non dobbiamo incrementare idUtente
				utenteGruppo.set_idRel(this.findUtenteGruppoByID(utenteGruppo.get_id()).get_idRel());
				utenteGruppo.set_dataModifica(new java.util.Date(System.currentTimeMillis()));
				utenteGruppo.set_userModifica(utenteGruppo.get_userCreazione());
			} 
			utenteGruppo.set_id(0L);
			UtenteGruppo utenteGruppoEntity = utenteGruppo.convertToEntity();
			UtenteGruppo saved = _utenteGruppoRepo.save(utenteGruppoEntity);
			res = new UtenteGruppoBO(saved);
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertUtenteGruppo: scrittura su database terminata");
		return res;
	}

	/**
	 * Commentata perchè al momento non si da la possibilità di fare update dal portale. Si deve cancellare e ricreare la relazione
	 */
//	@Override
//	public UtenteGruppoBO updateUtenteGruppo(UtenteGruppoBO utenteGruppo) throws ServiceException {
//		// usare id riga oppure idUtente con flag=1 ??
//		Optional<UtenteGruppo> optionalEntity = _utenteGruppoRepo.findById(utenteGruppo.get_id());
//		UtenteGruppo historicalEntity = null;
//		
//		if(optionalEntity.isPresent()) {
//			historicalEntity = optionalEntity.get();
//			historicalEntity.set_flagValido(0);
//		} else {
//			throw new ServiceException("Associazione Utente-gruppo non presente nel sistema");
//		}
//		
//		this.insertUtenteGruppo(utenteGruppo);
//		UtenteGruppo updated = _utenteGruppoRepo.save(historicalEntity);
//		return new UtenteGruppoBO(updated);
//	}

	@Override
	public Long deleteUtenteGruppo(Long id, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteUtenteGruppo: scrittura su database");
		Optional<UtenteGruppo> optionalEntity = _utenteGruppoRepo.findById(id);
		
		UtenteGruppo historicalEntity = null;
		if(optionalEntity.isPresent()) {
			historicalEntity = optionalEntity.get();
			historicalEntity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			historicalEntity.set_userCancellazione(user);
			historicalEntity.set_flagCancellato(1); // flag valido rimane ad 1
		} else {
			throw new ServiceException("Associazione Utente-gruppo non presente nel sistema");
		}
		
		UtenteGruppo deleted = _utenteGruppoRepo.save(historicalEntity);	
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteUtenteGruppo: scrittura su database terminata");
		return deleted.get_id();
	}

	@Override
	public List<GruppoBO> findGruppiValidiFromUtente(UtenteBO utente) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppiValidiFromUtente: inizio lettura  su database");
		List<Gruppo> gruppi = _utenteGruppoRepo.findGruppiValidFromUserId(utente.get_idUtente());
		
		List<GruppoBO> gruppiBO = new ArrayList<GruppoBO>();
		for(Gruppo g: gruppi) {
			gruppiBO.add(new GruppoBO(g));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppiValidiFromUtente: fine lettura  su database");
		return gruppiBO;
	}

	@Override
	public List<UtenteBO> findUtentiValidiFromGruppo(GruppoBO gruppo) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtentiValidiFromGruppo: inizio lettura  su database");
		List<Utente> utenti = _utenteGruppoRepo.findUtentiValidFromGroupId(gruppo.get_idGruppo());

		List<UtenteBO> utentiBO = new ArrayList<UtenteBO>();
		for(Utente u: utenti) {
			utentiBO.add(new UtenteBO(u));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtentiValidiFromGruppo: fine lettura  su database");
		return utentiBO;
	}

	@Override
	public UtenteGruppoBO findUtenteGruppoByID(Long id) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteGruppoByID: inizio lettura  su database");
		UtenteGruppoBO utenteGruppoBO = null;
		Optional<UtenteGruppo> utenteGruppoOptional = _utenteGruppoRepo.findById(id);
		if(utenteGruppoOptional.isPresent()) {
			utenteGruppoBO = new UtenteGruppoBO(utenteGruppoOptional.get());
		} else {
			throw new ServiceException("Utente non presente nel sistema"); 
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findUtenteGruppoByID: fine lettura  su database");
		return utenteGruppoBO;
	}

	@Override
	public List<UtenteGruppoBO> findAllUtentiGruppiValidi() throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllUtentiGruppiValidi: inizio lettura  su database");
		List<UtenteGruppo> listaUtenteGruppo = _utenteGruppoRepo.findAllValid();
		List<UtenteGruppoBO> utentiGruppiBO = new ArrayList<UtenteGruppoBO>();
		Optional<Utente> optionalUtente = null;
		Optional<Gruppo> optionalGruppo = null;
		Utente u = null;
		Gruppo g = null;
		for(UtenteGruppo ugEntity: listaUtenteGruppo) {
			optionalUtente = _utenteRepo.findValidFromId(ugEntity.get_idUtente());
			optionalGruppo = _gruppoRepo.findValidFromId(ugEntity.get_idGruppo());
			if(optionalUtente.isPresent() && optionalGruppo.isPresent()) {
				u = optionalUtente.get();
				g = optionalGruppo.get();
			} else 
				throw new ServiceException("enita non presente nel sistema");
			
			utentiGruppiBO.add(new UtenteGruppoBO(ugEntity, u, g));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllUtentiGruppiValidi: fine lettura  su database");
		return utentiGruppiBO;
	}

	/****************************************************************************/
	/*								GRUPPO-GRUPPO								*/
	/****************************************************************************/
	
	@Override
	public GruppoGruppoBO insertGruppoGruppo(GruppoGruppoBO gruppoGruppo) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertGruppoGruppo: scrittura su database");
		
		GruppoGruppo entity = gruppoGruppo.convertToEntity();
		entity.set_id(0L);
		entity.set_idRel(_gruppoGruppoRepo.getMaxIdRel() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		GruppoGruppo saved = _gruppoGruppoRepo.save(entity);
		
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - insertGruppoGruppo: scrittura su database terminata");
		return new GruppoGruppoBO(saved);
	}

	/**
	 * Commentata perchè al momento non si da la possibilità di fare update dal portale. Si deve cancellare e ricreare la relazione
	 */
//	@Override
//	public GruppoGruppoBO updateGruppoGruppo(GruppoGruppoBO gruppoGruppo, String user) throws ServiceException {
//		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateGruppoGruppo: scrittura su database");
//		
//		Optional<GruppoGruppo> optional = _gruppoGruppoRepo.findValidFromId(gruppoGruppo.get_idRel());
//		GruppoGruppo entityOld = null;
//		GruppoGruppo saved = null;
//		
//		if(optional.isPresent())
//			entityOld = optional.get();
//		else 
//			throw new ServiceException("GruppoGruppo non presente nel sistema");
//
//		if(entityOld == null)
//			_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - riga non trovata su db");
//		else
//		{
//			// 1) invalidare riga vecchia
//			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
//			entityOld.set_userModifica(user); 
//			entityOld.set_flagValido(0);
//			_gruppoGruppoRepo.save(entityOld);		
//			
//			// 2) creare riga nuova 
//			GruppoGruppo entityNew = gruppoGruppo.convertToEntity();
//			entityNew.set_id(0L);
//			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
//			entityNew.set_userCreazione(user);
//			entityNew.set_flagValido(1);
//			saved = _gruppoGruppoRepo.save(entityNew);		
//		}	
//		
//		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - updateGruppoGruppo: scrittura su database terminata");
//		return new GruppoGruppoBO(saved);
//	}

	@Override
	public Long deleteGruppoGruppo(Long id, String user) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteStazioneForestale: scrittura su database");
		GruppoGruppo deleted = null;
		
		Optional<GruppoGruppo> optionalEntity = _gruppoGruppoRepo.findById(id);
		if(optionalEntity.isPresent()) {			
			GruppoGruppo entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user); 
			entity.set_flagCancellato(1);
			deleted = _gruppoGruppoRepo.save(entity);	
		} else {
			throw new ServiceException("GruppoGruppo non presente nel sistema");
		}
		
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - deleteStazioneForestale: scrittura su database terminata");
		return deleted.get_id();
	}

	@Override
	public List<GruppoGruppoBO> findAllGruppiGruppiValidi() throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllGruppiGruppiValidi: inizio lettura  su database");
		
		List<GruppoGruppo> listaGruppoGruppo = _gruppoGruppoRepo.findAllValid();
		List<GruppoGruppoBO> gruppiGruppiBO = new ArrayList<GruppoGruppoBO>();
		Optional<Gruppo> optionalGruppoParent = null;
		Optional<Gruppo> optionalGruppoChild = null;
		Gruppo gruppoParent = null;
		Gruppo gruppoChild = null;
		for(GruppoGruppo ggEntity: listaGruppoGruppo) {
			optionalGruppoParent = _gruppoRepo.findValidFromId(ggEntity.get_idGruppoParent());
			optionalGruppoChild = _gruppoRepo.findValidFromId(ggEntity.get_idGruppoChild());
			if(optionalGruppoParent.isPresent() && optionalGruppoChild.isPresent()) {
				gruppoParent = optionalGruppoParent.get();
				gruppoChild = optionalGruppoChild.get();
			} else 
				throw new ServiceException("entita non presente nel sistema");
			
			gruppiGruppiBO.add(new GruppoGruppoBO(ggEntity, gruppoParent, gruppoChild));
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findAllGruppiGruppiValidi: fine lettura  su database");
		return gruppiGruppiBO;
	}

	@Override
	public GruppoGruppoBO findGruppoGruppoByID(Long id) throws ServiceException {
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppoGruppoByID: inizio lettura  su database");
		
		GruppoGruppoBO gruppoGruppoBO = null;
		Optional<GruppoGruppo> gruppoGruppoOptional = _gruppoGruppoRepo.findById(id);
		if(gruppoGruppoOptional.isPresent()) {
			gruppoGruppoBO = new GruppoGruppoBO(gruppoGruppoOptional.get());
		} else {
			throw new ServiceException("GruppoGruppo non presente nel sistema"); 
		}
		_logger.info(GestioneUtentiServiceImpl.class.getSimpleName() + " - findGruppoGruppoByID: fine lettura  su database");
		return gruppoGruppoBO;
	}
	
	
	/****************************************************************************/
	/*									UTILITIES								*/
	/****************************************************************************/
	
	
	private Long getMaxIDUtente() throws ServiceException {
		return _utenteRepo.getMaxID();
	}
	
	private Long getUserMaxIDGruppo() throws ServiceException {
		return _gruppoRepo.getMaxID();
	}
	
	private Long getMaxIDUtenteGruppo() throws ServiceException {
		return _utenteGruppoRepo.getMaxID();
	}

	

}
