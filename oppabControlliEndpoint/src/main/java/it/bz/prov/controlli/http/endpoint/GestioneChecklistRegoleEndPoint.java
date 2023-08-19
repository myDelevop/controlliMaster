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

import it.bz.prov.controlli.bo.ChecklistRegolaBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneChecklistRegoleService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Constants;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;

@RestController
@RequestMapping({"/gestioneChecklistRegole"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneChecklistRegoleEndPoint {

	@Autowired
	public IGestioneChecklistRegoleService _gesCheckRegoleService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	
	
	@PostMapping(value = "/insertCheckRegole")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ChecklistRegolaBO> insertChecklistRegola(@RequestBody ChecklistRegolaBO c) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT CHECKLIST REGOLA", "");
        	if(c!= null) {
        		if(c.get_anno() == null || c.get_anno() == 0) {
        			c.set_anno(c.get_campagna());
        		}
        		c.set_userCreazione(user);
        		_gesCheckRegoleService.insertRegola(c);
        	}
        	else _logger.error(GestioneChecklistRegoleEndPoint.class.getSimpleName() + ": insertChecklistRegola - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento della regola checklist nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyCheckRegole")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ChecklistRegolaBO> modifyChecklistRegola(@RequestBody ChecklistRegolaBO c) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY CHECKLIST REGOLA", "");
    		_gesCheckRegoleService.modifyRegola(c, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento della regola checklist nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteCheckRegola/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ChecklistRegolaBO> deleteChecklistRegola(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE CHECKLIST REGOLA", "ID:" + id);
    		_gesCheckRegoleService.deleteRegola(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione della regola checklist dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListCheckRegole"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ChecklistRegolaBO>> getListChecklistTemplate() {
    	List<ChecklistRegolaBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST CHECKLIST REGOLA", "");
			res= _gesCheckRegoleService.getList();
			_logger.info(this.getClass().getSimpleName() + " - getListChecklistTemplate : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura delle regole checklist validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
}
