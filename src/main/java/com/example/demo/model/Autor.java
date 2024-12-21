package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_autor", nullable = false)
    private String nombreAutor;

    @Lob
    @Column(name = "biografia")
    private String biografia;

}