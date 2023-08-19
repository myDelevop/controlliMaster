package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.Azienda;
import it.bz.prov.controlli.entities.AziendaStazioneForestale;

@Repository
public interface IAziendaStazioneForestaleRepository extends JpaRepository<AziendaStazioneForestale, Serializable> {
	
	@Query("select case when max(_idAziStazFor) is null then 0 else max(_idAziStazFor) end as mmm from AziendaStazioneForestale")
	public Long getMaxIdAziStazFor();
	
	@Query("select a from AziendaStazioneForestale a where _flagValido=1 and _flagCancellato=0")
	public List<AziendaStazioneForestale> findAllValid();
	
	@Query("select asf from AziendaStazioneForestale asf where _flagValido=1 and _flagCancellato=0 and _idAziStazFor = :_idAziStazFor")
	public AziendaStazioneForestale getLastRowById(@Param("_idAziStazFor") Long _idAziStazFor);
	
	
	@Query("select a from Campione c left join Azienda a on a._cuaa=c._cuaa and a._campagna= :campagna and a._flagValido=1 and a._flagCancellato=0 "
			+ "left join AziendaStazioneForestale asf on c._cuaa=asf._cuaa and asf._campagna= :campagna and asf._flagValido=1 and asf._flagCancellato=0 "
			+ "where c._flagValido=1 and c._flagCancellato=0 and c._flagCampione=1 and c._campagna= :campagna and asf._idStazioneForestale is null")
	public List<Azienda> getAziendeDaAssegnareForCampagna(@Param("campagna") int campagna);
	/*
		SELECT A.*
		FROM DWH_CAMPIONE C
		    LEFT JOIN DWH_AZIENDA A
		    ON A.CUAA=C.CUAA AND A.CAMPAGNA=2019 AND A.FLAG_VALIDO=1 AND A.FLAG_CANCELLATO=0
		        LEFT JOIN AZIENDA_STAZ_FOR ASF 
		        ON C.CUAA=ASF.CUAA AND ASF.CAMPAGNA=2019 AND ASF.FLAG_VALIDO=1 AND ASF.FLAG_CANCELLATO=0
		WHERE C.FLAG_VALIDO=1 AND C.FLAG_CANCELLATO=0
		AND C.CAMPAGNA=2019 AND C.FLAG_CAMPIONE=1
		AND ASF.ID_STAZIONE_FORESTALE IS NULL;

	 * 
	 * */
	

}
