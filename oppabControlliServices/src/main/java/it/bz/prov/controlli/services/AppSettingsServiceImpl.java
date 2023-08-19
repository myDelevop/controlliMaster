package it.bz.prov.controlli.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.AppSettingsBO;
import it.bz.prov.controlli.entities.AppSettings;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAppSettingsService;
import it.bz.prov.controlli.repositories.IAppSettingsRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class AppSettingsServiceImpl implements IAppSettingsService {

	@Autowired
	private IAppSettingsRepository _appSettingsRepo;

	private Logger _logger = Utils.getLogger();

	@Override
	public AppSettingsBO findSetting(String key) throws ServiceException {
		_logger.info("Leggo il settings " + key + " da DB");
		Optional<AppSettings> s = _appSettingsRepo.findBy_key(key);
		if(s.isPresent()){
			_logger.info(AppSettingsServiceImpl.class.getSimpleName() + " - findSetting: Lettura a DB terminata.");
			return new AppSettingsBO(s.get());
		} else throw new ServiceException(AppSettingsServiceImpl.class.getSimpleName() + " - findSetting: Setting non trovato.");
	}
}
