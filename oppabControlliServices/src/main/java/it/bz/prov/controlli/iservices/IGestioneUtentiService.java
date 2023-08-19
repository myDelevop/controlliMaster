package it.bz.prov.controlli.iservices;

import java.util.List;

import it.bz.prov.controlli.bo.GruppoBO;
import it.bz.prov.controlli.bo.GruppoGruppoBO;
import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.bo.UtenteGruppoBO;
import it.bz.prov.controlli.exception.ServiceException;

public interface IGestioneUtentiService {	
	
	/****************** UTENTE *************************/
	
	public abstract UtenteBO insertUtente(UtenteBO utente) throws ServiceException;
	public abstract UtenteBO updateUtente(UtenteBO utente, String user) throws ServiceException;
	public abstract Long deleteUtente(Long id, Long idUtente, String user) throws ServiceException;
	public abstract List<UtenteBO> findAllUtentiValidi() throws ServiceException;
	public abstract UtenteBO findUtenteByID(Long id) throws ServiceException;
	public abstract UtenteBO findUtenteByUsername(String username) throws ServiceException;
	
	/****************** GRUPPO *************************/

	public abstract GruppoBO insertGruppo(GruppoBO gruppo) throws ServiceException;
	public abstract GruppoBO updateGruppo(GruppoBO gruppo, String user) throws ServiceException;
	public abstract Long deleteGruppo(Long id, Long idGruppo, String user) throws ServiceException;
	public abstract List<GruppoBO> findAllGruppiValidi() throws ServiceException;
	public abstract GruppoBO findGruppoByID(Long id) throws ServiceException;
	
	/****************** UTENTE-GRUPPO *************************/
	
	public abstract UtenteGruppoBO insertUtenteGruppo(UtenteGruppoBO utenteGruppo) throws ServiceException;
	/**
	 * Commentata perchè al momento non si da la possibilità di fare update dal portale. Si deve cancellare e ricreare la relazione
	 */
//	public abstract UtenteGruppoBO updateUtenteGruppo(UtenteGruppoBO utenteGruppo) throws ServiceException;
	public abstract Long deleteUtenteGruppo(Long id, String user) throws ServiceException;
	public abstract List<UtenteGruppoBO > findAllUtentiGruppiValidi() throws ServiceException;
	public abstract UtenteGruppoBO findUtenteGruppoByID(Long id) throws ServiceException;

	public abstract List<GruppoBO> findGruppiValidiFromUtente(UtenteBO utente) throws ServiceException;
	public abstract List<UtenteBO> findUtentiValidiFromGruppo(GruppoBO utente) throws ServiceException;	
	
	/****************** GRUPPO-GRUPPO *************************/

	public abstract GruppoGruppoBO insertGruppoGruppo(GruppoGruppoBO gruppoGruppo) throws ServiceException;
	/**
	 * Commentata perchè al momento non si da la possibilità di fare update dal portale. Si deve cancellare e ricreare la relazione
	 */
//	public abstract GruppoGruppoBO updateGruppoGruppo(GruppoGruppoBO gruppoGruppo, String user) throws ServiceException;
	public abstract Long deleteGruppoGruppo(Long id, String user) throws ServiceException;
	public abstract List<GruppoGruppoBO> findAllGruppiGruppiValidi() throws ServiceException;
	public abstract GruppoGruppoBO findGruppoGruppoByID(Long id) throws ServiceException;
}
