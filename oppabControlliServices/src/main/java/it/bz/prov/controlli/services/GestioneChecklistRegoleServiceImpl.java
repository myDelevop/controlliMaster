package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.ChecklistRegolaBO;
import it.bz.prov.controlli.entities.ChecklistRegola;
import it.bz.prov.controlli.entities.Controllo;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneChecklistRegoleService;
import it.bz.prov.controlli.repositories.IChecklistRegoleRepository;
import it.bz.prov.controlli.repositories.IControlloRepository;
import it.bz.prov.controlli.util.Utils;

/**
 * Classe che implementa il service di gestione delle regole delle checklist
 * @author bpettazzoni
 *
 */
@Service
@Transactional
public class GestioneChecklistRegoleServiceImpl implements IGestioneChecklistRegoleService	{
	
	@Autowired
	private IChecklistRegoleRepository _repoCheckRegola;

	@Autowired
	private IControlloRepository _repoControllo;
	
	private Logger _logger = Utils.getLogger();

	@Override
	public void insertRegola(ChecklistRegolaBO checklistRegola) throws ServiceException {
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - insertRegola: scrittura su database");
		
		ChecklistRegola entity = checklistRegola.convertToEntity();
		entity.set_id(0L);
		entity.set_idChecklistRegola(_repoCheckRegola.getMaxIdChecklistRegola() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoCheckRegola.save(entity);
		
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - insertRegola: scrittura su database terminata");
	}

	@Override
	public void modifyRegola(ChecklistRegolaBO checklistRegola, String user) throws ServiceException {
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - modifyRegola: scrittura su database");
		
		Optional<ChecklistRegola> entityOldOptional = _repoCheckRegola.getLastRowById(checklistRegola.get_idChecklistRegola());
		ChecklistRegola entityOld = null;
		if(!entityOldOptional.isPresent()) 
			_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld = entityOldOptional.get();
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repoCheckRegola.save(entityOld);		
			
			// 2) creare riga nuova 
			ChecklistRegola entityNew = checklistRegola.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user);
			entityNew.set_flagValido(1);
			_repoCheckRegola.save(entityNew);	
		}					
		
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - modifyRegola: scrittura su database terminata");
	}

	@Override
	public void deleteRegola(Long id, String user) throws ServiceException {
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - deleteRegola: scrittura su database");
		
		Optional<ChecklistRegola> optionalEntity = _repoCheckRegola.findById(id);
		if(optionalEntity.isPresent()) {
			ChecklistRegola entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoCheckRegola.save(entity);	
		} else {
			throw new ServiceException("Regola Checklist non presente nel sistema");
		}
		
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - deleteRegola: scrittura su database terminata");	
	}

	@Override
	public List<ChecklistRegolaBO> getList() throws ServiceException {
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - getList: inizio lettura  su database");
		
		List<ChecklistRegola> list = _repoCheckRegola.findAllValid();
		List<ChecklistRegolaBO> listBO = new ArrayList<ChecklistRegolaBO>();
		
		Optional<Controllo> optionalControllo = null;
		Controllo c = null;
		for (ChecklistRegola entity : list) {
			optionalControllo = _repoControllo.findValidFromId(entity.get_idControllo());
			if(optionalControllo.isPresent()) {
				c = optionalControllo.get();
			} else 
				throw new ServiceException("enita non presente nel sistema");
			
			listBO.add(new ChecklistRegolaBO(entity, c));
		}	
		_logger.info(GestioneChecklistRegoleServiceImpl.class.getSimpleName() + " - getList: lettura  su database terminata");
		return listBO;
	}

}
