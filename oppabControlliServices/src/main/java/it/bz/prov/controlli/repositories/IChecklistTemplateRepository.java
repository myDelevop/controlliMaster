package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.bz.prov.controlli.entities.ChecklistTemplate;

public interface IChecklistTemplateRepository extends JpaRepository<ChecklistTemplate, Serializable>{
	
	@Query("select case when max(_idChecklistTemplate) is null then 0 else max(_idChecklistTemplate) end as mmm from ChecklistTemplate")
	public Integer getMaxIdChecklistTemplate();
	
	@Query("select c from ChecklistTemplate c where _flagValido=1 and _flagCancellato=0")
	public List<ChecklistTemplate> findAllValid();
	
	@Query("select c from ChecklistTemplate c where _flagValido=1 and _idChecklistTemplate= :_idChecklistTemplate")
	public ChecklistTemplate getLastRowById(@Param("_idChecklistTemplate") int _idChecklistTemplate);

}
