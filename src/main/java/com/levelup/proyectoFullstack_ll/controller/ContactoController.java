package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Contacto;
import com.levelup.proyectoFullstack_ll.service.ContactoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/contacto")
@Tag(name = "Contacto", description = "Gestión de mensajes de contacto de los usuarios")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @Operation(
        summary = "Enviar mensaje de contacto",
        description = "Permite a cualquier usuario enviar un mensaje de contacto sin necesidad de autenticación"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensaje enviado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del mensaje inválidos")
    })
    @PostMapping
    public ResponseEntity<Contacto> enviarMensaje(@RequestBody Contacto mensaje) {
        return ResponseEntity.ok(contactoService.guardarMensaje(mensaje));
    }

    @Operation(
        summary = "Listar todos los mensajes",
        description = "Obtiene todos los mensajes de contacto recibidos. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de mensajes"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @GetMapping
    public ResponseEntity<List<Contacto>> listarMensajes() {
        return ResponseEntity.ok(contactoService.listarTodos());
    }

    @Operation(
        summary = "Ver mensaje específico",
        description = "Obtiene los detalles de un mensaje de contacto. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensaje encontrado"),
        @ApiResponse(responseCode = "404", description = "Mensaje no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Contacto> verMensaje(
        @Parameter(description = "ID del mensaje", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(contactoService.findById(id));
    }

    @Operation(
        summary = "Eliminar mensaje",
        description = "Elimina un mensaje de contacto del sistema. Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Mensaje eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Mensaje no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarMensaje(
        @Parameter(description = "ID del mensaje", required = true)
        @PathVariable int id) {
        contactoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Marcar mensaje como leído/no leído",
        description = "Cambia el estado de lectura de un mensaje (toggle). Solo para ADMIN",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado cambiado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Mensaje no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PatchMapping("/{id}/leido")
    public ResponseEntity<Contacto> cambiarEstadoLeido(
        @Parameter(description = "ID del mensaje", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(contactoService.toggleLeido(id));
    }

}
