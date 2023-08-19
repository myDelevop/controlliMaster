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

import it.bz.prov.controlli.bo.AnagraficaBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneAnagraficaService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneAnagrafica"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneAnagraficaEndPoint {
	
	@Autowired
	public IGestioneAnagraficaService _gesAnagraficaService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	
	/**********************************************************************/
	/*					GESTIONE TABELLA APP_ANAGRAFICA					  */
	/**********************************************************************/
	
	@PostMapping(value = "/insert")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AnagraficaBO> insertAnagrafica(@RequestBody AnagraficaBO a) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT ANAGRAFICA", "");
        	if(a!= null) {
        		if(a.get_annoValiditaInizio() == null || a.get_annoValiditaInizio() == 0) {
        			a.set_annoValiditaInizio(Constants.CURRENT_YEAR);
        		}
        		if(a.get_annoValiditaFine() == null || a.get_annoValiditaFine() == 0) {
        			a.set_annoValiditaFine(Constants.LAST_YEAR);
        		}
        		a.set_userCreazione(user);
        		_gesAnagraficaService.insertAnagrafica(a);
        	}
        	else _logger.error(GestioneAnagraficaEndPoint.class.getSimpleName() + ": GestioneAnagraficaEndPoint - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento dell'anagrafica nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AnagraficaBO> modifyAnagrafica(@RequestBody AnagraficaBO a) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY ANAGRAFICA", "");
    		_gesAnagraficaService.modifyAnagrafica(a, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento dell'anagrafica nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AnagraficaBO> deleteAnagrafica(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE ANAGRAFICA", "");
    		_gesAnagraficaService.deleteAnagrafica(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione dell'anagrafica dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getList"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AnagraficaBO>> getListAnagrafica() {
    	List<AnagraficaBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "GET LIST ANAGRAFICA", "");
			res= _gesAnagraficaService.getList();
			_logger.info(this.getClass().getSimpleName() +" - getListAnagrafica : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura della anagrafiche valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	@GetMapping(path = {"/getListForChiave/{chiave}/{language}"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<String>> getListAnagraficaForChiave(@PathVariable String chiave, @PathVariable String language) {
    	List<String> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "GET LIST ANAGRAFICA FOR CHIAVE", "chiave: " + chiave + " - language:" + language);
			res= _gesAnagraficaService.getListForChiave(chiave, language);
			_logger.info(this.getClass().getSimpleName() + " - getListAnagraficaForChiave : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura della anagrafiche valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	
	/**********************************************************************/
	/*					LETTURA TABELLA ANAG_SOTTOINTERVENTO			  */
	/**********************************************************************/
	
	
	@GetMapping(path = {"/getListAnagrMisura"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<String>> getListAnagrMisura() {
    	List<String> res = null;
		try {
			res= _gesAnagraficaService.getListMisura();
			System.out.println("getListAnagrMisura - n. righe:" + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura delle misure valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	@GetMapping(path = {"/getListAnagrIntervento/{misura}"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<String>> getListAnagrIntervento(@PathVariable String misura) {
    	List<String> res = null;
		try {
			res= _gesAnagraficaService.getListIntervento(misura);
			System.out.println("getListAnagrIntervento - n. righe:" + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura degli interventi per misura validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	@GetMapping(path = {"/getListAnagrSottointervento/{intervento}"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<String>> getListAnagrSottointervento(@PathVariable String intervento) {
    	List<String> res = null;
		try {
			res= _gesAnagraficaService.getListSottointervento(intervento);
			System.out.println("getListAnagrSottointervento - n. righe:" + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura dei sottointerventi per interventi validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	

}
