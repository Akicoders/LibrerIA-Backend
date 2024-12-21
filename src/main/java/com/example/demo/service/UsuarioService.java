package com.example.demo.service;


import com.example.demo.model.Usuario;
import com.google.common.collect.ImmutableList;

public interface UsuarioService {
    ImmutableList<Usuario> ObtenerUsuarios();
    void agregarUsuario(Usuario usuario);
    void actualizarUsuario( Usuario usuario);

    Usuario obtenerPorId(int id);

    Boolean existeUsuarioPorId(Integer id);

    Long contarUsuario();

    void eliminarPorId(int id);
    void  eliminarUsuario(Usuario usuario);



}
