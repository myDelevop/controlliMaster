package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.AnagraficaBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneAnagraficaService {
	
	/**********************************************************************/
	/*					GESTIONE TABELLA APP_ANAGRAFICA					  */
	/**********************************************************************/
	
	/**
	 * inserimento nuova riga di anagrafica
	 * @param anagrafica
	 * @throws ServiceException
	 */
	public abstract void insertAnagrafica(AnagraficaBO anagrafica) throws ServiceException;
	

	/**
	 * modifica di una riga di anagrafica esistente
	 * @param anagrafica
	 * @param user é l'utente che ha richiesto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyAnagrafica(AnagraficaBO anagrafica, String user) throws ServiceException;
	
	/**
	 * cancellazione di una riga di anagrafica
	 * @param id é l'id riga da cancellare
	 * @param user é l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteAnagrafica(Long id, String user) throws ServiceException;
	
	/**
	 * restituzione della lista di tutte le anagrafiche valide
	 * @return List<AnagraficaBO> é la lista degli oggetti
	 * @throws ServiceException
	 */
	public abstract List<AnagraficaBO> getList() throws ServiceException;
	
	/**
	 * restituisce la lista di valori validi per una determinata anagrafica 
	 * (flag_valido = 1 e anno corrente compreso tra anno di inizio e fine validità)
	 * @param chiave
	 * @param lingua
	 * @return List<String> é la lista dei valori
	 * @throws ServiceException
	 */
	public abstract List<String> getListForChiave (String chiave, String lingua) throws ServiceException;
	
	
	/**********************************************************************/
	/*					LETTURA TABELLA ANAG_SOTTOINTERVENTO			  */
	/**********************************************************************/

	/**
	 * restituisce la lista di misure che ci sono nella tabella di anagrafica
	 * @return List<String>
	 * @throws ServiceException
	 */
	public abstract List<String> getListMisura() throws ServiceException;

	/**
	 * restituisce la lista degli interventi per una data misura
	 * @param misura
	 * @return List<String>
	 * @throws ServiceException
	 */
	public abstract List<String> getListIntervento(String misura) throws ServiceException;
	
	/**
	 * restituisce la lista dei sottointerventi per un dato intervento
	 * @param intervento
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<String> getListSottointervento(String intervento) throws ServiceException;
	
}
