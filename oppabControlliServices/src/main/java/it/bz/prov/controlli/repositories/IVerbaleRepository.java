package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.Verbale;

@Repository
public interface IVerbaleRepository extends JpaRepository<Verbale, Serializable> {
	
	@Query("select case when max(_idVerbale) is null then 0 else max(_idVerbale) end as mmm from Verbale")
	public Long getMaxIdVerbale();


	@Query("select v from Verbale v where _flagValido=1 and _idControllo= :_idControllo")
	public Optional<Verbale> findValidFromControllo(@Param("_idControllo") long _idControllo);
	

}
