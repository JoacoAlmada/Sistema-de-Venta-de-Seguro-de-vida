package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UsuarioDTO {
    private Integer id_usuario;
    @NotBlank (message = "La identificacion no puede estar vacia")
    @NotNull
    private String identificacion;
    @NotBlank (message = "El nombre no puede estar vacio")
    @NotNull
    private String nombre_completo;
    @NotBlank
    @NotNull (message = "Ingrese una contraseña")
    private String contrasenia;

    @Min(value = 0 ,message = "La edad no puede ser negativa")
    @Max(value = 120 , message = "La edad no puede ser mayor a 120")
    private Integer edad;
    @NotNull (message = "Ingrese un Seguro")
    private Seguros seguro;
    @NotBlank
    @NotNull (message = "Ingrese un Correo")
    private String email;
    @NotBlank
    @NotNull (message = "Ingrese una telefono")
    private String telefono;

    @NotNull (message = "Seleccione un genero")
    private Genero genero;
    @NotNull (message = "Seleccione un estado")
    private Estado estado;

    private Boolean maneja;

    private Boolean usa_lentes;

    private Boolean diabetico;

    private Boolean otra_enfermedad;

    private String cual_enfermedad;
    @JsonIgnore
    private Cuenta cuenta;

}
