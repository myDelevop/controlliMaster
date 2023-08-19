package it.bz.prov.controlli.http.endpoint;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.bz.prov.controlli.bo.ControlloBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneControlliService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneControlli"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneControlliEndpoint {	
	
	@Autowired
	public IGestioneControlliService _gesContrService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	
	@GetMapping(path = {"/getListControlli"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ControlloBO>> getListControlli() {
    	List<ControlloBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST CONTROLLI", "");
			res= _gesContrService.getListControlli();
			_logger.info(this.getClass().getSimpleName() + " - getListControlli : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura dei controlli validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
