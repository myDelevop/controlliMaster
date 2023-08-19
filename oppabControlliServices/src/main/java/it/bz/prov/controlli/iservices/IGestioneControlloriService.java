package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.ControlloreBO;
import it.bz.prov.controlli.bo.ControlloreStazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;

/**
 * interfaccia che espone i servizi per la gestione dei controllori
 * @author bpettazzoni
 *
 */
public interface IGestioneControlloriService {
	
	/*************************************************************************/
	/*				ANAGRAFICA CONTROLLORE									 */
	/*************************************************************************/
	
	
	/**
	 * Creazione di un nuova controllore
	 * @param controllore
	 * @throws ServiceException
	 */
	public abstract void insertControllore(ControlloreBO controllore) throws ServiceException;
	
	/**
	 * modifica di una stazione forestale esistente
	 * @param controllore
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyControllore(ControlloreBO controllore, String user) throws ServiceException;
	
	/**
	 * Cancellazione di una stazione forestale esistente
	 * @param id é l'id riga (pk)
	 * @param idControllore é l'id applicativo
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteControllore(Long id, Long idControllore, String user) throws ServiceException;
	
	/**
	 * restituisce la lista di controllori validi
	 * @return List<ControlloreBO>
	 */
	public abstract List<ControlloreBO> getListControllori() throws ServiceException;

	

	/*************************************************************************/
	/*				ASSEGNAMENTO CONTROLLORE A STAZIONE FORESTALE			 */
	/*************************************************************************/
	
	/**
	 * Crea un nuovo assegnamento tra il controllore e la stazione forestale
	 * @param controlloreStazioneForestale
	 * @throws ServiceException
	 */
	public abstract void insertAssegnamentoControllore(ControlloreStazioneForestaleBO controlloreStazioneForestale) throws ServiceException;
	
	/**
	 * Modifica un assegnamento esistente tra il controllore e la stazione forestale
	 * @param controlloreStazioneForestale
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyAssegnamentoControllore(ControlloreStazioneForestaleBO controlloreStazioneForestale, String user) throws ServiceException;
	
	/**
	 * Cancella un assegnamento esistente tra il controllore e la stazione forestale
	 * @param id é l'id riga (pk)
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteAssegnamentoControllore(Long id, String user) throws ServiceException;
	
	
	/**
	 * restituisce la lista di controllori validi assegnati alle stazioni forestali
	 * @return List<ControlloreStazioneForestaleBO>
	 */
	public abstract List<ControlloreStazioneForestaleBO> getListControlloriStazFor() throws ServiceException;
}
