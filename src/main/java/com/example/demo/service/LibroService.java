package com.example.demo.service;

import com.example.demo.model.Libro;
import com.google.common.collect.ImmutableList;

public interface LibroService {

    ImmutableList<Libro> ObtenerLibros();
    void agregarLibro(Libro libro);
    void actualizarLibro(Libro libro);

    Libro obtenerPorId(int id);

    Boolean existeLibroPorId(Integer id);

    Long contarLibro();

    void eliminarPorId(int id);
    void  eliminarLibro(Libro libro);


}
