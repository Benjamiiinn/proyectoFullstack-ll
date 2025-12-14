package com.levelup.proyectoFullstack_ll.Auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de inicio de sesión")
public class LoginRequest {
    @Schema(description = "Correo electrónico del usuario", example = "usuario@ejemplo.com", required = true)
    String username;
    
    @Schema(description = "Contraseña del usuario", example = "Password123", required = true)
    String password;
}
