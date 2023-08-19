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

import it.bz.prov.controlli.bo.GruppoBO;
import it.bz.prov.controlli.bo.GruppoGruppoBO;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.bo.UtenteGruppoBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneUtenti"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneUtentiEndpoint {
	@Autowired
	public IGestioneUtentiService _gesUtenteService;
	@Autowired
	public IGestioneLogService _gesLogService;
	
	private Logger _logger = Utils.getLogger();
	
	/*********************************************************************/
	/*					GESTIONE UTENTE									 */
	/*********************************************************************/
		
	@PostMapping(value = "/insertUtente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UtenteBO> insertUtente(@RequestBody UtenteBO utente) {
    	UtenteBO res = null;
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT UTENTE", "UTENTE:" + utente.get_username());
        	utente.set_userCreazione(user);
			res = _gesUtenteService.insertUtente(utente);
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	
	@GetMapping(path = {"/findAllUtentiValidi"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<UtenteBO>> findAllUtentiValidi() {
    	List<UtenteBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "GET LIST UTENTI", "");
			res = _gesUtenteService.findAllUtentiValidi();
		} catch (ServiceException e) {
			_logger.error("Errore durante la ricerca degli utenti nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
	@DeleteMapping("/utente/{ids}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Long> deleteUtente(@PathVariable String ids) {
		Long res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "DELETE UTENTE", "ID:" + ids);
        	String s[] = ids.split("_");
        	Long id = Long.valueOf(s[0]);
        	Long idUtente = Long.valueOf(s[1]);
			res = _gesUtenteService.deleteUtente(id, idUtente, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
	@PutMapping("/updateUtente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UtenteBO> updateUtente(@RequestBody UtenteBO utenteDetails) {
    	UtenteBO res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY UTENTE", "utente:" + utenteDetails.get_username());
    		res = _gesUtenteService.updateUtente(utenteDetails, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento dell'utente nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	

	/*********************************************************************/
	/*					GESTIONE GRUPPO									 */
	/*********************************************************************/
    
	@PostMapping(value = "/insertGruppo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<GruppoBO> insertGruppo(@RequestBody GruppoBO gruppo) {
    	GruppoBO res = null;
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT GRUPPO", "GRUPPO:" + gruppo.get_nomeGruppo());
        	gruppo.set_userCreazione(user);
			res = _gesUtenteService.insertGruppo(gruppo);
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	
	@GetMapping(path = {"/findAllGruppiValidi"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<GruppoBO>> findAllGruppiValidi() {
    	List<GruppoBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "GET LIST GRUPPO", "");
			res = _gesUtenteService.findAllGruppiValidi();
		} catch (ServiceException e) {
			_logger.error("Errore durante la ricerca degli utenti nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
	@DeleteMapping("/gruppo/{ids}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Long> deleteGruppo(@PathVariable String ids) {
    	String s[] = ids.split("_");
    	Long id = Long.valueOf(s[0]);
    	Long idGruppo= Long.valueOf(s[1]);

    	Long res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE GRUPPO", "ID:" + ids);
			res = _gesUtenteService.deleteGruppo(id, idGruppo, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
	@PutMapping("/updateGruppo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<GruppoBO> updateGruppo(/*@PathVariable Long id, */ @RequestBody GruppoBO gruppoDetails) {
    	GruppoBO res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY GRUPPO", "GRUPPO:" + gruppoDetails.get_nomeGruppo());
    		res = _gesUtenteService.updateGruppo(gruppoDetails, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento dell'utente nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
/*    @GetMapping(path = {"/findGruppiFromUser/{id}"})
 * 	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<GruppoBO>> findGruppiFromUser(@PathVariable Long id) {
    	List<GruppoBO> res =null;
		try {
	    	res = gesUtenteService.findGruppiValidiFromUtente(gesUtenteService.findUtenteByID(id));
		} catch (ServiceException e) {
			logger.error("Errore durante la ricerca dei gruppi a partire dall'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
*/
	
	
	/*********************************************************************/
	/*					GESTIONE UTENTE-GRUPPO							 */
	/*********************************************************************/
    
	@GetMapping(path = {"/findAllUtentiGruppiValidi"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UtenteGruppoBO>> findAllUtentiGruppiValidi() {
    	List<UtenteGruppoBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST UTENTE-GRUPPO", "");
			res = _gesUtenteService.findAllUtentiGruppiValidi();
		} catch (ServiceException e) {
			_logger.error("Errore durante la ricerca degli utenti nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	@PostMapping(value = "/insertUtenteGruppo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UtenteGruppoBO> insertUtenteGruppo(@RequestBody UtenteGruppoBO utenteGruppo) {
    	UtenteGruppoBO res = null;
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT UTENTE-GRUPPO", "");
        	utenteGruppo.set_userCreazione(user);
			res = _gesUtenteService.insertUtenteGruppo(utenteGruppo);
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	@DeleteMapping("/utenteGruppo/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Long> deleteUtenteGruppo(@PathVariable Long id) {
    	Long res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE UTENTE-GRUPPO", "ID:" + id);
			res = _gesUtenteService.deleteUtenteGruppo(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione dell'utente nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    
    /**
	 * Commentata perchè al momento non si da la possibilità di fare update dal portale. Si deve cancellare e ricreare la relazione
	 */
//    @PutMapping("/updateUtenteGruppo")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public ResponseEntity<UtenteGruppoBO> updateUtenteGruppo(/*@PathVariable Long id, */ @RequestBody UtenteGruppoBO utenteGruppoDetails) {
//    	UtenteGruppoBO res = null;
//    	try {
//    		res = gesUtenteService.updateUtenteGruppo(utenteGruppoDetails);
//    	} catch (ServiceException e) {
//    		logger.error("Errore durante l' aggiornamento dell'utente nel sistema");
//    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    	}
//    	return new ResponseEntity<>(res, HttpStatus.OK);
//    }

	/*********************************************************************/
	/*					GESTIONE GRUPPO-GRUPPO							 */
	/*********************************************************************/


	@GetMapping(path = {"/findAllGruppiGruppiValidi"})
    public ResponseEntity<List<GruppoGruppoBO>> findAllGruppiGruppiValidi() {
    	List<GruppoGruppoBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST GRUPPO-GRUPPO", "");
			res = _gesUtenteService.findAllGruppiGruppiValidi();
		} catch (ServiceException e) {
			_logger.error("Errore durante la ricerca delle relazioni gruppi-gruppi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
  
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	@PostMapping(value = "/insertGruppoGruppo")
    public ResponseEntity<GruppoGruppoBO> insertGruppoGruppo(@RequestBody GruppoGruppoBO gruppoGruppo) {
		GruppoGruppoBO res = null;
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT GRUPPO-GRUPPO", "");
        	gruppoGruppo.set_userCreazione(user);
			res = _gesUtenteService.insertGruppoGruppo(gruppoGruppo);
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento della relazione gruppo-gruppo nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	@DeleteMapping("/deleteGruppoGruppo/{id}")
    public ResponseEntity<Long> deleteGruppoGruppo(@PathVariable Long id) {
    	Long res = null;
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE GRUPPO-GRUPPO", "ID:" + id);
			res = _gesUtenteService.deleteGruppoGruppo(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione della relazione gruppo-gruppo nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
