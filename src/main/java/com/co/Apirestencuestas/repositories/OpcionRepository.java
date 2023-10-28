package com.co.Apirestencuestas.repositories;

import com.co.Apirestencuestas.model.Encuesta;
import com.co.Apirestencuestas.model.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Long> {
}
