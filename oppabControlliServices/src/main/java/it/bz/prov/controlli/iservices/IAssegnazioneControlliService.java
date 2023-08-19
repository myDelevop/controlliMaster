package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.AziendaStazForControlloreBO;
import it.bz.prov.controlli.bo.AziendaStazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;

/**
 * Interfaccia che espone i metodi per la gestione dell'assegnamento dei controlli di ogni cuaa alla stazione forestale di riferimento 
 * e ai relativi controllori
 * 
 * @author bpettazzoni
 *
 */

public interface IAssegnazioneControlliService {
	
	/********************************************************************/
	/* 1) Assegnamento CUAA a Stazione forestale 						*/
	/********************************************************************/
	
	/**
	 * inserimento di una nuova relazione di azienda-stazione forestale
	 * @param asf é l'oggetto relazione di azienda-stazione forestale
	 * @throws ServiceException
	 */
	public abstract void insertAziStazFor(AziendaStazioneForestaleBO asf) throws ServiceException;
	

	/**
	 * Modifica di una associazione esistente di azienda-stazione forestale
	 * @param asf é l'oggetto relazione di azienda-stazione forestale
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyAziStazFor(AziendaStazioneForestaleBO asf, String user) throws ServiceException;
	
	
	/**
	 * Cancellazione di una associazione esistente di azienda-stazione forestale
	 * @param id é l'id riga da cancellare
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteAziStazFor(Long id, String user) throws ServiceException;
	
	/**
	 * restituisce la lista delle associazioni azienda-stazione forestale valide
	 * @return List<AziendaStazioneForestaleBO>
	 * @throws ServiceException
	 */
	public abstract List<AziendaStazioneForestaleBO> getListAziStazFor() throws ServiceException;
	
	
	/**
	 * effettua una assegnazione automatica delle aziende alle stazione forestale di pertinenza in funzione del comune dell'azienda 
	 * @return List<AziendaStazioneForestaleBO> é la lista delle aziende assegnate
	 * @throws ServiceException
	 */
	public abstract List<AziendaStazioneForestaleBO> assegnamentoAutomaticoAziendaStazFor(String user) throws ServiceException;


	/********************************************************************/
	/* 2) Assegnamento CUAA a controllori 								*/
	/********************************************************************/
	
	/**
	 * inserimento di una nuova relazione di azienda-controllore
	 * @param ac é l'oggetto relazione di azienda-controllore
	 * @throws ServiceException
	 */
	public abstract void insertAziContr(AziendaStazForControlloreBO ac) throws ServiceException;
	

	/**
	 * Modifica di una associazione esistente di azienda-controllore
	 * @param ac é l'oggetto relazione di azienda-controllore
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyAziContr(AziendaStazForControlloreBO ac, String user) throws ServiceException;
	
	
	/**
	 * Cancellazione di una associazione esistente di azienda-controllore
	 * @param id é l'id riga da cancellare
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteAziContr(Long id, String user) throws ServiceException;
	
	/**
	 * restituisce la lista delle associazioni azienda-controllore valide
	 * @return List<AziendaStazForControlloreBO>
	 * @throws ServiceException
	 */
	public abstract List<AziendaStazForControlloreBO> getListAziContr() throws ServiceException;
	
	
	
}
