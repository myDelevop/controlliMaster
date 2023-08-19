package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Anagrafica;

@Repository
public interface IAnagraficaRepository extends JpaRepository<Anagrafica, Serializable>{
	
	@Query("select case when max(_idAnagrafica) is null then 0 else max(_idAnagrafica) end as mmm from Anagrafica")
	public Integer getMaxIdAnagrafica();
	
	@Query("select c from Anagrafica c where _flagValido=1 and _flagCancellato=0")
	public List<Anagrafica> findAllValid();
	
	@Query("select c._valoreIT from Anagrafica c where _flagValido=1 and _flagCancellato=0 and _chiave = :_chiave "
			+ "and _annoValiditaInizio <= :_anno and _annoValiditaFine >= :_anno")
	public List<String> findAllValidForChiaveLangIT(@Param("_chiave") String _chiave, @Param("_anno") int _anno);
	
	@Query("select c._valoreDE from Anagrafica c where _flagValido=1 and _flagCancellato=0 and _chiave = :_chiave "
			+ "and _annoValiditaInizio <= :_anno and _annoValiditaFine >= :_anno")
	public List<String> findAllValidForChiaveLangDE(@Param("_chiave") String _chiave, @Param("_anno") int _anno);
	
	@Query("select c from Anagrafica c where _flagValido=1 and c._idAnagrafica= :_idAnagrafica")
	public Anagrafica getLastRowById(@Param("_idAnagrafica") int _idAnagrafica);

}
