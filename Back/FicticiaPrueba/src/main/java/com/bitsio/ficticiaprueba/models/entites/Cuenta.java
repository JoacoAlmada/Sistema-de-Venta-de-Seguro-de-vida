package com.bitsio.ficticiaprueba.models.entites;

import com.bitsio.ficticiaprueba.models.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cuenta")
public class Cuenta {
    @Id
    private String identificacion;
    @Column(unique = true)
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    @Column
    private Rol rol;
}
