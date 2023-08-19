package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.StazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;

/**
 * interfaccia che espone i servizi per la gestione delle stazioni forestali
 * @author bpettazzoni
 *
 */
public interface IGestioneStazioniForestaliService {

	/**
	 * Creazione di una nuova stazione forestale
	 * @param stazioneForestale
	 * @throws ServiceException
	 */
	public abstract void insertStazioneForestale(StazioneForestaleBO stazioneForestale) throws ServiceException;
	
	/**
	 * modifica di una stazione forestale esistente
	 * @param stazioneForestale
	 * @param user é l'utente che ha fatto la modifica
	 * @throws ServiceException
	 */
	public abstract void modifyStazioneForestale(StazioneForestaleBO stazioneForestale, String user) throws ServiceException;
	
	/**
	 * Cancellazione di una stazione forestale esistente
	 * @param id é l'id riga (pk)
	 * @param idStazioneForestale é l'id applicativo
	 * @param user * l'utente che ha richiesto la cancellazione
	 * @throws ServiceException
	 */
	public abstract void deleteStazioneForestale(Long id, Long idStazioneForestale, String user) throws ServiceException;
	
	/**
	 * restituisce la lista di stazioni forestali valide
	 * @return List<StazioneForestaleBO>
	 */
	public abstract List<StazioneForestaleBO> getList() throws ServiceException;
}
