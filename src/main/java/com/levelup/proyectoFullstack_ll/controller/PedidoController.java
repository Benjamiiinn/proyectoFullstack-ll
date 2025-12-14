package com.levelup.proyectoFullstack_ll.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.dto.PedidoRequest;
import com.levelup.proyectoFullstack_ll.model.Pedido;
import com.levelup.proyectoFullstack_ll.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Gestión de pedidos y compras de los usuarios")
@SecurityRequirement(name = "bearerAuth")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(
        summary = "Realizar una compra",
        description = "Crea un nuevo pedido con los productos del carrito. Requiere autenticación"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Compra realizada exitosamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Error en los datos del pedido o stock insuficiente"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "No autenticado"
        )
    })
    @PostMapping
    public ResponseEntity<?> realizarCompra(@RequestBody PedidoRequest request) {
        try {
            return ResponseEntity.ok(pedidoService.realizarCompra(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
        summary = "Obtener pedido por ID",
        description = "Obtiene la información detallada de un pedido específico. El usuario solo puede ver sus propios pedidos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedido encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido no encontrado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos para ver este pedido"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(
        @Parameter(description = "ID del pedido", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @Operation(
        summary = "Listar mis pedidos",
        description = "Obtiene todos los pedidos de un usuario específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de pedidos del usuario"
        )
    })
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pedido>> misPedidos(
        @Parameter(description = "ID del usuario", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(id));
    }

    @Operation(
        summary = "Listar todos los pedidos",
        description = "Obtiene todos los pedidos del sistema. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista completa de pedidos"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos de administrador"
        )
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @Operation(
        summary = "Actualizar estado del pedido",
        description = "Cambia el estado de un pedido (ej: Pendiente, En proceso, Enviado, Entregado). Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Estado actualizado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido no encontrado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos de administrador"
        )
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
        @Parameter(description = "ID del pedido", required = true)
        @PathVariable int id, 
        @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, nuevoEstado));
    }

    @Operation(
        summary = "Buscar pedidos por rango de fechas",
        description = "Obtiene pedidos realizados entre dos fechas específicas. Solo para ADMIN. Formato: yyyy-MM-dd'T'HH:mm:ss"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de pedidos en el rango de fechas"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos de administrador"
        )
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<Pedido>> buscarPorFechas(
            @Parameter(description = "Fecha de inicio", required = true, example = "2023-11-01T00:00:00")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @Parameter(description = "Fecha de fin", required = true, example = "2023-11-30T23:59:59")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(pedidoService.buscarPorFechas(inicio, fin));
    }
    
    @Operation(
        summary = "Eliminar pedido",
        description = "Elimina un pedido del sistema. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Pedido eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Pedido no encontrado"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "No tiene permisos de administrador"
        )
    })
    @DeleteMapping
    public ResponseEntity<Void> eliminarPedido(
        @Parameter(description = "ID del pedido a eliminar", required = true)
        @PathVariable int id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
