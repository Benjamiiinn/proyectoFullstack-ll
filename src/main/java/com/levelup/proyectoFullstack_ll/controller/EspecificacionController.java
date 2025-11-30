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

import com.levelup.proyectoFullstack_ll.model.Especificacion;
import com.levelup.proyectoFullstack_ll.service.EspecificacionService;

@RestController
@RequestMapping("/api/v1/especificaciones")
public class EspecificacionController {

    @Autowired
    private EspecificacionService especificacionService;

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<Especificacion>> listarPorProducto(@PathVariable int id) {
        return ResponseEntity.ok(especificacionService.findByProducto(id));
    }

    @PostMapping
    public ResponseEntity<Especificacion> agregarEspecificacion(@RequestBody Especificacion especificacion) {
        return ResponseEntity.ok(especificacionService.save(especificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especificacion> actualizarEspecificacion(@PathVariable int id, @RequestBody Especificacion especificacion) {
        return ResponseEntity.ok(especificacionService.update(id, especificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEspecificacion(@PathVariable int id) {
        especificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
