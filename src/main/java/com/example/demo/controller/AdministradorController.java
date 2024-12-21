package com.example.demo.controller;

import com.example.demo.model.Administrador;
import com.example.demo.service.AdministradorService;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Operation(
            summary = "Obtener todos los administradores",
            description = "Devuelve una lista de todos los administradores registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/administradores")
    public ImmutableList<Administrador> getAdministradores() {
        return administradorService.ObtenerAdministradores();
    }

    @Operation(
            summary = "Agregar un nuevo administrador",
            description = "Registra un nuevo administrador en la base de datos, asignando automáticamente la fecha de registro actual."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Administrador agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Detalles del administrador a agregar",
            required = true,
            content = @Content(mediaType = "application/json", examples = @ExampleObject(
                    value = """
                            {
                                "nombre": "Juan Pérez",
                                "correo": "juan.perez@example.com",
                                "rol": "Administrador"
                            }
                            """
            ))
    )
    @PostMapping("/administrador/agregar")
    public void agregarAdministrador(@RequestBody Administrador administrador) {
        administrador.setFechaRegistro(LocalDate.now());
        administradorService.agregarAdministrador(administrador);
    }

    @Operation(
            summary = "Actualizar un administrador existente",
            description = "Actualiza los detalles de un administrador existente en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/administrador/actualizar")
    public void actualizarAdministrador(@RequestBody Administrador administrador) {
        administradorService.actualizarAdministrador(administrador);
    }

    @Operation(
            summary = "Verificar si un administrador existe",
            description = "Comprueba si un administrador existe en la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado de la verificación obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
    })
    @GetMapping("/administrador/existe/{id}")
    public boolean existeAdministrador(@PathVariable int id) {
        return administradorService.existeAdministradorPorId(id);
    }

    @Operation(
            summary = "Obtener un administrador por ID",
            description = "Devuelve los detalles de un administrador específico basado en su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador obtenido exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Administrador.class))
            }),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
    })
    @GetMapping("/administrador/{id}")
    public Administrador getAdministrador(@PathVariable int id) {
        return administradorService.obtenerPorId(id);
    }

    @Operation(
            summary = "Contar todos los administradores",
            description = "Devuelve el número total de administradores registrados en la base de datos."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Número total de administradores obtenido exitosamente")
    })
    @GetMapping("/administradores/contar")
    public Long contarAdministradores() {
        return administradorService.contarAdministrador();
    }

    @Operation(
            summary = "Eliminar un administrador por ID",
            description = "Elimina un administrador específico de la base de datos según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
    })
    @DeleteMapping("/administrador/{id}")
    public void eliminarAdministrador(@PathVariable int id) {
        administradorService.eliminarPorId(id);
    }

    @Operation(
            summary = "Eliminar un administrador",
            description = "Elimina un administrador basado en su objeto."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado", content = @Content)
    })
    @DeleteMapping("/administrador/eliminar")
    public void eliminarAdministrador(@RequestBody Administrador administrador) {
        administradorService.eliminarAdministrador(administrador);
    }
}
