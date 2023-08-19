package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Azienda;
import it.bz.prov.controlli.entities.Gruppo;


@Repository
public interface IAziendaRepository extends JpaRepository<Azienda, Serializable> {
	
	@Query("select a from Azienda a where _flagValido=1 and _flagCancellato=0 and a._idAzienda = :idAzienda")
	public Optional<Azienda> findValidFromId(@Param("idAzienda") Long idAzienda);
	
	@Query("select a from Azienda a where _flagValido=1 and _flagCancellato=0")
	public List<Azienda> findAllValid();

	
	@Query(value = 
			"SELECT A.* FROM DWH_CAMPIONE C LEFT JOIN DWH_AZIENDA A ON A.CUAA=C.CUAA AND A.CAMPAGNA=2019 AND A.FLAG_VALIDO=1 AND A.FLAG_CANCELLATO=0 LEFT JOIN AZIENDA_STAZ_FOR ASF ON C.CUAA=ASF.CUAA AND ASF.CAMPAGNA=2019 AND ASF.FLAG_VALIDO=1 AND ASF.FLAG_CANCELLATO=0 WHERE C.FLAG_VALIDO=1 AND C.FLAG_CANCELLATO=0 AND C.CAMPAGNA=2019 AND C.FLAG_CAMPIONE=1 AND ASF.ID_STAZIONE_FORESTALE IS NULL", 
			nativeQuery=true)
	public List<Azienda> getAziendeDaAssegnareForCampagna(@Param("campagna") int campagna);


}
