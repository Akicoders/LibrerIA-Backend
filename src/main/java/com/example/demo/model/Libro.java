package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genero genero;

    @Column(name = "isbn", length = 20)
    private String isbn;

    @Lob
    @Column(name = "description")
    private String descripcion;

    @Column(name = "pulication_date")
    private LocalDate fechaPublicacion;

    @Column(name = "pdf_link")
    private String enlacePdf;

}