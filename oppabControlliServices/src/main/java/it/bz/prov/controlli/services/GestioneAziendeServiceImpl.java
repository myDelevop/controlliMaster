package it.bz.prov.controlli.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.bz.prov.controlli.bo.AziendaBO;
import it.bz.prov.controlli.entities.Azienda;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneAziendeService;
import it.bz.prov.controlli.repositories.IAziendaRepository;
import it.bz.prov.controlli.util.Utils;

@Service
@Transactional
public class GestioneAziendeServiceImpl implements IGestioneAziendeService {

	@Autowired
	private IAziendaRepository _aziendaRepo;

	private Logger _logger = Utils.getLogger();



	@Override
	public List<AziendaBO> findAllAziendeValide() throws ServiceException {
		_logger.info(GestioneAziendeServiceImpl.class.getSimpleName() + " - findAllAziendeValide: inizio lettura  su database");
		List<AziendaBO> aziendeBO = new ArrayList<AziendaBO>();

		List<Azienda> listaAziende = _aziendaRepo.findAllValid();
		aziendeBO = new ArrayList<AziendaBO>();
		
		for(Azienda a: listaAziende) {
			aziendeBO.add(new AziendaBO(a));
		}
		_logger.info(GestioneAziendeServiceImpl.class.getSimpleName() + " - findAllAziendeValide: fine lettura  su database");
		
		return aziendeBO;
	}

}
