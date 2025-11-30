package com.levelup.proyectoFullstack_ll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Envio;
import com.levelup.proyectoFullstack_ll.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable int id) {
        return ResponseEntity.ok(envioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable int id, @RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.actualizarDireccion(id, envio));
    }
}
