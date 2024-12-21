package com.example.demo.controller;

import com.example.demo.model.Genero;
import com.example.demo.service.GeneroService;
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
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Operation(
            summary = "Obtener todos los géneros",
            description = "Devuelve una lista de todos los géneros disponibles en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de géneros obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Genero.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/generos")
    public ImmutableList<Genero> getGeneros() {
        return generoService.ObtenerGeneros();
    }

    @Operation(
            summary = "Agregar un nuevo género",
            description = "Registra un nuevo género en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Género agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles del género a agregar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "nombre": "Ficción",
                                "descripcion": "Género literario que incluye obras imaginativas."
                            }
                            """
            ))
    )
    @PostMapping("/genero/agregar")
    public void agregarGenero(@RequestBody Genero genero) {
        generoService.agregarGenero(genero);
    }

    @Operation(
            summary = "Actualizar un género existente",
            description = "Actualiza los detalles de un género existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Género actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/genero/actualizar")
    public void actualizarGenero(@RequestBody Genero genero) {
        generoService.actualizarGenero(genero);
    }

    @Operation(
            summary = "Verificar si un género existe",
            description = "Comprueba si un género existe en la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de la verificación obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado", content = @Content)
    })
    @GetMapping("/genero/existe/{id}")
    public boolean existeGenero(@PathVariable int id) {
        return generoService.existeGeneroPorId(id);
    }

    @Operation(
            summary = "Obtener un género por ID",
            description = "Devuelve los detalles de un género específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Género obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Genero.class))
            }),
            @ApiResponse(responseCode = "404", description = "Género no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/genero/{id}")
    public Genero getGenero(@PathVariable int id) {
        return generoService.obtenerPorId(id);
    }

    @Operation(
            summary = "Contar todos los géneros",
            description = "Devuelve el número total de géneros registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de géneros obtenido exitosamente")
    })
    @GetMapping("/generos/todos")
    public Long contarGeneros() {
        return generoService.contarGenero();
    }

    @Operation(
            summary = "Eliminar un género por ID",
            description = "Elimina un género específico de la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Género eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado", content = @Content)
    })
    @DeleteMapping("/genero/{id}")
    public void eliminarGenero(@PathVariable int id) {
        generoService.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar un género",
            description = "Elimina un género basado en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Género eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado", content = @Content)
    })
    @DeleteMapping("/genero/eliminar")
    public void eliminarGeneros(@RequestBody Genero genero) {
        generoService.eliminarGenero(genero);
    }
}
