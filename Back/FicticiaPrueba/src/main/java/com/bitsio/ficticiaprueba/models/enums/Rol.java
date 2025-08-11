package com.bitsio.ficticiaprueba.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)

public enum Rol {
    Administrador,
    Usuario
}
