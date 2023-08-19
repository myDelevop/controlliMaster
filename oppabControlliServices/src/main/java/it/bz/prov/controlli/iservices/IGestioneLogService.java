package it.bz.prov.controlli.iservices;

import it.bz.prov.controlli.exception.ServiceException;

/**
 * Interfaccia che espone i metodi per la gestione del logging sulla tabelle SYS_
 * @author bpettazzoni
 *
 */
public interface IGestioneLogService {
	
	/**
	 * scrittura sulla tabella SYS_LOG
	 * @param user
	 * @param tipoOperazione
	 * @param descr é una descrizione estesa dell'operazione, con eventuali dettagli
	 * @throws ServiceException
	 */
	public abstract void writeSysLog(String user, String tipoOperazione, String descr) throws ServiceException;

}
