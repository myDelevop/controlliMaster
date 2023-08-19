package it.bz.prov.controlli.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.bz.prov.controlli.entities.Ruolo;

@Repository
public interface IRuoloRepository extends JpaRepository<Ruolo, Serializable> {

    @Query("SELECT r FROM Ruolo r WHERE _flagValido = 1 AND _flagCancellato = 0 AND r._idRuolo = :idRuolo")
    List<Ruolo> findBy_idRuolo(@Param("idRuolo") Long idRuolo);

    @Query("SELECT r._ruolo FROM Ruolo r WHERE r._flagValido = 1 AND r._flagCancellato = 0 AND r._idRuolo = :idRuolo")
    List<String> findBy_idRuoloStringList(@Param("idRuolo") Long idRuolo);

    @Query("SELECT DISTINCT r._ruolo FROM Ruolo r WHERE _flagValido = 1 AND _flagCancellato = 0 AND r._idRuolo IN :idRuoli")
    List<String> findBy_idRuoli(@Param("idRuoli") Iterable<Long> idRuoli);
}
