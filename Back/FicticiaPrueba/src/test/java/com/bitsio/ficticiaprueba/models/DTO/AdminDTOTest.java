package com.bitsio.ficticiaprueba.models.DTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDTOTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSettersYGetters() {
        AdminDTO admin = new AdminDTO();

        admin.setIdentificacion("admin123");
        admin.setNombre_completo("Admin User");
        admin.setContrasenia("secreto");
        admin.setEmail("admin@mail.com");
        admin.setTelefono("123456789");

        assertEquals("admin123", admin.getIdentificacion());
        assertEquals("Admin User", admin.getNombre_completo());
        assertEquals("secreto", admin.getContrasenia());
        assertEquals("admin@mail.com", admin.getEmail());
        assertEquals("123456789", admin.getTelefono());
    }

    @Test
    void testValidarCamposObligatorios() {
        AdminDTO admin = new AdminDTO();

        Set<ConstraintViolation<AdminDTO>> violations = validator.validate(admin);

        assertFalse(violations.isEmpty());

        boolean tieneIdentificacion = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("identificacion"));
        boolean tieneNombre = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nombre_completo"));
        boolean tieneContrasenia = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("contrasenia"));

        assertTrue(tieneIdentificacion, " La identificacion no puede estar vacia");
        assertTrue(tieneNombre, " El nombre no puede estar vacio");
        assertTrue(tieneContrasenia, " La Contraseña no puede estar vacio");
    }
}
