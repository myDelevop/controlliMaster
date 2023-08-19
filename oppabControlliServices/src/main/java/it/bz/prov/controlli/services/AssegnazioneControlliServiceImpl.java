package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.AziendaStazForControlloreBO;
import it.bz.prov.controlli.bo.AziendaStazioneForestaleBO;
import it.bz.prov.controlli.entities.Azienda;
import it.bz.prov.controlli.entities.AziendaStazForControllore;
import it.bz.prov.controlli.entities.AziendaStazioneForestale;
import it.bz.prov.controlli.entities.StazioneForestale;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAssegnazioneControlliService;
import it.bz.prov.controlli.repositories.IAziendaRepository;
import it.bz.prov.controlli.repositories.IAziendaStazForControlloreRepository;
import it.bz.prov.controlli.repositories.IAziendaStazioneForestaleRepository;
import it.bz.prov.controlli.repositories.IStazioneForestaleRepository;
import it.bz.prov.controlli.util.Utils;


@Service
@Transactional
public class AssegnazioneControlliServiceImpl implements IAssegnazioneControlliService {
	
	@Autowired
	private IAziendaStazioneForestaleRepository _repoAziStazFor;
	@Autowired
	private IAziendaStazForControlloreRepository _repoAziStazForContr;
	@Autowired
	private IStazioneForestaleRepository _repoStazioneForestale;
	@Autowired
	private IAziendaRepository _repoAzienda;

	private Logger _logger = Utils.getLogger();
	
	/*****************************************************************************/
	/*			1) Assegnamento CUAA a Stazione forestale						 */
	/*****************************************************************************/
	
	
	@Override
	public void insertAziStazFor(AziendaStazioneForestaleBO asf) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - insertAziStazFor: scrittura su database");
		
		AziendaStazioneForestale entity = asf.convertToEntity();
		entity.set_id(0L);
		entity.set_idAziStazFor(_repoAziStazFor.getMaxIdAziStazFor() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoAziStazFor.save(entity);
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - insertAziStazFor: scrittura su database terminata");
	}

	@Override
	public void modifyAziStazFor(AziendaStazioneForestaleBO asf, String user) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - modifyAziStazFor: scrittura su database");
		
		AziendaStazioneForestale entityOld = _repoAziStazFor.getLastRowById(asf.get_idAziStazFor());
		if(entityOld == null)
			_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repoAziStazFor.save(entityOld);		
			
			// 2) creare riga nuova 
			AziendaStazioneForestale entityNew = asf.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user); 
			entityNew.set_flagValido(1);
			_repoAziStazFor.save(entityNew);	
		}					
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - modifyAziStazFor: scrittura su database terminata");
	}

	@Override
	public void deleteAziStazFor(Long id, String user) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - deleteAziStazFor: scrittura su database");
		
		Optional<AziendaStazioneForestale> optionalEntity = _repoAziStazFor.findById(id);
		if(optionalEntity.isPresent()) {
			AziendaStazioneForestale entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoAziStazFor.save(entity);	
		} else {
			throw new ServiceException("Anagrafica non presente nel sistema");
		}
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - deleteAziStazFor: scrittura su database terminata");	
	}

	@Override
	public List<AziendaStazioneForestaleBO> getListAziStazFor() throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - getListAziStazFor: inizio lettura  su database");
		
		List<AziendaStazioneForestale> list = _repoAziStazFor.findAllValid();
		List<AziendaStazioneForestaleBO> listBO = new ArrayList<AziendaStazioneForestaleBO>();
		for (AziendaStazioneForestale entity : list) {
			Optional<Azienda> optionalAzienda = _repoAzienda.findById(entity.get_idAzienda());
			Optional<StazioneForestale> optionalSf = _repoStazioneForestale.findValidFromId(entity.get_idStazioneForestale());
			Azienda azienda = null;
			StazioneForestale stazioneForestale = null;
			
			if(optionalAzienda.isPresent() && optionalSf.isPresent()) {
				azienda = optionalAzienda.get();
				stazioneForestale = optionalSf.get();
			}
			else 
				throw new ServiceException("stazioneForestale non presente nel sistema");
			
			AziendaStazioneForestaleBO c = new AziendaStazioneForestaleBO(entity, azienda, stazioneForestale);
			listBO.add(c);
		}
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - getListAziStazFor: lettura  su database terminata");
		return listBO;
	}

	
	@Override
	public List<AziendaStazioneForestaleBO> assegnamentoAutomaticoAziendaStazFor(String user) throws ServiceException {
		
		//TODO ------- da modificare
		int campagna = 2019; /***************************** da cambiare!!!! ***************************************/
		
		List<AziendaStazioneForestaleBO> listBO = new ArrayList<AziendaStazioneForestaleBO>();
		// 1. recupero le aziende a campione per la campagna 
		List<Azienda> listCuaa = _repoAzienda.getAziendeDaAssegnareForCampagna(campagna);
		for (Azienda azienda : listCuaa) {
			//2. per ogni azienda recupero la stazione forestale corretta dal mapping
			StazioneForestale sf = _repoStazioneForestale.findStazioneForestaleForComuneIT(azienda.get_comune());
			// creazione di una nuova riga sulla tabella finale
			AziendaStazioneForestale asf = new AziendaStazioneForestale();
			asf.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			asf.set_userCreazione(user);
			asf.set_idAziStazFor(_repoAziStazFor.getMaxIdAziStazFor() + 1);
			asf.set_idAzienda(azienda.get_idAzienda());
			asf.set_cuaa(azienda.get_cuaa());
			asf.set_idStazioneForestale(sf.get_idStazioneForestale());
			asf.set_campagna(campagna);
			asf.set_note("*** Riga generata dall'assegnamento automatico ***");
			_repoAziStazFor.save(asf);
			AziendaStazioneForestaleBO asfBO =new AziendaStazioneForestaleBO(asf, azienda, sf); 
			listBO.add(asfBO);
		}
		return listBO;
	}

	
	
	/*****************************************************************************/
	/*			2) Assegnamento CUAA a controllori								 */
	/*****************************************************************************/


	@Override
	public void insertAziContr(AziendaStazForControlloreBO ac) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - insertAziContr: scrittura su database");
		
		AziendaStazForControllore entity = ac.convertToEntity();
		entity.set_id(0L);
		entity.set_idAziStazForContr(_repoAziStazForContr.getMaxIdAziStazForContr() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoAziStazForContr.save(entity);
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - insertAziContr: scrittura su database terminata");
	}

	@Override
	public void modifyAziContr(AziendaStazForControlloreBO ac, String user) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - modifyAziContr: scrittura su database");
		
		AziendaStazForControllore entityOld = _repoAziStazForContr.getLastRowById(ac.get_idAziStazForContr());
		if(entityOld == null)
			_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repoAziStazForContr.save(entityOld);		
			
			// 2) creare riga nuova 
			AziendaStazForControllore entityNew = ac.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user); 
			entityNew.set_flagValido(1);
			_repoAziStazForContr.save(entityNew);	
		}					
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - modifyAziContr: scrittura su database terminata");
	}

	@Override
	public void deleteAziContr(Long id, String user) throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - deleteAziContr: scrittura su database");
		
		Optional<AziendaStazForControllore> optionalEntity = _repoAziStazForContr.findById(id);
		if(optionalEntity.isPresent()) {
			AziendaStazForControllore entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoAziStazForContr.save(entity);	
		} else {
			throw new ServiceException("Anagrafica non presente nel sistema");
		}
		
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - deleteAziContr: scrittura su database terminata");
	}

	@Override
	public List<AziendaStazForControlloreBO> getListAziContr() throws ServiceException {
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - getListAziContr: inizio lettura  su database");
		
		List<AziendaStazForControllore> list = _repoAziStazForContr.findAllValid();
		List<AziendaStazForControlloreBO> listBO = new ArrayList<AziendaStazForControlloreBO>();
		for (AziendaStazForControllore entity : list) {
			AziendaStazForControlloreBO c = new AziendaStazForControlloreBO(entity);
			listBO.add(c);
		}
		_logger.info(AssegnazioneControlliServiceImpl.class.getSimpleName() + " - getListAziContr: lettura  su database terminata");
		return listBO;
	}

	
	

	
}
