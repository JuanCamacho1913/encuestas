package com.co.Apirestencuestas.repositories;

import com.co.Apirestencuestas.model.Encuesta;
import com.co.Apirestencuestas.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "select v.* from Opcion o, Voto v where o.ENCUESTA_ID = ?1 and v.OPCION_ID = o.OPCION_ID",nativeQuery = true)
    public Iterable<Voto> findByEncuesta(Long encuestaId);

}
