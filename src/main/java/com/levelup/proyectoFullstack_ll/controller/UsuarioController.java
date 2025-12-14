package com.levelup.proyectoFullstack_ll.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.proyectoFullstack_ll.model.Usuario;
import com.levelup.proyectoFullstack_ll.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios del sistema")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene la lista completa de usuarios registrados. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Obtiene información de un usuario específico. Los usuarios pueden ver su propio perfil"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos para ver este usuario")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(
        @Parameter(description = "ID del usuario", required = true)
        @PathVariable int id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(
        summary = "Buscar usuario por RUT",
        description = "Busca un usuario mediante su RUT. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda completada"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Optional<Usuario>> obtenerUsuarioPorRut(
        @Parameter(description = "RUT del usuario", required = true, example = "12345678-9")
        @PathVariable String rut) {
        return ResponseEntity.ok(usuarioService.buscarPorRut(rut));
    }

    @Operation(
        summary = "Crear nuevo usuario",
        description = "Crea un nuevo usuario en el sistema. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza la información de un usuario existente. Los usuarios pueden actualizar su propio perfil"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos para actualizar este usuario")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
        @Parameter(description = "ID del usuario", required = true)
        @PathVariable int id, 
        @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema. Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
        @Parameter(description = "ID del usuario", required = true)
        @PathVariable int id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Cambiar estado del usuario",
        description = "Activa o desactiva un usuario (toggle). Solo para ADMIN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado cambiado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos de administrador")
    })
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Usuario> cambiarEstado(
        @Parameter(description = "ID del usuario", required = true)
        @PathVariable int id) {
        Usuario usuarioActualizado = usuarioService.toggleEstado(id);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
