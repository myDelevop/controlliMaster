
package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.StazioneForestaleBO;
import it.bz.prov.controlli.entities.ControlloreStazioneForestale;
import it.bz.prov.controlli.entities.StazioneForestale;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneStazioniForestaliService;
import it.bz.prov.controlli.repositories.IControlloreStazioneForestaleRepository;
import it.bz.prov.controlli.repositories.IStazioneForestaleRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class GestioneStazioniForestaliServiceImpl implements IGestioneStazioniForestaliService{
	
	private Logger _logger = Utils.getLogger();
	
	@Autowired
	private IStazioneForestaleRepository _repoStazFor;
	@Autowired
	private IControlloreStazioneForestaleRepository _repoContrStazFor;

	@Override
	public void insertStazioneForestale(StazioneForestaleBO stazioneForestale) throws ServiceException {
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - insertStazioneForestale: scrittura su database");
		
		StazioneForestale entity = stazioneForestale.convertToEntity();
		entity.set_id(0L);
		entity.set_idStazioneForestale(_repoStazFor.getMaxIdStazioneForestale() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoStazFor.save(entity);
		
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - insertStazioneForestale: scrittura su database terminata");
	}

	@Override
	public void modifyStazioneForestale(StazioneForestaleBO stazioneForestale, String user) throws ServiceException {
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - modifyStazioneForestale: scrittura su database");
		
		Optional<StazioneForestale> optionalStazFor = _repoStazFor.findValidFromId(stazioneForestale.get_idStazioneForestale());
		StazioneForestale entityOld = null;
		
		if(optionalStazFor.isPresent())
			entityOld = optionalStazFor.get();
		else 
			throw new ServiceException("stazioneForestale non presente nel sistema");

		if(entityOld == null)
			_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else
		{
			// 1) invalidare riga vecchia
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user); 
			entityOld.set_flagValido(0);
			_repoStazFor.save(entityOld);		
			
			// 2) creare riga nuova 
			StazioneForestale entityNew = stazioneForestale.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user);
			entityNew.set_flagValido(1);
			_repoStazFor.save(entityNew);		
		}	
		
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - modifyStazioneForestale: scrittura su database terminata");		
	}

	@Override
	public void deleteStazioneForestale(Long id, Long idStazioneForestale, String user) throws ServiceException {
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - deleteStazioneForestale: scrittura su database");
				
		Optional<StazioneForestale> optionalEntity = _repoStazFor.findById(id);
		if(optionalEntity.isPresent()) {			
			// cancellazione delle relazioni stazione forestale-controllore 
			List<ControlloreStazioneForestale> list = _repoContrStazFor.findAllValidForStazFor(idStazioneForestale);
			for(ControlloreStazioneForestale cs: list) {
				cs.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
				cs.set_userCancellazione(user);
				cs.set_flagCancellato(1);
				_repoContrStazFor.save(cs);
			}		
			
			// cancellazione stazione forestale
			StazioneForestale entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user); 
			entity.set_flagCancellato(1);
			_repoStazFor.save(entity);	
		} else {
			throw new ServiceException("Stazione forestale non presente nel sistema");
		}
		
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - deleteStazioneForestale: scrittura su database terminata");	
	}

	@Override
	public List<StazioneForestaleBO> getList() throws ServiceException {
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - getList: inizio lettura  su database");
		
		List<StazioneForestale> list = _repoStazFor.findAllValid();
		List<StazioneForestaleBO> listBO = new ArrayList<StazioneForestaleBO>();
		for (StazioneForestale stazioneForestale : list) {
			StazioneForestaleBO s = new StazioneForestaleBO(stazioneForestale);
			listBO.add(s);
		}
		_logger.info(GestioneStazioniForestaliServiceImpl.class.getSimpleName() + " - getList: lettura  su database terminata");
		return listBO;
	}

}
