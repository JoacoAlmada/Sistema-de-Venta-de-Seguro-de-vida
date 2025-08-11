package com.bitsio.ficticiaprueba.models.DTO;

import static org.junit.jupiter.api.Assertions.*;

import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import org.junit.jupiter.api.Test;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;

import java.util.Set;

public class UsuarioDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSettersYGetters() {
        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setId_usuario(1);
        usuario.setIdentificacion("12345");
        usuario.setNombre_completo("Juan Perez");
        usuario.setContrasenia("pass123");
        usuario.setEdad(30);
        usuario.setEmail("juan@mail.com");
        usuario.setTelefono("123456789");
        usuario.setGenero(Genero.Masculino);
        usuario.setEstado(Estado.Activo);
        usuario.setManeja(true);
        usuario.setUsa_lentes(false);
        usuario.setDiabetico(false);
        usuario.setOtra_enfermedad(false);
        usuario.setCual_enfermedad(null);
        usuario.setSeguro(Seguros.Basico);
        usuario.setCuenta(null);

        assertEquals(1, usuario.getId_usuario());
        assertEquals("12345", usuario.getIdentificacion());
        assertEquals("Juan Perez", usuario.getNombre_completo());
        assertEquals("pass123", usuario.getContrasenia());
        assertEquals(30, usuario.getEdad());
        assertEquals("juan@mail.com", usuario.getEmail());
        assertEquals("123456789", usuario.getTelefono());
        assertEquals(Genero.Masculino, usuario.getGenero());
        assertEquals(Estado.Activo, usuario.getEstado());
        assertTrue(usuario.getManeja());
        assertFalse(usuario.getUsa_lentes());
        assertFalse(usuario.getDiabetico());
        assertFalse(usuario.getOtra_enfermedad());
        assertNull(usuario.getCual_enfermedad());
        assertEquals(Seguros.Basico, usuario.getSeguro());
        assertNull(usuario.getCuenta());
    }

    @Test
    void testBuilder() {
        UsuarioDTO usuario = UsuarioDTO.builder()
                .id_usuario(2)
                .identificacion("67890")
                .nombre_completo("Ana Gomez")
                .contrasenia("pass456")
                .edad(25)
                .email("ana@mail.com")
                .telefono("987654321")
                .genero(Genero.Femenino)
                .estado(Estado.Inactivo)
                .maneja(false)
                .usa_lentes(true)
                .diabetico(true)
                .otra_enfermedad(true)
                .cual_enfermedad("Asma")
                .seguro(Seguros.Premium)
                .build();

        assertEquals(2, usuario.getId_usuario());
        assertEquals("67890", usuario.getIdentificacion());
        assertEquals("Ana Gomez", usuario.getNombre_completo());
        assertEquals("pass456", usuario.getContrasenia());
        assertEquals(25, usuario.getEdad());
        assertEquals("ana@mail.com", usuario.getEmail());
        assertEquals("987654321", usuario.getTelefono());
        assertEquals(Genero.Femenino, usuario.getGenero());
        assertEquals(Estado.Inactivo, usuario.getEstado());
        assertFalse(usuario.getManeja());
        assertTrue(usuario.getUsa_lentes());
        assertTrue(usuario.getDiabetico());
        assertTrue(usuario.getOtra_enfermedad());
        assertEquals("Asma", usuario.getCual_enfermedad());
        assertEquals(Seguros.Premium, usuario.getSeguro());
    }

    @Test
    void testvalidarCamposObligatoriosYRestricciones() {
        UsuarioDTO usuario = new UsuarioDTO();

        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty());

        boolean errorIdentificacion = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("identificacion") && v.getMessage().contains("La identificacion no puede estar vacia"));
        assertTrue(errorIdentificacion);

        boolean errorNombre = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nombre_completo") && v.getMessage().contains("El nombre no puede estar vacio"));
        assertTrue(errorNombre);

        boolean errorContrasenia = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("contrasenia") && v.getMessage().contains("Ingrese una contraseña"));
        assertTrue(errorContrasenia);

        boolean errorGenero = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("genero") && v.getMessage().contains("Seleccione un genero"));
        assertTrue(errorGenero);

        boolean errorEstado = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("estado") && v.getMessage().contains("Seleccione un estado"));
        assertTrue(errorEstado);

        boolean errorSeguro = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("seguro") && v.getMessage().contains("Ingrese un Seguro"));
        assertTrue(errorSeguro);

        usuario.setEdad(-1);
        violations = validator.validateProperty(usuario, "edad");
        assertFalse(violations.isEmpty());
        assertTrue(violations.iterator().next().getMessage().contains("La edad no puede ser negativa"));

        usuario.setEdad(121);
        violations = validator.validateProperty(usuario, "edad");
        assertFalse(violations.isEmpty());
        assertTrue(violations.iterator().next().getMessage().contains("La edad no puede ser mayor a 120"));

        usuario.setEdad(30);
        violations = validator.validateProperty(usuario, "edad");
        assertTrue(violations.isEmpty());
    }

    @Test
    void testvalidarCamposCorrectosNoDaErrores() {
        UsuarioDTO usuario = UsuarioDTO.builder()
                .identificacion("12345")
                .nombre_completo("Juan Perez")
                .contrasenia("pass123")
                .genero(Genero.Masculino)
                .estado(Estado.Activo)
                .edad(25)
                .seguro(Seguros.Estandar)
                .email("juanperez@mail.com")
                .telefono("1234567890")
                .build();
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty());
    }
}