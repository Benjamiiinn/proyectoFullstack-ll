package com.levelup.proyectoFullstack_ll.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Rol;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(Arrays.asList(Rol.values()));
    }

}
