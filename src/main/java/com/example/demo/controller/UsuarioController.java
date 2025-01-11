package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Devuelve una lista completa de todos los usuarios registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            }),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
    @GetMapping("/usuarios")
    public ImmutableList<Usuario> getUsuarios() {
        return usuarioService.ObtenerUsuarios();
    }

    @Operation(
            summary = "Agregar un nuevo usuario",
            description = "Permite registrar un nuevo usuario en la base de datos. La fecha de registro se asigna automáticamente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles del usuario a registrar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "nombre": "Juan",
                                "apellido": "Pérez",
                                "email": "juan.perez@gmail.com",
                                "contrasena": "password",
                            }
                            """
            ))
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_USER') ")
    @PostMapping("/usuario/agregar")
    public ResponseEntity<String> agregarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setContrasena(BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Datos Invalidos", HttpStatus.BAD_REQUEST);
        }
        usuarioService.agregarUsuario(usuario);
        return new ResponseEntity<>("Usuario agregado exitosamente", HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza los datos de un usuario existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_USER') ")
    @PostMapping("/usuario/actualizar")
    public void actualizarUsuario(@RequestBody Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
    }

    @Operation(
            summary = "Verificar si un usuario existe",
            description = "Comprueba si un usuario existe en la base de datos dado su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Se verificó la existencia del usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')  ")
    @GetMapping("/usuario/existe/{id}")
    public boolean existeUsuario(@PathVariable int id) {
        return usuarioService.existeUsuarioPorId(id);
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve los detalles de un usuario específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            }),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_USER') ")
    @GetMapping("usuario/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return usuarioService.obtenerPorId(id);
    }

    @Operation(
            summary = "Contar el total de usuarios",
            description = "Devuelve la cantidad total de usuarios registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de usuarios obtenido exitosamente")
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_USER') ")
    @GetMapping("/usuarios/todos")
    public Long contarUsuarios() {
        return usuarioService.contarUsuario();
    }

    @Operation(
            summary = "Eliminar un usuario por ID",
            description = "Elimina un usuario específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') ")
    @DeleteMapping("usuario/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario específico basado en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')  ")
    @DeleteMapping("usuario/eliminar")
    public void eliminarUsuarios(@RequestBody Usuario usuario) {
        usuarioService.eliminarUsuario(usuario);
    }
}
