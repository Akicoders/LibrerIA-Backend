package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_genero", nullable = false, length = 100)
    private String nombreGenero;

}