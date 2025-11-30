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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> realizarCompra(@RequestBody PedidoRequest request) {
        try {
            return ResponseEntity.ok(pedidoService.realizarCompra(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pedido>> misPedidos(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable int id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, nuevoEstado));
    }

    // Ejemplo URL: /api/v1/pedidos/buscar?inicio=2023-11-01T00:00:00&fin=2023-11-30T23:59:59
    @GetMapping("/buscar")
    public ResponseEntity<List<Pedido>> buscarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(pedidoService.buscarPorFechas(inicio, fin));
    }
    
    @DeleteMapping
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
