package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de videojuegos y productos de la tienda")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(
        summary = "Listar todos los productos",
        description = "Obtiene el listado completo de productos disponibles en la tienda"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos obtenida exitosamente"
        )
    })
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @Operation(
        summary = "Obtener producto por ID",
        description = "Obtiene la información detallada de un producto específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(
        @Parameter(description = "ID del producto a buscar", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @Operation(
        summary = "Buscar productos por categoría",
        description = "Obtiene todos los productos que pertenecen a una categoría específica (ej: Acción, RPG, Deportes)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos de la categoría"
        )
    })
    @GetMapping("/categoria/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(
        @Parameter(description = "Nombre de la categoría", required = true, example = "Acción")
        @PathVariable String nombre) {
        return ResponseEntity.ok(productoService.findByCategoria(nombre));
    }

    @Operation(
        summary = "Listar productos destacados",
        description = "Obtiene los productos marcados como destacados para mostrar en la página principal"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos destacados"
        )
    })
    @GetMapping("/destacados")
    public ResponseEntity<List<Producto>> listarDestacados() {
        return ResponseEntity.ok(productoService.findDestacados());
    }

    @Operation(
        summary = "Buscar productos por plataforma",
        description = "Obtiene todos los productos disponibles para una plataforma específica (ej: PlayStation, Xbox, Nintendo Switch, PC)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos de la plataforma"
        )
    })
    @GetMapping("/plataforma/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorPlataforma(
        @Parameter(description = "Nombre de la plataforma", required = true, example = "PlayStation")
        @PathVariable String nombre) {
        return ResponseEntity.ok(productoService.findByPlataforma(nombre));
    }
    
    @Operation(
        summary = "Crear nuevo producto",
        description = "Crea un nuevo producto en el catálogo. Requiere permisos de ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto creado exitosamente"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para crear productos"
        )
    })
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @Operation(
        summary = "Actualizar producto",
        description = "Actualiza la información de un producto existente. Requiere permisos de ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para actualizar productos"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
        @Parameter(description = "ID del producto a actualizar", required = true)
        @PathVariable int id, 
        @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
    }

    @Operation(
        summary = "Eliminar producto",
        description = "Elimina un producto del catálogo. Requiere permisos de ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Producto eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para eliminar productos"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
        @Parameter(description = "ID del producto a eliminar", required = true)
        @PathVariable int id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    

}
