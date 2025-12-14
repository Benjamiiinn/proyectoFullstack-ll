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

import com.levelup.proyectoFullstack_ll.model.Categoria;
import com.levelup.proyectoFullstack_ll.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/categorias")
@Tag(name = "Categorías", description = "Gestión de categorías de productos (Acción, RPG, Deportes, etc.)")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(
        summary = "Listar todas las categorías",
        description = "Obtiene el listado completo de categorías de productos disponibles"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorías")
    })
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @Operation(
        summary = "Obtener categoría por ID",
        description = "Obtiene los detalles de una categoría específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(
        @Parameter(description = "ID de la categoría", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @Operation(
        summary = "Crear nueva categoría",
        description = "Crea una nueva categoría de productos. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría creada exitosamente"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.save(categoria));
    }

    @Operation(
        summary = "Actualizar categoría",
        description = "Actualiza la información de una categoría existente. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
        @Parameter(description = "ID de la categoría", required = true)
        @PathVariable int id, 
        @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.update(id, categoria));
    }

    @Operation(
        summary = "Eliminar categoría",
        description = "Elimina una categoría del sistema. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(
        @Parameter(description = "ID de la categoría", required = true)
        @PathVariable int id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
