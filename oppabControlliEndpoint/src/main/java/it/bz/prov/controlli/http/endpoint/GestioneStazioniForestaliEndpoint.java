package it.bz.prov.controlli.http.endpoint;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.bz.prov.controlli.bo.StazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.iservices.IGestioneStazioniForestaliService;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneStazioniForestali"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneStazioniForestaliEndpoint {
	
	@Autowired
	public IGestioneStazioniForestaliService _gesStazForService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	@PostMapping(value = "/insert")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StazioneForestaleBO> insertStazioneForestale(@RequestBody StazioneForestaleBO s) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT STAZIONE FORESTALE", "");
        	if(s!= null) {
        		s.set_userCreazione(user);
        		if(s.get_annoValiditaInizio() == null || s.get_annoValiditaInizio() == 0) {
        			s.set_annoValiditaInizio(Constants.CURRENT_YEAR);
        		}
        		if(s.get_annoValiditaFine() == null || s.get_annoValiditaFine() == 0) {
        			s.set_annoValiditaFine(Constants.LAST_YEAR);
        		}
        		
        		_gesStazForService.insertStazioneForestale(s);
        	}
        	else _logger.error(IGestioneStazioniForestaliService.class + ": insertStazioneForestale - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento della stazione forestale nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PutMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    ResponseEntity<StazioneForestaleBO> modifyStazioneForestale(@RequestBody StazioneForestaleBO s) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY STAZIONE FORESTALE", "");
    		_gesStazForService.modifyStazioneForestale(s, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento della stazione forestale nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/delete/{ids}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StazioneForestaleBO> deleteStazioneForestale(@PathVariable String ids) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE STAZIONE FORESTALE", "ID:" + ids);
    		String s[]=ids.split("_");
    		Long id= Long.valueOf(s[0]);
    		Long idStazFor= Long.valueOf(s[1]);
    		_gesStazForService.deleteStazioneForestale(id, idStazFor, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione della stazione forestale dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getList"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<StazioneForestaleBO>> getList() {
    	List<StazioneForestaleBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "GET LIST STAZIONE FORESTALE", "");
			res= _gesStazForService.getList();
			_logger.info(this.getClass().getSimpleName() + " - getList : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura delle stazioni forestali valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
}
