package com.co.Apirestencuestas.repositories;

import com.co.Apirestencuestas.model.Encuesta;
import com.co.Apirestencuestas.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
}
