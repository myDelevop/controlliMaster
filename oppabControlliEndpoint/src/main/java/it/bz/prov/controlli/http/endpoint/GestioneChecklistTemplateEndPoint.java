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

import it.bz.prov.controlli.bo.ChecklistTemplateBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneChecklistTemplateService;
import it.bz.prov.controlli.iservices.IGestioneLogService;
import it.bz.prov.controlli.util.Utils;
import it.bz.prov.controlli.utility.Utility;


@RestController
@RequestMapping({"/gestioneChecklistTemplate"})
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600, allowCredentials = "true")
public class GestioneChecklistTemplateEndPoint {
	
	@Autowired
	public IGestioneChecklistTemplateService _gesCheckTemplateService;
	@Autowired
	public IGestioneLogService _gesLogService;

	Logger _logger = Utils.getLogger();
	
	
	/****************************************************************************/
	/*				GESTIONE CHECKLIST TEMPLATE									*/
	/****************************************************************************/
	
	
	@PostMapping(value = "/insertCheckTemplate")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ChecklistTemplateBO> insertChecklistTemplate(@RequestBody ChecklistTemplateBO c) {
        try {
        	String user = Utility.getAuthenticatedUser();
        	_gesLogService.writeSysLog(user, "INSERT CHECKLIST TEMPLATE", "");
        	if(c!= null) {
        		c.set_userCreazione(user);
        		_gesCheckTemplateService.insertChecklistTemplate(c);
        	}
        	else _logger.error(GestioneChecklistTemplateEndPoint.class.getSimpleName() + ": insertChecklistTemplate - parametro nullo");
		} catch (ServiceException e) {
			_logger.error("Errore durante l' inserimento del checklist template nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@PutMapping("/modifyCheckTemplate")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ChecklistTemplateBO> modifyChecklistTemplate(@RequestBody ChecklistTemplateBO c) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "MODIFY CHECKLIST TEMPLATE", "");
    		_gesCheckTemplateService.modifyChecklistTemplate(c, user);
    	} catch (ServiceException e) {
    		_logger.error("Errore durante l' aggiornamento del checklist template nel sistema");
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteCheckTemplate/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ChecklistTemplateBO> deleteChecklistTemplate(@PathVariable Long id) {
    	try {
    		String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "DELETE CHECKLIST TEMPLATE", "ID:" + id);
    		_gesCheckTemplateService.deleteChecklistTemplate(id, user);
		} catch (ServiceException e) {
			_logger.error("Errore durante la cancellazione del checklist template dal sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	@GetMapping(path = {"/getListCheckTemplate"})
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ChecklistTemplateBO>> getListChecklistTemplate() {
    	List<ChecklistTemplateBO> res = null;
		try {
			String user = Utility.getAuthenticatedUser();
    		_gesLogService.writeSysLog(user, "GET LIST CHECKLIST TEMPLATE", "");
			res= _gesCheckTemplateService.getListTemplate();
			_logger.info(this.getClass().getSimpleName() + " - getListChecklistTemplate : n. righe: " + res.size());
		} catch (ServiceException e) {
			_logger.error("Errore durante la lettura dei checklist template validi nel sistema");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
	

}
