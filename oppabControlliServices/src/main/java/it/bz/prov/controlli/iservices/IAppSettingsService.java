package it.bz.prov.controlli.iservices;

import it.bz.prov.controlli.bo.AppSettingsBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IAppSettingsService {	
	AppSettingsBO findSetting(String key) throws ServiceException;	
}
