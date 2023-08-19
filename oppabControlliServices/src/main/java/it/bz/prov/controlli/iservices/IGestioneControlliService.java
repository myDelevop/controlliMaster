package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.ControlloBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneControlliService {
	
	public abstract List<ControlloBO> getListControlli() throws ServiceException;
}
