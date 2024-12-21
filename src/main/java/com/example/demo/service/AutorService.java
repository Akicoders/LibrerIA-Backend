package com.example.demo.service;

import com.example.demo.model.Autor;
import com.google.common.collect.ImmutableList;

public interface AutorService {
    ImmutableList<Autor> ObtenerAutores();
    void agregarAutor(Autor autor);
    void actualizarAutor(Autor autor);

    Autor obtenerPorId(int id);

    Boolean existeAutorPorId(Integer id);

    Long contarAutor();

    void eliminarPorId(int id);
    void  eliminarAutor(Autor autor);
}
