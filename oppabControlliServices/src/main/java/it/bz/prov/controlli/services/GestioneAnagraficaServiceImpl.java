package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.AnagraficaBO;
import it.bz.prov.controlli.entities.Anagrafica;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneAnagraficaService;
import it.bz.prov.controlli.repositories.IAnagSottointerventoRepository;
import it.bz.prov.controlli.repositories.IAnagraficaRepository;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;


/**
 * classe che implementa il service di gestione della tabella di anagrafiche
 * @author bpettazzoni
 *
 */
@Service
@Transactional
public class GestioneAnagraficaServiceImpl implements IGestioneAnagraficaService{
	
	@Autowired
	private IAnagraficaRepository _repo;

	@Autowired
	private IAnagSottointerventoRepository _repoSottointervento;
	
	private Logger _logger = Utils.getLogger();
	
	
	/**********************************************************************/
	/*					GESTIONE TABELLA APP_ANAGRAFICA					  */
	/**********************************************************************/

	@Override
	public void insertAnagrafica(AnagraficaBO anagrafica) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - insertAnagrafica: scrittura su database");
		
		Anagrafica entity = anagrafica.convertToEntity();
		entity.set_id(0L);
		entity.set_idAnagrafica(_repo.getMaxIdAnagrafica() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repo.save(entity);
		
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - insertAnagrafica: scrittura su database terminata");
	}

	@Override
	public void modifyAnagrafica(AnagraficaBO anagrafica, String user) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - modifyAnagrafica: scrittura su database");
		
		Anagrafica entityOld = _repo.getLastRowById(anagrafica.get_idAnagrafica());
		if(entityOld == null)
			_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repo.save(entityOld);		
			
			// 2) creare riga nuova 
			Anagrafica entityNew = anagrafica.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user); 
			entityNew.set_flagValido(1);
			_repo.save(entityNew);	
		}					
		
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - modifyAnagrafica: scrittura su database terminata");
	}

	@Override
	public void deleteAnagrafica(Long id, String user) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - deleteAnagrafica: scrittura su database");
		
		Optional<Anagrafica> optionalEntity = _repo.findById(id);
		if(optionalEntity.isPresent()) {
			Anagrafica entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repo.save(entity);	
		} else {
			throw new ServiceException("Anagrafica non presente nel sistema");
		}
		
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - deleteAnagrafica: scrittura su database terminata");	
	}

	@Override
	public List<AnagraficaBO> getList() throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getList: inizio lettura  su database");
		
		List<Anagrafica> list = _repo.findAllValid();
		List<AnagraficaBO> listBO = new ArrayList<AnagraficaBO>();
		for (Anagrafica entity : list) {
			AnagraficaBO c = new AnagraficaBO(entity);
			listBO.add(c);
		}
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getList: lettura  su database terminata");
		return listBO;
	}

	@Override
	public List<String> getListForChiave(String chiave, String lingua) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListForChiave: inizio lettura  su database");
		List<String> list ;
		if(lingua.equals(Constants.LinguaDE))
			list= _repo.findAllValidForChiaveLangDE(chiave, Constants.CURRENT_YEAR);
		else 
			list= _repo.findAllValidForChiaveLangIT(chiave, Constants.CURRENT_YEAR);
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListForChiave: lettura  su database terminata");
		return list;
	}

	
	/**********************************************************************/
	/*					LETTURA TABELLA ANAG_SOTTOINTERVENTO			  */
	/**********************************************************************/
	
	@Override
	public List<String> getListMisura() throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListMisura: inizio lettura  su database");
		List<String> list = _repoSottointervento.getListMisura();
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListMisura: lettura  su database terminata");
		return list;
	}

	@Override
	public List<String> getListIntervento(String misura) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListIntervento: inizio lettura  su database");
		List<String> list = _repoSottointervento.getListInterventoForMisura(misura);
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListIntervento: lettura  su database terminata");
		return list;
	}

	@Override
	public List<String> getListSottointervento(String intervento) throws ServiceException {
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListSottointervento: inizio lettura  su database");
		List<String> list = _repoSottointervento.getListSottointerventoForIntervento(intervento);
		_logger.info(GestioneAnagraficaServiceImpl.class.getSimpleName() + " - getListSottointervento: lettura  su database terminata");
		return list;
	}

}
