package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.service.LibroService;
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
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Operation(
            summary = "Obtener todos los libros",
            description = "Devuelve una lista de todos los libros almacenados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Libros obtenidos exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/libros")
    public ImmutableList<Libro> getLibros() {
        return libroService.ObtenerLibros();
    }

    @Operation(
            summary = "Agregar un libro",
            description = "Permite registrar un nuevo libro en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Libro agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles del libro a registrar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "titulo": "El Quijote",
                                "autor": "Miguel de Cervantes",
                                "isbn": "978-1234567890",
                                "editorial": "Anaya",
                                "anioPublicacion": 1605
                            }
                            """
            ))
    )
    @PostMapping("/libro/agregar")
    public void agregarLibro(@RequestBody Libro libro) {
        libroService.agregarLibro(libro);
    }

    @Operation(
            summary = "Actualizar un libro",
            description = "Actualiza los datos de un libro existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Libro actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/libro/actualizar")
    public void actualizarLibro(@RequestBody Libro libro) {
        libroService.actualizarLibro(libro);
    }

    @Operation(
            summary = "Obtener un libro por ID",
            description = "Devuelve los detalles de un libro específico dado su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Libro obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
            }),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/libro/{id}")
    public Libro getLibro(@PathVariable int id) {
        return libroService.obtenerPorId(id);
    }

    @Operation(
            summary = "Verificar si un libro existe",
            description = "Comprueba si un libro existe en la base de datos dado su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de la verificación obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
    })
    @GetMapping("/libro/existe/{id}")
    public boolean existeLibro(@PathVariable int id) {
        return libroService.existeLibroPorId(id);
    }

    @Operation(
            summary = "Contar el total de libros",
            description = "Devuelve el número total de libros almacenados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de libros obtenido exitosamente")
    })
    @GetMapping("/libros/todos")
    public Long contarLibros() {
        return libroService.contarLibro();
    }

    @Operation(
            summary = "Eliminar un libro por ID",
            description = "Elimina un libro específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
    })
    @DeleteMapping("/libro/{id}")
    public void eliminarLibro(@PathVariable int id) {
        libroService.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar un libro",
            description = "Elimina un libro específico basado en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Libro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado", content = @Content)
    })
    @DeleteMapping("/libro/eliminar")
    public void eliminarLibro(@RequestBody Libro libro) {
        libroService.eliminarLibro(libro);
    }
}
