package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.AnagSottointervento;


@Repository
public interface IAnagSottointerventoRepository extends JpaRepository<AnagSottointervento, Serializable> {
	
	@Query("select c from AnagSottointervento c where _flagValido=1")
	public List<AnagSottointervento> findAllValid();
	
	@Query("select distinct c._misura from AnagSottointervento c where _flagValido=1")
	public List<String> getListMisura();
	
	@Query("select distinct c._intervento from AnagSottointervento c where _flagValido=1 and _misura= :_misura")
	public List<String> getListInterventoForMisura(@Param("_misura") String _misura);
	
	@Query("select distinct c._sottointervento from AnagSottointervento c where _flagValido=1 and _intervento= :_intervento")
	public List<String> getListSottointerventoForIntervento(@Param("_intervento") String _intervento);

}
