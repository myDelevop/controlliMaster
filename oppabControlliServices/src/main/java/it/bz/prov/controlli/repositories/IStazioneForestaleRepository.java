package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.StazioneForestale;

@Repository
public interface IStazioneForestaleRepository extends JpaRepository<StazioneForestale, Serializable> {
	
	@Query("select case when max(_idStazioneForestale) is null then 0 else max(_idStazioneForestale) end as mmm from StazioneForestale")
	public Long getMaxIdStazioneForestale();
	
	@Query("select s from StazioneForestale s where _flagValido=1 and _flagCancellato=0")
	public List<StazioneForestale> findAllValid();
	
	@Query("select s from StazioneForestale s where _flagValido=1 and s._idStazioneForestale= :_idStazioneForestale")
	public Optional<StazioneForestale> findValidFromId(@Param("_idStazioneForestale") long _idStazioneForestale);
	
	@Query("select sf from StazioneForestaleMapping sfm "
			+ "left join StazioneForestale sf on sfm._stazioneForestale=sf._nome and sf._flagValido=1 and sf._flagCancellato=0 "
			+ "where sfm._flagValido=1 and sfm._flagCancellato=0 and sfm._comuneIT = :comuneIT")
	public StazioneForestale findStazioneForestaleForComuneIT(@Param("comuneIT") String comuneIT);
	
	@Query("select sf from StazioneForestaleMapping sfm "
			+ "left join StazioneForestale sf on sfm._stazioneForestale=sf._nome and sf._flagValido=1 and sf._flagCancellato=0 "
			+ "where sfm._flagValido=1 and sfm._flagCancellato=0 and sfm._comuneDE = :comuneDE")
	public StazioneForestale findStazioneForestaleForComuneDE(@Param("comuneDE") String comuneDE);
	

}
