package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Contacto;
import com.levelup.proyectoFullstack_ll.service.ContactoService;

@RestController
@RequestMapping("/api/v1/contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @PostMapping
    public ResponseEntity<Contacto> enviarMensaje(@RequestBody Contacto mensaje) {
        return ResponseEntity.ok(contactoService.guardarMensaje(mensaje));
    }

    @GetMapping
    public ResponseEntity<List<Contacto>> listarMensajes() {
        return ResponseEntity.ok(contactoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> verMensaje(@PathVariable int id) {
        return ResponseEntity.ok(contactoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMensaje(@PathVariable int id) {
        contactoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
