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

import it.bz.prov.controlli.bo.AziendaStazForControlloreBO;
import it.bz.prov.controlli.bo.AziendaStazioneForestaleBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IAssegnazioneControlliService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/assegnazioneControlli"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class AssegnazioneControlliEndpoint {
	
	@Autowired
	public IAssegnazioneControlliService _gesContrService;
	@Autowired
	public IGestioneLogService _gesLogService;
	
	Logger _logger = Utils.getLogger();
	
	
	/********************************************************************/
	/* 1) Assegnamento CUAA a Stazione forestale 						*/
	/********************************************************************/
	
	
	@PostMapping(value = "/insertAziStazFor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazioneForestaleBO> insertAziStazFor(@RequestBody AziendaStazioneForestaleBO a) {
        try {
        	String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "INSERT AZIENDA-STAZIONE FORESTALE", "");
        	if(a!= null) {
        		a.set_userCreazione(user);
        		_gesContrService.insertAziStazFor(a);
        	}
        	else _logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": insertAziStazFor - parametro nullo");
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": insertAziStazFor - Errore durante l' inserimento nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyAziStazFor")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazioneForestaleBO> modifyAziStazFor(@RequestBody AziendaStazioneForestaleBO a) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY AZIENDA-STAZIONE FORESTALE", "");
    		_gesContrService.modifyAziStazFor(a, user);
    	} catch (ServiceException e) {
    		_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() +": modifyAziStazFor - Errore durante l' aggiornamento nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteAziStazFor/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazioneForestaleBO> deleteAziStazFor(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE AZIENDA-STAZIONE FORESTALE", "ID:" + id);
    		_gesContrService.deleteAziStazFor(id, user);
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": deleteAziStazFor - Errore durante la cancellazione dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListAziStazFor"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AziendaStazioneForestaleBO>> getListAziStazFor() {
    	List<AziendaStazioneForestaleBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "GET LIST AZIENDA-STAZIONE FORESTALE", "");
			res= _gesContrService.getListAziStazFor();
			_logger.info(AssegnazioneControlliEndpoint.class.getSimpleName() +" - getListAziStazFor : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": getListAziStazFor - Errore durante la lettura nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/assegnamentoAutomaticoAziendeStazFor"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AziendaStazioneForestaleBO>> assegnamentoAutomaticoAziendeStazFor() {
    	List<AziendaStazioneForestaleBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "ASSEGNAMENTO AUTOMATICO AZIENDA-STAZIONE FORESTALE", "");
			res= _gesContrService.assegnamentoAutomaticoAziendaStazFor(user);
			_logger.info(AssegnazioneControlliEndpoint.class.getSimpleName() + " - assegnamentoAutomaticoAziendeStazFor : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() +": assegnamentoAutomaticoAziendeStazFor - "
					+ "Errore durante l assegnamento automatico delle associazioni azienda-stazioni forestali valide nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	/********************************************************************/
	/* 2) Assegnamento CUAA a controllori 								*/
	/********************************************************************/
	
	
	@PostMapping(value = "/insertAziContr")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazForControlloreBO> insertAziContr(@RequestBody AziendaStazForControlloreBO a) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT AZIENDA-CONTROLLORI", "");
        	if(a!= null) {        		
        		a.set_userCreazione(user);
        		_gesContrService.insertAziContr(a);
        	}
        	else _logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": insertAziContr - parametro nullo");
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": insertAziContr - Errore durante l' inserimento nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyAziContr")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazForControlloreBO> modifyAziContr(@RequestBody AziendaStazForControlloreBO a) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY AZIENDA-CONTROLLORI", "");
    		_gesContrService.modifyAziContr(a, user);
    	} catch (ServiceException e) {
    		_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() +": modifyAziContr - Errore durante l' aggiornamento nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteAziContr/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AziendaStazForControlloreBO> deleteAziContr(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE AZIENDA-CONTROLLORI", "ID:" + id);
    		_gesContrService.deleteAziContr(id, user);
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": deleteAziContr - Errore durante la cancellazione dell'anagrafica dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListAziContr"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<AziendaStazForControlloreBO>> getListAziContr() {
    	List<AziendaStazForControlloreBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
			_gesLogService.writeSysLog(user, "GET LIST AZIENDA-CONTROLLORI", "");
			res= _gesContrService.getListAziContr();
			_logger.info(AssegnazioneControlliEndpoint.class.getSimpleName() +" - getListAziContr : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error(AssegnazioneControlliEndpoint.class.getSimpleName() + ": getListAziContr - Errore durante la lettura nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
}
