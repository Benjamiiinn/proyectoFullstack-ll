package com.levelup.proyectoFullstack_ll.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
    @NotBlank(message = "El nombre es obligatiorio")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contraseña;

    private String confirmarContraseña;

    @NotNull(message = "El RUN es obligatorio")
    @Min(value = 1000000, message = "Run invalido")
    private Integer run;

    @NotBlank(message = "El digito verificador es obligatorio")
    @Pattern(regexp = "[0-9kK]", message = "DV debe ser un numero o K")
    @Size(max = 1)
    private String dv;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
}
