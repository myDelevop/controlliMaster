package it.bz.prov.controlli.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.entities.SysLog;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.repositories.ISysLogRepository;

@Service
@Transactional
public class GestioneLogServiceImpl  implements IGestioneLogService {
	
	@Autowired
	private ISysLogRepository _repoSysLog;

	
	
	@Override
	public void writeSysLog(String user, String tipoOperazione, String descr) throws ServiceException {
		SysLog log = new SysLog(user, tipoOperazione, descr);
		_repoSysLog.save(log);
	}

}
