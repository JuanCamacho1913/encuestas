package com.co.Apirestencuestas.controllers;

import com.co.Apirestencuestas.exception.ResourceNotFoundException;
import com.co.Apirestencuestas.model.Encuesta;
import com.co.Apirestencuestas.model.Opcion;
import com.co.Apirestencuestas.repositories.EncuestaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "encuestas")
public class EncuestaController {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Encuesta>> obtenerEncuestas() {
        return new ResponseEntity<>(encuestaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{encuestaId}")
    public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId) {
        verifyEncuesta(encuestaId);
        Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);                          //Optional<> Indica que va a retornar un true si existe o un false si no
        if (encuesta.isPresent()) {
            return new ResponseEntity<>(encuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> crearEncuesta(@Valid @RequestBody Encuesta encuesta) {
        encuesta = encuestaRepository.save(encuesta);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newEncuestaUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(encuesta.getId()).toUri();
        httpHeaders.setLocation(newEncuestaUri);

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{encuestaId}")
    public ResponseEntity<?> actualizarEncuesta(@Valid @PathVariable Long encuestaId, @RequestBody Encuesta encuesta) {
        verifyEncuesta(encuestaId);
        encuesta.setId(encuestaId);
        encuestaRepository.save(encuesta);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{encuestaId}")
    public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId) {
        verifyEncuesta(encuestaId);
        encuestaRepository.deleteById(encuestaId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyEncuesta(@PathVariable Long encuestaId) {                                                  //Se encarga de verificar si una encuesta existe o no existe
        Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);
        if (!encuesta.isPresent())
            throw new ResourceNotFoundException("Encuesta con el ID: " + encuestaId + ", no encontrado");
    }
}
