package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Campione;


@Repository
public interface ICampioneRepository extends JpaRepository<Campione, Serializable> {
	
	@Query("select distinct c._cuaa from Campione c where _flagValido=1 and _flagCancellato=0 and _flagCampione=1 and _campagna= :_campagna")
	public List<String> findCUAAperCampagna(@Param("_campagna") int _campagna);

}
