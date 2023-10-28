package com.co.Apirestencuestas.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Encuesta_ID")
    private Long id;

    @Column(name = "pregunta")
    private String pregunta;

    @OneToMany(cascade = CascadeType.ALL)                                       //CascadeType.ALL quiere decir que cuando se elimine una encuesta se eliminara tambien las opciones
    @JoinColumn(name = "Encuesta_ID")
    @OrderBy                                                                    //@OrderBy Indica que se va a ordenar por opciones
    private Set<Opcion> opciones;
}
