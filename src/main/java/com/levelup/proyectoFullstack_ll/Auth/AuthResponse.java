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
@Schema(description = "Respuesta de autenticación con token JWT")
public class AuthResponse {
    @Schema(description = "Token JWT para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String token;
    
    @Schema(description = "Rol del usuario", example = "USER", allowableValues = {"USER", "ADMIN"})
    String role;
    
    @Schema(description = "ID del usuario", example = "1")
    int userId;
}
