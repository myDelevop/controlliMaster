package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.StazioneForestaleMapping;

@Repository
public interface IStazioneForestaleMappingRepository extends JpaRepository<StazioneForestaleMapping, Serializable>{
	
	

}
