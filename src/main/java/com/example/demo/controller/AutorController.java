package com.example.demo.controller;

import com.example.demo.model.Autor;
import com.example.demo.service.AutorService;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(
            summary = "Obtener todos los autores",
            description = "Devuelve una lista con todos los autores registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/autores")
    public ImmutableList<Autor> getAutores() {
        return autorService.ObtenerAutores();
    }

    @Operation(
            summary = "Agregar un nuevo autor",
            description = "Registra un nuevo autor en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Autor agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles del autor a agregar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "nombre": "Gabriel García Márquez",
                                "fechaNacimiento": "1927-03-06",
                                "nacionalidad": "Colombiana"
                            }
                            """
            ))
    )
    @PostMapping("/autor/agregar")
    public void agregarAutor(@RequestBody Autor autor) {
        autorService.agregarAutor(autor);
    }

    @Operation(
            summary = "Actualizar un autor existente",
            description = "Actualiza los detalles de un autor existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/autor/actualizar")
    public void actualizarAutor(@RequestBody Autor autor) {
        autorService.actualizarAutor(autor);
    }

    @Operation(
            summary = "Verificar si un autor existe",
            description = "Comprueba si un autor existe en la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de la verificación obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
    })
    @GetMapping("/autor/existe/{id}")
    public boolean existeAutor(@PathVariable int id) {
        return autorService.existeAutorPorId(id);
    }

    @Operation(
            summary = "Obtener un autor por ID",
            description = "Devuelve los detalles de un autor específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Autor.class))
            }),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/autor/{id}")
    public Autor getAutor(@PathVariable int id) {
        return autorService.obtenerPorId(id);
    }

    @Operation(
            summary = "Contar todos los autores",
            description = "Devuelve el número total de autores registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de autores obtenido exitosamente")
    })
    @GetMapping("/autores/contar")
    public Long contarAutores() {
        return autorService.contarAutor();
    }

    @Operation(
            summary = "Eliminar un autor por ID",
            description = "Elimina un autor específico de la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Autor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
    })
    @DeleteMapping("/autor/{id}")
    public void eliminarAutor(@PathVariable int id) {
        autorService.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar un autor",
            description = "Elimina un autor basado en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Autor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content)
    })
    @DeleteMapping("/autor/eliminar")
    public void eliminarAutor(@RequestBody Autor autor) {
        autorService.eliminarAutor(autor);
    }
}
