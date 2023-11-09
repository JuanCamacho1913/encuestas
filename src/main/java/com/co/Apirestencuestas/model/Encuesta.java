package com.co.Apirestencuestas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Column(name = "ENCUESTA_ID")
    private Long id;

    @Column(name = "pregunta")
    @NotEmpty                                                                   //@NotEmpty indica que este campo no puede estar vacio.
    private String pregunta;

    @OneToMany(cascade = CascadeType.ALL)                                       //CascadeType.ALL quiere decir que cuando se elimine una encuesta se eliminara tambien las opciones.
    @JoinColumn(name = "ENCUESTA_ID")
    @OrderBy                                                                    //@OrderBy Indica que se va a ordenar por opciones.
    @Size(min = 2, max = 6)                                                     //@Size() indica el minimo y maximo de opciones que pueden haber.
    private Set<Opcion> opciones;
}
