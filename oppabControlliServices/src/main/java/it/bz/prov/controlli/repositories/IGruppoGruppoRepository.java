package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.GruppoGruppo;

@Repository
public interface IGruppoGruppoRepository extends JpaRepository<GruppoGruppo, Serializable>{

	@Query("select case when max(_idRel) is null then 0 else max(_idRel) end as mmm from GruppoGruppo")
	public Long getMaxIdRel();
	
	@Query("select gg from GruppoGruppo gg where _flagValido=1 and gg._idRel= :_idRel")
	public Optional<GruppoGruppo> findValidFromId(@Param("_idRel") long _idRel);
	
	@Query("select a from GruppoGruppo a where _flagValido=1 and _flagCancellato=0")
	public List<GruppoGruppo> findAllValid();
	
	@Query("select a from GruppoGruppo a where _flagValido=1 and _flagCancellato=0 and (_idGruppoParent= :_idGruppo or _idGruppoChild = :_idGruppo)")
	public List<GruppoGruppo> findRelForGruppo(@Param("_idGruppo") long _idGruppo);
	
	
}
