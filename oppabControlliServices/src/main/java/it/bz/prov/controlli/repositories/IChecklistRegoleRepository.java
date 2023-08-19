package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.ChecklistRegola;


@Repository
public interface IChecklistRegoleRepository extends JpaRepository<ChecklistRegola, Serializable> {

	@Query("select case when max(_idChecklistRegola) is null then 0 else max(_idChecklistRegola) end as mmm from ChecklistRegola")
	public Long getMaxIdChecklistRegola();
	
	@Query("select c from ChecklistRegola c where _flagValido=1 and _flagCancellato=0")
	public List<ChecklistRegola> findAllValid();
	
	@Query("select c from ChecklistRegola c where _flagValido=1 and _idChecklistRegola= :_idChecklistRegola")
	public Optional<ChecklistRegola> getLastRowById(@Param("_idChecklistRegola") Long _idChecklistRegola);
}
