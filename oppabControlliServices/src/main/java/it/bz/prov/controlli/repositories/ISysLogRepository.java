package it.bz.prov.controlli.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.SysLog;

@Repository
public interface ISysLogRepository extends JpaRepository<SysLog, Serializable>  {

}
