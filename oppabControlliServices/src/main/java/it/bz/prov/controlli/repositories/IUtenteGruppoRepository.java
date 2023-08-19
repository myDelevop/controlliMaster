package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Gruppo;
import it.bz.prov.controlli.entities.Utente;
import it.bz.prov.controlli.entities.UtenteGruppo;

@Repository
public interface IUtenteGruppoRepository extends JpaRepository<UtenteGruppo, Serializable> {

	@Query("select case when max(_idRel) is null then 0 else max(_idRel) end from UtenteGruppo")
	public Long getMaxID();
	
	@Query("select a from UtenteGruppo a where _flagValido=1 and _flagCancellato=0")
	public List<UtenteGruppo> findAllValid();

	@Query("select a from UtenteGruppo a where _flagValido = 1 and _flagCancellato = 0 and _idUtente = :_idUtente")
	public List<UtenteGruppo> findValidFromUserId(@Param("_idUtente") Long _idUtente);
	
	@Query("select a from UtenteGruppo a where _flagValido = 1 and _flagCancellato = 0 and _idGruppo = :_idGruppo")
	public List<UtenteGruppo> findValidFromGroupId(@Param("_idGruppo") Long _idGruppo);

	@Query("select a from UtenteGruppo a where _flagValido = 1 and _flagCancellato = 0 and _idUtente = :_idUtente and _idGruppo = :_idGruppo")
	public Optional<UtenteGruppo> findValidFromUserIdAndGroupId(@Param("_idUtente") Long _idUtente, @Param("_idGruppo") Long _idGruppo);
	
	@Query("select g from UtenteGruppo ug join Gruppo g on ug._idGruppo = g._idGruppo and g._flagValido=1 and g._flagCancellato=0 where g._idGruppo is not null and ug._idUtente = :idUtente")
	public List<Gruppo> findGruppiValidFromUserId(@Param("idUtente") Long idUtente);

	@Query("select u from UtenteGruppo ug join Utente u on ug._idUtente = u._idUtente and u._flagValido=1 and u._flagCancellato=0 where u._idUtente is not null and ug._idGruppo = :idGruppo")
	public List<Utente> findUtentiValidFromGroupId(@Param("idGruppo") Long idGruppo);
	

}
