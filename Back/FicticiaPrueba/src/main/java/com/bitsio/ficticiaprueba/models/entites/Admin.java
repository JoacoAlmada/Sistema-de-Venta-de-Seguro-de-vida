package com.bitsio.ficticiaprueba.models.entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_admin;
    @Column(unique = true)
    private String identificacion;
    @Column(unique = true)
    private String nombre_completo;
    @Column(unique = true)
    private String contrasenia;
    @Column
    private String email;
    @Column
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion", insertable = false, updatable = false)
    private Cuenta cuenta;
}
