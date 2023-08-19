package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.AziendaStazForControllore;

@Repository
public interface IAziendaStazForControlloreRepository extends JpaRepository<AziendaStazForControllore, Serializable> {

	@Query("select case when max(_idAziStazForContr) is null then 0 else max(_idAziStazForContr) end as mmm from AziendaStazForControllore")
	public Long getMaxIdAziStazForContr();
	
	@Query("select a from AziendaStazForControllore a where _flagValido=1 and _flagCancellato=0")
	public List<AziendaStazForControllore> findAllValid();
	
	@Query("select ac from AziendaStazForControllore ac where _flagValido=1 and _flagCancellato=0 and _idAziStazForContr = :_idAziStazForContr")
	public AziendaStazForControllore getLastRowById(@Param("_idAziStazForContr") Long _idAziStazForContr);
}
