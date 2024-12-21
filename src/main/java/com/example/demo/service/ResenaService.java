package com.example.demo.service;

import com.example.demo.model.Resena;
import com.google.common.collect.ImmutableList;

public interface ResenaService {
    ImmutableList<Resena> ObtenerResenas();
    void agregarResena(Resena resena);
    void actualizarResena(Resena resena);

    Resena obtenerPorId(int id);

    Boolean existeResenaPorId(Integer id);

    Long contarResena();

    void eliminarPorId(int id);
    void  eliminarResena(Resena resena);


}
