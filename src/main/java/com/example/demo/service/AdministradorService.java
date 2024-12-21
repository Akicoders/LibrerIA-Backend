package com.example.demo.service;


import com.example.demo.model.Administrador;
import com.google.common.collect.ImmutableList;

public interface AdministradorService {
    ImmutableList<Administrador> ObtenerAdministradores();
    void agregarAdministrador(Administrador administrador);
    void actualizarAdministrador(Administrador administrador);

    Administrador obtenerPorId(int id);

    Boolean existeAdministradorPorId(Integer id);

    Long contarAdministrador();

    void eliminarPorId(int id);
    void  eliminarAdministrador(Administrador administrador);
}
