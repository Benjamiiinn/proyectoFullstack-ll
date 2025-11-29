package com.levelup.proyectoFullstack_ll.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String confirmPassword;
    String nombre;
    String rut;
    int telefono;
    String direccion;
}
