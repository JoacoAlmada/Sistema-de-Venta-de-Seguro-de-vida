package com.bitsio.ficticiaprueba.models.entities;

import com.bitsio.ficticiaprueba.models.entites.Usuario;
import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioEntityTest {
    @Test
    void testSettersYGetters() {
        Usuario usuario = new Usuario();

        usuario.setId_usuario(1);
        usuario.setIdentificacion("usuario123");
        usuario.setNombre_completo("Juan Perez");
        usuario.setContrasenia("pass123");
        usuario.setEdad(30);
        usuario.setSeguro(Seguros.Basico);
        usuario.setEmail("juan@mail.com");
        usuario.setTelefono("123456789");
        usuario.setGenero(Genero.Masculino);
        usuario.setEstado(Estado.Activo);
        usuario.setManeja(true);
        usuario.setUsa_lentes(false);
        usuario.setDiabetico(true);
        usuario.setOtra_enfermedad(false);
        usuario.setCual_enfermedad(null);
        usuario.setCuenta(null);

        assertEquals(1, usuario.getId_usuario());
        assertEquals("usuario123", usuario.getIdentificacion());
        assertEquals("Juan Perez", usuario.getNombre_completo());
        assertEquals("pass123", usuario.getContrasenia());
        assertEquals(30, usuario.getEdad());
        assertEquals(Seguros.Basico, usuario.getSeguro());
        assertEquals("juan@mail.com", usuario.getEmail());
        assertEquals("123456789", usuario.getTelefono());
        assertEquals(Genero.Masculino, usuario.getGenero());
        assertEquals(Estado.Activo, usuario.getEstado());
        assertTrue(usuario.getManeja());
        assertFalse(usuario.getUsa_lentes());
        assertTrue(usuario.getDiabetico());
        assertFalse(usuario.getOtra_enfermedad());
        assertNull(usuario.getCual_enfermedad());
        assertNull(usuario.getCuenta());
    }


    @Test
    void testEqualsYHashCode() {
        Usuario u1 = new Usuario();
        u1.setId_usuario(1);
        u1.setIdentificacion("1");

        Usuario u2 = new Usuario();
        u2.setId_usuario(1);
        u2.setIdentificacion("1");

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());

        Usuario u3 = new Usuario();
        u3.setId_usuario(2);
        u3.setIdentificacion("2");

        assertNotEquals(u1, u3);
    }
}
