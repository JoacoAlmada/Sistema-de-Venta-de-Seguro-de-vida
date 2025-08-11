package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.enums.Rol;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGettersYSetters() {
        LoginDTO login = new LoginDTO();

        login.setIdentificacion("user123");
        login.setContrasenia("pass123");
        login.setRol(Rol.Administrador);

        assertEquals("user123", login.getIdentificacion());
        assertEquals("pass123", login.getContrasenia());
        assertEquals(Rol.Administrador, login.getRol());
    }

    @Test
    void testvalidarCamposObligatorios() {
        LoginDTO login = new LoginDTO();

        Set<ConstraintViolation<LoginDTO>> violaciones = validator.validate(login);
        assertFalse(violaciones.isEmpty());

        boolean tieneIdentificacion = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("identificacion"));
        boolean tieneContrasenia = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("contrasenia"));
        boolean tieneRol = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("rol"));

        assertTrue(tieneIdentificacion, "Ingrese tu indentificacion");
        assertTrue(tieneContrasenia, "Ingrese tu contraseña");
        assertTrue(tieneRol, "Seleccione un rol");
    }
}