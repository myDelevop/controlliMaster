package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.ChecklistTemplateBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneChecklistTemplateService {
	
	
	/**********************************************************************************************/
	/*									CHECKLIST TEMPLATE										  */	
	/**********************************************************************************************/
	
	/**
	 * inserimento di una nuova riga di template checklist
	 * @param checklistTemplate
	 * @throws ServiceException
	 */
	public abstract void insertChecklistTemplate(ChecklistTemplateBO checklistTemplate) throws ServiceException;

	/**
	 * modifica di un template esistente
	 * @param checklistTemplate
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyChecklistTemplate(ChecklistTemplateBO checklistTemplate, String user) throws ServiceException;

	/**
	 * cancellazione di un template di checklist esistente
	 * @param checklistTemplate
	 * @param user é l'utente che ha richeisto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteChecklistTemplate(Long id, String user) throws ServiceException;
	
	/**
	 * restituzione di tutte le checklist template valide
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<ChecklistTemplateBO> getListTemplate() throws ServiceException;

}
