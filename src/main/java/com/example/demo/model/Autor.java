package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "biography")
    private String biografia;

    @Column(name = "birthdate" , nullable = false )
    private Date fechaNacimiento;

    @Column(name = "nationality" , nullable = false)
    private String nationalidad;



}