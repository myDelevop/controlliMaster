package it.bz.prov.controlli.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.ChecklistTemplateBO;
import it.bz.prov.controlli.entities.ChecklistTemplate;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneChecklistTemplateService;
import it.bz.prov.controlli.repositories.IChecklistTemplateRepository;
import it.bz.prov.controlli.util.Utils;

/**
 * Classe per la gestione delle operazioni da fare sui template delle checklist (di testata e dettaglio)
 * @author bpettazzoni
 *
 */
@Service
@Transactional
public class GestioneChecklistTemplateServiceImpl implements IGestioneChecklistTemplateService {
	
	@Autowired
	private IChecklistTemplateRepository _repoCheckTemplate;
	
	private Logger _logger = Utils.getLogger();
	
	
	/**********************************************************************************************/
	/*									CHECKLIST TEMPLATE										  */	
	/**********************************************************************************************/

	@Override
	public void insertChecklistTemplate(ChecklistTemplateBO checklistTemplate) throws ServiceException {
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - insertChecklistTemplate: scrittura su database");
		
		ChecklistTemplate entity = checklistTemplate.convertToEntity();
		entity.set_id(0L);
		entity.set_idChecklistTemplate(_repoCheckTemplate.getMaxIdChecklistTemplate() + 1);
		entity.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
		entity.set_flagValido(1);
		entity.set_flagCancellato(0);
		_repoCheckTemplate.save(entity);
		
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - insertChecklistTemplate: scrittura su database terminata");
	}

	@Override
	public void modifyChecklistTemplate(ChecklistTemplateBO checklistTemplate, String user) throws ServiceException {
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - modifyChecklistTemplate: scrittura su database");
		
		ChecklistTemplate entityOld = _repoCheckTemplate.getLastRowById(checklistTemplate.get_idChecklistTemplate());
		if(entityOld == null)
			_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - riga non trovata su db");
		else {
			// 1) invalidare riga vecchia	
			entityOld.set_dataModifica(new Timestamp(System.currentTimeMillis()));
			entityOld.set_userModifica(user);
			entityOld.set_flagValido(0);
			_repoCheckTemplate.save(entityOld);		
			
			// 2) creare riga nuova 
			ChecklistTemplate entityNew = checklistTemplate.convertToEntity();
			entityNew.set_id(0L);
			entityNew.set_dataCreazione(new Timestamp(System.currentTimeMillis()));
			entityNew.set_userCreazione(user);
			entityNew.set_flagValido(1);
			_repoCheckTemplate.save(entityNew);	
		}					
		
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - modifyChecklistTemplate: scrittura su database terminata");		
	}

	@Override
	public void deleteChecklistTemplate(Long id, String user) throws ServiceException {
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - deleteChecklistTemplate: scrittura su database");
		
		Optional<ChecklistTemplate> optionalEntity = _repoCheckTemplate.findById(id);
		if(optionalEntity.isPresent()) {
			ChecklistTemplate entity = optionalEntity.get();
			entity.set_dataCancellazione(new Timestamp(System.currentTimeMillis()));
			entity.set_userCancellazione(user);
			entity.set_flagCancellato(1);
			_repoCheckTemplate.save(entity);	
		} else {
			throw new ServiceException("Template Checklist non presente nel sistema");
		}
		
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - deleteRegola: scrittura su database terminata");	
	}

	@Override
	public List<ChecklistTemplateBO> getListTemplate() throws ServiceException {
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - getListTemplate: inizio lettura  su database");
		
		List<ChecklistTemplate> list = _repoCheckTemplate.findAllValid();
		List<ChecklistTemplateBO> listBO = new ArrayList<ChecklistTemplateBO>();
		for (ChecklistTemplate entity : list) {
			ChecklistTemplateBO c = new ChecklistTemplateBO(entity);
			listBO.add(c);
		}
		_logger.info(GestioneChecklistTemplateServiceImpl.class.getSimpleName() + " - getListTemplate: lettura  su database terminata");
		return listBO;
	}

}
