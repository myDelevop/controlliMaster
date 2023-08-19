package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Gruppo;

@Repository
public interface IGruppoRepository extends JpaRepository<Gruppo, Serializable> {
	
	@Query("select case when max(_idGruppo) is null then 0 else max(_idGruppo) end from Gruppo")
	public Long getMaxID();
	
	@Query("select a from Gruppo a where _flagValido=1 and _flagCancellato=0")
	public List<Gruppo> findAllValid();

	@Query("select a from Gruppo a where _flagValido=1 and _flagCancellato=0 and a._idGruppo in :groupIds")
	public List<Gruppo> findValidFromIds(@Param("groupIds") Iterable<Long> groupIds);

	@Query("select a from Gruppo a where _flagValido=1 and _flagCancellato=0 and a._idGruppo = :idGruppo")
	public Optional<Gruppo> findValidFromId(@Param("idGruppo") Long idGruppo);

	
}
