package com.bitsio.ficticiaprueba.repository;

import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface cuentaRepository extends JpaRepository<Cuenta, String> {
    Optional<Cuenta> findByIdentificacion(String identificacion);

}
