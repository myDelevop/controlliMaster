package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Controllo;


@Repository
public interface IControlloRepository extends JpaRepository<Controllo, Serializable> {
	
	@Query("select c from Controllo c where _flagValido=1 and _flagCancellato=0 and c._idControllo = :idControllo")
	public Optional<Controllo> findValidFromId(@Param("idControllo") Long idControllo);

	@Query("select c from Controllo c where _flagValido=1 and _flagCancellato=0")
	public List<Controllo> findAllValid();

}