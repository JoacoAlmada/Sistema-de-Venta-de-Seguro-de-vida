package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.enums.Rol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {
    @NotBlank
    @NotNull (message = "Ingrese una indentificacion")
    private String identificacion;
    @NotBlank
    @NotNull (message = "Ingrese una contraseña")
    @JsonIgnore
    private String contrasenia;
    @NotBlank
    @NotNull
    private Rol rol;
}
