package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.ChecklistRegolaBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneChecklistRegoleService {
	
	/**
	 * inserimento di una nuova regola
	 * @param checklistRegola
	 * @throws ServiceException
	 */
	public abstract void insertRegola(ChecklistRegolaBO checklistRegola) throws ServiceException;
	

	/**
	 * Modifica di una regola esistente
	 * @param checklistRegola
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyRegola(ChecklistRegolaBO checklistRegola, String user) throws ServiceException;
	
	
	/**
	 * Cancellazione di una regola esistente
	 * @param id é l'id riga da cancellare
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteRegola(Long id, String user) throws ServiceException;
	
	/**
	 * restituisce la lista delle regole
	 * @return List<ChecklistRegolaBO>
	 * @throws ServiceException
	 */
	public abstract List<ChecklistRegolaBO> getList() throws ServiceException;
	
}
