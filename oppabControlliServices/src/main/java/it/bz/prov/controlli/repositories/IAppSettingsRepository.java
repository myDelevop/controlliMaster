package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.bz.prov.controlli.entities.AppSettings;;


@Repository
public interface IAppSettingsRepository extends JpaRepository<AppSettings, Serializable> {
    @Query("SELECT S FROM AppSettings S WHERE S._key = :key")
    Optional<AppSettings> findBy_key(@Param("key") String key);
}
