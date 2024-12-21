package com.example.demo.service;

import com.example.demo.model.Genero;
import com.example.demo.model.Genero;
import com.google.common.collect.ImmutableList;

public interface GeneroService {
    ImmutableList<Genero> ObtenerGeneros();
    void agregarGenero(Genero genero);
    void actualizarGenero(Genero genero);

    Genero obtenerPorId(int id);

    Boolean existeGeneroPorId(Integer id);

    Long contarGenero();

    void eliminarPorId(int id);
    void  eliminarGenero(Genero genero);


}
