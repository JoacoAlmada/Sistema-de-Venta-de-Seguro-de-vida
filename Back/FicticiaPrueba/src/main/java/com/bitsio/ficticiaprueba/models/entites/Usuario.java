package com.bitsio.ficticiaprueba.models.entites;

import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(unique = true , name = "identificacion")
    private String identificacion;

    @Column(unique = true)
    private String nombre_completo;
    @Column(unique = true)
    private String contrasenia;
    @Column
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column
    private Seguros seguro;

    @Column
    private String email;

    @Column
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column
    private Estado estado;

    @Column
    private Boolean maneja;

    @Column
    private Boolean usa_lentes;

    @Column
    private Boolean diabetico;

    @Column
    private Boolean otra_enfermedad;

    @Column
    private String cual_enfermedad;

    @ManyToOne
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion", insertable = false, updatable = false)
    private Cuenta cuenta;

}
