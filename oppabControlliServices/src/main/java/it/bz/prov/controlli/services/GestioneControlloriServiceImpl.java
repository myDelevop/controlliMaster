package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.ControlloreBO;
import it.bz.prov.controlli.bo.ControlloreStazioneForestaleBO;
import it.bz.prov.controlli.entities.Controllore;
import it.bz.prov.controlli.entities.ControlloreStazioneForestale;
import it.bz.prov.controlli.entities.StazioneForestale;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneControlloriService;
import it.bz.prov.controlli.repositories.IControlloreRepository;
import it.bz.prov.controlli.repositories.IControlloreStazioneForestaleRepository;
import it.bz.prov.controlli.repositories.IStazioneForestaleRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class GestioneControlloriServiceImpl implements IGestioneControlloriService{
	
	private Logger _logger = Utils.getLogger();
	
	@Autowired
	private IControlloreRepository _repoContr;

	@Autowired
	private IStazioneForestaleRepository _repoStaz;

	@Autowired
	private IControlloreStazioneForestaleRepository _repoContrStazFor;
	
	/****************************************************************************/
	/*				GESTIONE ANAGRAFICA CONTROLLORE								*/
	/****************************************************************************/

	@Override
	public void insertControllore(ControlloreBO controllore) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - insertControllore: scrittura su database");
		
		Controllore entity = controllore.convertToEntity();
		entity.set_id(0L);
		entity.set_idControllore(_repoContr.getMaxIdControllore() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoContr.save(entity);
		
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - insertControllore: scrittura su database terminata");
	}

	@Override
	public void modifyControllore(ControlloreBO controllore, String user) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - modifyControllore: scrittura su database");
		
		Controllore entityOld = _repoContr.getLastRowById(controllore.get_idControllore());
		if(entityOld == null)
			_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user); 
			entityOld.set_flagValido(0);
			_repoContr.save(entityOld);		
			
			// 2) creare riga nuova 
			Controllore entityNew = controllore.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user);  
			entityNew.set_flagValido(1);
			_repoContr.save(entityNew);	
		}					
		
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - modifyControllore: scrittura su database terminata");
	}

	@Override
	public void deleteControllore(Long id, Long idControllore, String user) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - deleteControllore: scrittura su database");
				
		Optional<Controllore> optionalEntity = _repoContr.findById(id);
		if(optionalEntity.isPresent()) {
			// cancellazione delle relazioni stazione forestale-controllore 
			List<ControlloreStazioneForestale> list = _repoContrStazFor.findAllValidForContr(idControllore);
			for(ControlloreStazioneForestale cs: list) {
				cs.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
				cs.set_userCancellazione(user);  
				cs.set_flagCancellato(1);
				_repoContrStazFor.save(cs);
			}
						
			Controllore entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoContr.save(entity);	
		} else {
			throw new ServiceException("Controllore non presente nel sistema");
		}
		
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - deleteControllore: scrittura su database terminata");			
	}
	
	@Override
	public List<ControlloreBO> getListControllori() throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - getListControllori: inizio lettura  su database");
		
		List<Controllore> list = _repoContr.findAllValid();
		List<ControlloreBO> listBO = new ArrayList<ControlloreBO>();
		for (Controllore entity : list) {
			ControlloreBO c = new ControlloreBO(entity);
			listBO.add(c);
		}
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - getListControllori: lettura  su database terminata");
		return listBO;
	}
	
	/****************************************************************************/
	/*			GESTIONE ASSEGNAMENTO CONTROLLORE A STAZIONE FORESTALE			*/
	/****************************************************************************/

	@Override
	public void insertAssegnamentoControllore(ControlloreStazioneForestaleBO controlloreStazioneForestale) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - insertAssegnamentoControllore: scrittura su database");
		
		ControlloreStazioneForestale entity = controlloreStazioneForestale.convertToEntity();
		entity.set_id(0L);
		entity.set_idContrStazFor(_repoContrStazFor.getMaxIdControlloreStazFor() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoContrStazFor.save(entity);
		
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - insertAssegnamentoControllore: scrittura su database terminata");
	}

	@Override
	public void modifyAssegnamentoControllore(ControlloreStazioneForestaleBO controlloreStazioneForestale, String user) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - modifyAssegnamentoControllore: scrittura su database");
		
		ControlloreStazioneForestale entityOld = _repoContrStazFor.getLastRowById(controlloreStazioneForestale.get_idContrStazFor());
		if(entityOld == null)
			_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repoContrStazFor.save(entityOld);		
			
			// 2) creare riga nuova 
			ControlloreStazioneForestale entityNew = controlloreStazioneForestale.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user);
			entityNew.set_flagValido(1);
			_repoContrStazFor.save(entityNew);	
		}
		
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - modifyAssegnamentoControllore: scrittura su database terminata");
	}

	@Override
	public void deleteAssegnamentoControllore(Long id, String user) throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - deleteAssegnamentoControllore: scrittura su database");
		
		Optional<ControlloreStazioneForestale> optionalEntity = _repoContrStazFor.findById(id);
		if(optionalEntity.isPresent()) {
			ControlloreStazioneForestale entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoContrStazFor.save(entity);	
		} else {
			throw new ServiceException("Controllore-Stazione Forestale non presente nel sistema");
		}

		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - deleteAssegnamentoControllore: scrittura su database terminata");
	}

	@Override
	public List<ControlloreStazioneForestaleBO> getListControlloriStazFor() throws ServiceException {
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - getListControlloriStazFor: inizio lettura  su database");
		
		List<ControlloreStazioneForestale> list = _repoContrStazFor.findAllValid();
		List<ControlloreStazioneForestaleBO> listBO = new ArrayList<ControlloreStazioneForestaleBO>();
		for (ControlloreStazioneForestale entity : list) {
			Controllore controllore = _repoContr.getLastRowById(entity.get_idControllore());
			Optional<StazioneForestale> optionalSf = _repoStaz.findValidFromId(entity.get_idStazioneForestale());
			StazioneForestale stazioneForestale = null;
			if(optionalSf.isPresent())
				stazioneForestale = optionalSf.get();
			else 
				throw new ServiceException("stazioneForestale non presente nel sistema");
			ControlloreStazioneForestaleBO c = new ControlloreStazioneForestaleBO(entity, controllore, stazioneForestale);
			listBO.add(c);
		}
		_logger.info(GestioneControlloriServiceImpl.class.getSimpleName() + " - getListControlloriStazFor: lettura  su database terminata");
		return listBO;
	}

}
