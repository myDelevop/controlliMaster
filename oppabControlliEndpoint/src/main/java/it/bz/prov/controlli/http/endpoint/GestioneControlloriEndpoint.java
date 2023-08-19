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

import it.bz.prov.controlli.bo.ControlloreBO;
import it.bz.prov.controlli.bo.ControlloreStazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneControlloriService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneControllori"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneControlloriEndpoint {	
	
	@Autowired
	public IGestioneControlloriService _gesContrService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	
	/****************************************************************************/
	/*				GESTIONE ANAGRAFICA CONTROLLORE								*/
	/****************************************************************************/
	
	
	@PostMapping(value = "/insertContr")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ControlloreBO> insertControllore(@RequestBody ControlloreBO c) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT CONTROLLORE", "");
        	if(c!= null) {
        		c.set_userCreazione(user);
        		_gesContrService.insertControllore(c);
        	} 
        	else _logger.error(GestioneControlloriEndpoint.class.getSimpleName() + ": insertControllore - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento del controllore nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyContr")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ControlloreBO> modifyControllore(@RequestBody ControlloreBO c) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY CONTROLLORE", "");
    		_gesContrService.modifyControllore(c, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento del controllore nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteContr/{ids}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ControlloreBO> deleteControllore(@PathVariable String ids) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE CONTROLLORE", "ID:" + ids);
    		String s[]=ids.split("_");
    		Long id= Long.valueOf(s[0]);
    		Long idControllore= Long.valueOf(s[1]);
    		_gesContrService.deleteControllore(id, idControllore, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione del controllore dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListContr"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ControlloreBO>> getListControllori() {
    	List<ControlloreBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "get list CONTROLLORE", "");
			res= _gesContrService.getListControllori();
			_logger.info(this.getClass().getSimpleName() + " - getListControllori : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura dei controllori validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	/****************************************************************************/
	/*			GESTIONE ASSEGNAMENTO CONTROLLORE A STAZIONE FORESTALE			*/
	/****************************************************************************/

	
	@PostMapping(value = "/insertContrStazFor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ControlloreStazioneForestaleBO> insertControlloreStazFor(@RequestBody ControlloreStazioneForestaleBO c) {
        try {
        	String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "INSERT CONTROLLORE-STAZIONE FORESTALE", "");
        	if(c!= null) {
        		if(c.get_annoValiditaInizio() == null || c.get_annoValiditaInizio() == 0) {
        			c.set_annoValiditaInizio(Constants.CURRENT_YEAR);
        		}
        		if(c.get_annoValiditaFine() == null || c.get_annoValiditaFine() == 0) {
        			c.set_annoValiditaFine(Constants.LAST_YEAR);
        		}
        		c.set_userCreazione(user);
        		_gesContrService.insertAssegnamentoControllore(c);
        	}
        	else _logger.error(GestioneControlloriEndpoint.class.getSimpleName() + ": insertControlloreStazFor - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l'inserimento dell'assegnamento del controllore alla stazioen forestale nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyContrStazFor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    ResponseEntity<ControlloreStazioneForestaleBO> modifyControlloreStazFor(@RequestBody ControlloreStazioneForestaleBO c) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY CONTROLLORE-STAZIONE FORESTALE", "");
    		_gesContrService.modifyAssegnamentoControllore(c, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l'aggiornamento dell'assegnamento del controllore alla stazione forestale nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteContrStazFor/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ControlloreStazioneForestaleBO> deleteControlloreStazFor(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE CONTROLLORE-STAZIONE FORESTALE", "ID:" + id);
    		_gesContrService.deleteAssegnamentoControllore(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione dell'assegnamento del controllore alla stazioen forestale nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListContrStazFor"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ControlloreStazioneForestaleBO>> getListControlloriStazFor() {
    	List<ControlloreStazioneForestaleBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST CONTROLLORE-STAZIONE FORESTALE", "");
			res= _gesContrService.getListControlloriStazFor();
			_logger.info(this.getClass().getSimpleName() + " - getListControlloriStazFor : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura delle associazioni controllori-stazioni forestali valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
