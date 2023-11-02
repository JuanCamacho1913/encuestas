package com.co.Apirestencuestas.controllers;

import com.co.Apirestencuestas.dto.OpcionCount;
import com.co.Apirestencuestas.dto.VotoResult;
import com.co.Apirestencuestas.model.Voto;
import com.co.Apirestencuestas.repositories.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Autowired
    private VotoRepository votoRepository;

    @GetMapping("/calcularResultado")
    public ResponseEntity<?> calcularResultado(@RequestParam Long encuestaId) {
        VotoResult votoResult = new VotoResult();
        Iterable<Voto> votos = votoRepository.findByEncuesta(encuestaId);

        //Algoritmo para contar votos
        int totalVotos = 0;
        Map<Long, OpcionCount> tempMap = new HashMap<Long, OpcionCount>();

        for (Voto v : votos) {
            totalVotos++;

            //Obtenemos la OpcionCount correspondiente a esta opcion
            OpcionCount opcionCount = tempMap.get(v.getOpcion().getId());
            if (opcionCount == null) {
                opcionCount = new OpcionCount();
                opcionCount.setOpcionId(v.getOpcion().getId());
                tempMap.put(v.getOpcion().getId(), opcionCount);
            }
            opcionCount.setCount(opcionCount.getCount() + 1);
        }

        votoResult.setTotalVotos(totalVotos);
        votoResult.setResults(tempMap.values());

        return new ResponseEntity<>(votoResult, HttpStatus.OK);
    }

}
