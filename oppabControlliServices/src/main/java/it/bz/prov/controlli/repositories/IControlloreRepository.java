package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.Controllore;

@Repository
public interface IControlloreRepository extends JpaRepository<Controllore, Serializable> {
	
	@Query("select case when max(_idControllore) is null then 0 else max(_idControllore) end as mmm from Controllore")
	public Long getMaxIdControllore();
	
	@Query("select c from Controllore c where _flagValido=1 and _flagCancellato=0")
	public List<Controllore> findAllValid();
	
	@Query("select c from Controllore c where _flagValido=1 and c._idControllore= :_idControllore")
	public Controllore getLastRowById(@Param("_idControllore") Long _idControllore);

}
