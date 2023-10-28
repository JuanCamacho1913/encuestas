package com.co.Apirestencuestas.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Voto_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Opcion_ID")
    private Opcion opcion;

}
