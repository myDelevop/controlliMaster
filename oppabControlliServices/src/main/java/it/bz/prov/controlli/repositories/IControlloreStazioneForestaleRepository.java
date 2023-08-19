package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.ControlloreStazioneForestale;

@Repository
public interface IControlloreStazioneForestaleRepository extends JpaRepository<ControlloreStazioneForestale, Serializable> {
	
	@Query("select case when max(_idContrStazFor) is null then 0 else max(_idContrStazFor) end as mmm from ControlloreStazioneForestale")
	public Long getMaxIdControlloreStazFor();
	
	@Query("select c from ControlloreStazioneForestale c where _flagValido=1 and _flagCancellato=0")
	public List<ControlloreStazioneForestale> findAllValid();
	
	@Query("select c from ControlloreStazioneForestale c where _flagValido=1 and _idContrStazFor= :_idContrStazFor")
	public ControlloreStazioneForestale getLastRowById(@Param("_idContrStazFor") Long _idContrStazFor);

	@Query("select c from ControlloreStazioneForestale c where _flagValido=1 and _flagCancellato=0 and _idStazioneForestale= :_idStazioneForestale")
	public List<ControlloreStazioneForestale> findAllValidForStazFor(@Param("_idStazioneForestale") Long _idStazioneForestale);
	
	@Query("select c from ControlloreStazioneForestale c where _flagValido=1 and _flagCancellato=0 and _idControllore= :_idControllore")
	public List<ControlloreStazioneForestale> findAllValidForContr(@Param("_idControllore") Long _idControllore);
	
}
