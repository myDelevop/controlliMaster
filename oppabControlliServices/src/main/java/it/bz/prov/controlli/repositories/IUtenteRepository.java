package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Serializable> {

	@Query("select case when max(_idUtente) is null then 0 else max(_idUtente) end as mmm from Utente")
	public Long getMaxID();

	@Query("select a from Utente a where _flagValido=1 and _flagCancellato=0")
	public List<Utente> findAllValid();

	@Query("select a from Utente a where _flagValido=1 and _flagCancellato=0 and a._idUtente in :utentiIds")
	public List<Utente> findValidFromIds(@Param("utentiIds") Iterable<Long> utentiIds);

	@Query("select a from Utente a where _flagValido=1 and _flagCancellato=0 and a._idUtente = :idUtente")
	public Optional<Utente> findValidFromId(@Param("idUtente") Long idUtente);

	@Query("SELECT A FROM Utente A WHERE _flagValido = 1 AND _flagCancellato = 0 AND A._username = :username")
	public Optional<Utente> findValidFromUsername(@Param("username") String username);
}
