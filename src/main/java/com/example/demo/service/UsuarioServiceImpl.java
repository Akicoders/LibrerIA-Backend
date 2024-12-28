package com.example.demo.service;

import com.example.demo.business.UsuarioRepository;
import com.example.demo.model.Usuario;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public ImmutableList<Usuario> ObtenerUsuarios() {
        try {
            log.info("Se llamó al método obtenerUsuarios()");
            return ImmutableList.copyOf(usuarioRepository.findAll());
        } catch (Exception e) {
            log.error("Error al obtener la lista de usuarios: {}", e.getMessage(), e);
            return ImmutableList.of();
        }
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        try {
            log.info("Agregando usuario con email: {}", usuario.getEmail());
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            log.error("Error al agregar usuario: {}", e.getMessage(), e);
            throw new RuntimeException("No se pudo agregar el usuario");
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getId())) {
            try {
                log.info("Actualizando usuario con ID: {}", usuario.getId());
                usuarioRepository.save(usuario);
            } catch (Exception e) {
                log.error("Error al actualizar usuario: {}", e.getMessage(), e);
                throw new RuntimeException("No se pudo actualizar el usuario");
            }
        } else {
            log.warn("El usuario con ID: {} no existe", usuario.getId());
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public Usuario obtenerPorId(int id) {
        log.info("Buscando Usuario con ID: {}" + id);
        return usuarioRepository.findById(id).orElseThrow(() ->
        {
            log.warn("El usuario con ID: {} no encontrado", id);
            return new RuntimeException("El usuario no encontrado");
        }) ;
    }

    @Override
    public Boolean existeUsuarioPorId(Integer id) {
        log.info("Verificando existencia del Usuario con ID: {}" + id);
        return usuarioRepository.existsById(id);
    }

    @Override
    public Long contarUsuario() {
        log.info("Buscando usuario con ID");
        return usuarioRepository.count();
    }

    @Override
    public void eliminarPorId(int id) {

        if (usuarioRepository.existsById(id)) {
         try {
             log.info("Eliminando usuario con ID: {}", id);
             usuarioRepository.deleteById(id);
         }catch (Exception e) {
             log.error("Error al eliminar el usuario: {}", e.getMessage(), e);
             throw new RuntimeException("No se pudo eliminar el usuario");
         }
        }else {
            log.warn("El usuario con ID: {} no existe", id);
            throw new IllegalArgumentException("El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        try {
            log.info("Eliminando Usuario con ID: {}", usuario.getId());
            usuarioRepository.delete(usuario);
        }catch (Exception e) {
            log.warn("Error al eliminar el usuario: {}", e.getMessage(), e);
            throw new RuntimeException("No se pudo eliminar el usuario");
        }

    }
}
