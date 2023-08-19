package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.AziendaBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneAziendeService {	
	
	public abstract List<AziendaBO> findAllAziendeValide() throws ServiceException;
	
}
