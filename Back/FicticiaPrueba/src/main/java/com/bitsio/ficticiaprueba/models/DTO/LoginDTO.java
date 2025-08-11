package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotBlank
    @NotNull (message = "Ingrese tu indentificacion")
    private String identificacion;
    @NotBlank
    @NotNull (message = "Ingrese tu contraseña")
    private String contrasenia;
    @NotNull(message = "Seleccione un rol")
    private Rol rol;
}
