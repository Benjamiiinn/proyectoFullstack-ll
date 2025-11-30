package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.DetallePedido;
import com.levelup.proyectoFullstack_ll.service.DetallePedidoService;

@RestController
@RequestMapping("/api/v1/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/pedido/{id}")
    public ResponseEntity<List<DetallePedido>> obtenerPorPedido(@PathVariable int id) {
        return ResponseEntity.ok(detallePedidoService.findByPedido(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(detallePedidoService.findById(id));
    }
}
