package com.bitsio.ficticiaprueba.models.DTO;

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
public class AdminDTO {
    @NotBlank (message = "La identificacion no puede estar vacia")
    @NotNull
    private String identificacion;
    @NotBlank (message = "El nombre no puede estar vacio")
    @NotNull
    private String nombre_completo;
    @NotBlank (message = "La Contraseña no puede estar vacio")
    @NotNull
    @JsonIgnore
    private String contrasenia;
    private String email;
    private String telefono;

}
