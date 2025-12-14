package com.levelup.proyectoFullstack_ll.Auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para registro de nuevo usuario")
public class RegisterRequest {
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com", required = true)
    String username;
    
    @Schema(description = "Contraseña del usuario", example = "Password123", required = true)
    String password;
    
    @Schema(description = "Confirmación de contraseña", example = "Password123", required = true)
    String confirmPassword;
    
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez", required = true)
    String nombre;
    
    @Schema(description = "RUT del usuario (formato chileno)", example = "12345678-9", required = true)
    String rut;
    
    @Schema(description = "Número de teléfono", example = "987654321", required = true)
    int telefono;
    
    @Schema(description = "Dirección del usuario", example = "Av. Libertador 1234, Santiago", required = true)
    String direccion;
}
