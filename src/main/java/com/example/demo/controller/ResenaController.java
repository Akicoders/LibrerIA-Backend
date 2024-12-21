package com.example.demo.controller;

import com.example.demo.model.Resena;
import com.example.demo.service.ResenaService;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResenaController {

    @Autowired
    private ResenaService service;

    @Operation(
            summary = "Obtener todas las reseñas",
            description = "Devuelve una lista completa de todas las reseñas almacenadas en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseñas obtenidas exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
            }),
            @ApiResponse(responseCode = "404", description = "No se encontraron reseñas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/resenas")
    public ImmutableList<Resena> getResenas() {
        return service.ObtenerResenas();
    }

    @Operation(
            summary = "Agregar una nueva reseña",
            description = "Permite registrar una nueva reseña en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reseña creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles de la reseña a registrar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "titulo": "Excelente servicio",
                                "comentario": "La comida fue increíble y el personal muy amable.",
                                "calificacion": 5,
                                "usuarioId": 1,
                                "productoId": 101
                            }
                            """
            ))
    )
    @PostMapping("/resena/agregar")
    public void agregarResena(@RequestBody Resena resena) {
        service.agregarResena(resena);
    }

    @Operation(
            summary = "Actualizar una reseña existente",
            description = "Actualiza los datos de una reseña existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/resena/actualizar")
    public void actualizarResena(@RequestBody Resena resena) {
        service.actualizarResena(resena);
    }

    @Operation(
            summary = "Verificar si una reseña existe",
            description = "Comprueba si una reseña existe en la base de datos dado su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Se verificó la existencia de la reseña"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada", content = @Content)
    })
    @GetMapping("/resena/existe/{id}")
    public boolean existeResena(@PathVariable int id) {
        return service.existeResenaPorId(id);
    }

    @Operation(
            summary = "Obtener una reseña por ID",
            description = "Devuelve los detalles de una reseña específica basada en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseña obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resena.class))
            }),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("resena/{id}")
    public Resena getResena(@PathVariable int id) {
        return service.obtenerPorId(id);
    }

    @Operation(
            summary = "Contar el total de reseñas",
            description = "Devuelve el número total de reseñas almacenadas en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de reseñas obtenido exitosamente")
    })
    @GetMapping("/resenas/todos")
    public Long contarResenas() {
        return service.contarResena();
    }

    @Operation(
            summary = "Eliminar una reseña por ID",
            description = "Elimina una reseña específica basada en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reseña eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada", content = @Content)
    })
    @DeleteMapping("resena/{id}")
    public void eliminarResena(@PathVariable int id) {
        service.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar una reseña",
            description = "Elimina una reseña específica basada en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reseña eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada", content = @Content)
    })
    @DeleteMapping("resena/eliminar")
    public void eliminarResenas(@RequestBody Resena resena) {
        service.eliminarResena(resena);
    }
}
