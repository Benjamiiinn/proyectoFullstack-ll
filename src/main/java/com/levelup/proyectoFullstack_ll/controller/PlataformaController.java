package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Plataforma;
import com.levelup.proyectoFullstack_ll.service.PlataformaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/plataformas")
@Tag(name = "Plataformas", description = "Gestión de plataformas de videojuegos (PlayStation, Xbox, Nintendo, PC, etc.)")
public class PlataformaController {

    @Autowired
    private PlataformaService plataformaService;

    @Operation(
        summary = "Listar todas las plataformas",
        description = "Obtiene el listado completo de plataformas de videojuegos disponibles"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de plataformas")
    })
    @GetMapping
    public ResponseEntity<List<Plataforma>> listarPlataformas() {
        return ResponseEntity.ok(plataformaService.findAll());
    }

    @Operation(
        summary = "Obtener plataforma por ID",
        description = "Obtiene los detalles de una plataforma específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plataforma encontrada"),
        @ApiResponse(responseCode = "404", description = "Plataforma no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Plataforma> obtenerPorId(
        @Parameter(description = "ID de la plataforma", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(plataformaService.findById(id));
    }

    @Operation(
        summary = "Crear nueva plataforma",
        description = "Crea una nueva plataforma de videojuegos. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plataforma creada exitosamente"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PostMapping
    public ResponseEntity<Plataforma> crearPlataforma(@RequestBody Plataforma plataforma) {
        return ResponseEntity.ok(plataformaService.save(plataforma));
    }

    @Operation(
        summary = "Actualizar plataforma",
        description = "Actualiza la información de una plataforma existente. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plataforma actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Plataforma no encontrada"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Plataforma> actualizarPlataforma(
        @Parameter(description = "ID de la plataforma", required = true)
        @PathVariable int id, 
        @RequestBody Plataforma plataforma) {
        return ResponseEntity.ok(plataformaService.update(id, plataforma));
    }

    @Operation(
        summary = "Eliminar plataforma",
        description = "Elimina una plataforma del sistema. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Plataforma eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Plataforma no encontrada"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlataforma(
        @Parameter(description = "ID de la plataforma", required = true)
        @PathVariable int id) {
        plataformaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
