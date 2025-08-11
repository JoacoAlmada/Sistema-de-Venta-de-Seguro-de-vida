package com.bitsio.ficticiaprueba.models.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioFiltroDTOTest {
    @Test
    void testGetterYSetter() {
        UsuarioFiltro filtro = new UsuarioFiltro();

        filtro.setGenero("Masculino");
        filtro.setEstado("Activo");
        filtro.setUsa_lentes(true);
        filtro.setManeja(false);
        filtro.setDiabetico(true);
        filtro.setOtra_enfermedad(false);

        assertEquals("Masculino", filtro.getGenero());
        assertEquals("Activo", filtro.getEstado());
        assertTrue(filtro.getUsa_lentes());
        assertFalse(filtro.getManeja());
        assertTrue(filtro.getDiabetico());
        assertFalse(filtro.getOtra_enfermedad());
    }

    @Test
    void testConstructorYAllArgs() {
        UsuarioFiltro filtro = new UsuarioFiltro("Femenino", "Inactivo","Basico", false, true, false, true);

        assertEquals("Femenino", filtro.getGenero());
        assertEquals("Inactivo", filtro.getEstado());
        assertEquals("Basico", filtro.getSeguro());
        assertFalse(filtro.getUsa_lentes());
        assertTrue(filtro.getManeja());
        assertFalse(filtro.getDiabetico());
        assertTrue(filtro.getOtra_enfermedad());
    }
}
