package com.bitsio.ficticiaprueba.repository;

import com.bitsio.ficticiaprueba.models.entites.Usuario;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByIdentificacion(String identificacion);

}
