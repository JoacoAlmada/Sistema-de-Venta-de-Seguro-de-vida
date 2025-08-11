package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AltaDTOTest {
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGettersYSetters() {
        AltaDTO alta = new AltaDTO();

        alta.setIdentificacion("12345");
        alta.setNombre_completo("Juan Perez");
        alta.setContrasenia("secreto");
        alta.setEdad(30);
        alta.setEmail("juan@mail.com");
        alta.setTelefono("123456789");
        alta.setGenero(Genero.Masculino);
        alta.setEstado(Estado.Activo);
        alta.setManeja(true);
        alta.setUsa_lentes(false);
        alta.setDiabetico(false);
        alta.setOtra_enfermedad(true);
        alta.setCual_enfermedad("asma");
        alta.setSeguro(Seguros.Basico);

        assertEquals("12345", alta.getIdentificacion());
        assertEquals("Juan Perez", alta.getNombre_completo());
        assertEquals("secreto", alta.getContrasenia());
        assertEquals(30, alta.getEdad());
        assertEquals("juan@mail.com", alta.getEmail());
        assertEquals("123456789", alta.getTelefono());
        assertEquals(Genero.Masculino, alta.getGenero());
        assertEquals(Estado.Activo, alta.getEstado());
        assertTrue(alta.getManeja());
        assertFalse(alta.getUsa_lentes());
        assertFalse(alta.getDiabetico());
        assertTrue(alta.getOtra_enfermedad());
        assertEquals("asma", alta.getCual_enfermedad());
        assertEquals(Seguros.Basico, alta.getSeguro());
    }

    @Test
    void testValidarCamposObligatoriosYRestricciones() {
        AltaDTO alta = new AltaDTO();

        Set<ConstraintViolation<AltaDTO>> violaciones = validator.validate(alta);

        assertFalse(violaciones.isEmpty());

        boolean tieneIdentificacion = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("identificacion"));
        boolean tieneNombre = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nombre_completo"));
        boolean tieneContrasenia = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("contrasenia"));
        boolean tieneGenero = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("genero"));
        boolean tieneEstado = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("estado"));
        boolean tieneSeguro = violaciones.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("seguro"));

        assertTrue(tieneIdentificacion, "La identificacion no puede estar vacia");
        assertTrue(tieneNombre, "El nombre no puede estar vacio");
        assertTrue(tieneContrasenia, "Ingrese una contraseña");
        assertTrue(tieneGenero, "Seleccione un genero");
        assertTrue(tieneEstado, "Seleccione un estado");
        assertTrue(tieneSeguro, "Ingrese un Seguro");
    }

    @Test
    void testValidarEdadFueraDeRango() {
        AltaDTO alta = new AltaDTO();
        alta.setIdentificacion("id123");
        alta.setNombre_completo("Nombre");
        alta.setContrasenia("pass");
        alta.setGenero(Genero.Masculino);
        alta.setEstado(Estado.Activo);
        alta.setSeguro(Seguros.Premium);

        alta.setEdad(-1);
        Set<ConstraintViolation<AltaDTO>> violacionesNeg = validator.validate(alta);
        boolean tieneEdadNegativa = violacionesNeg.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("edad"));
        assertTrue(tieneEdadNegativa, "La edad no puede ser negativa");

        alta.setEdad(121);
        Set<ConstraintViolation<AltaDTO>> violacionesMayor = validator.validate(alta);
        boolean tieneEdadMayor = violacionesMayor.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("edad"));
        assertTrue(tieneEdadMayor, "La edad no puede ser mayor a 120");
    }
}
