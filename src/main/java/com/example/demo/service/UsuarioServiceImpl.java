package com.example.demo.service;

import com.example.demo.business.UsuarioRepository;
import com.example.demo.model.Usuario;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    // @Autowired
    // BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public ImmutableList<Usuario> ObtenerUsuarios() {
        return ImmutableList.copyOf(usuarioRepository.findAll());
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
       // String passwordEncode = bCryptPasswordEncoder.encode(usuario.getContrasena());
        //usuario.setContrasena(passwordEncode);
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(int id) {
        return usuarioRepository.findById(id).get() ;
    }

    @Override
    public Boolean existeUsuarioPorId(Integer id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public Long contarUsuario() {
        return usuarioRepository.count();
    }

    @Override
    public void eliminarPorId(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
