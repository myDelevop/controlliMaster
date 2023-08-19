package it.bz.prov.controlli.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.ControlloBO;
import it.bz.prov.controlli.entities.Controllo;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneControlliService;
import it.bz.prov.controlli.repositories.IControlloRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class GestioneControlliServiceImpl implements IGestioneControlliService {
	
	private Logger _logger = Utils.getLogger();
	
	@Autowired
	private IControlloRepository _repoContr;

	
	@Override
	public List<ControlloBO> getListControlli() throws ServiceException {
		_logger.info(GestioneControlliServiceImpl.class.getSimpleName() + " - getListControlli: inizio lettura  su database");
		
		List<Controllo> list = _repoContr.findAllValid();
		List<ControlloBO> listBO = new ArrayList<ControlloBO>();
		for (Controllo entity : list) {
			ControlloBO c = new ControlloBO(entity);
			listBO.add(c);
		}
		_logger.info(GestioneControlliServiceImpl.class.getSimpleName() + " - getListControlli: lettura  su database terminata");
		return listBO;
	}
	
}
