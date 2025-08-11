package com.bitsio.ficticiaprueba.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFiltro {
    private String genero;
    private String estado;
    private String seguro;
    private Boolean usa_lentes;
    private Boolean maneja;
    private Boolean diabetico;
    private Boolean otra_enfermedad;
}
