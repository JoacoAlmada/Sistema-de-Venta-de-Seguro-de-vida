package com.bitsio.ficticiaprueba.models.entities;

import com.bitsio.ficticiaprueba.models.entites.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminEntityTest {
    @Test
    void testSettersYGetters() {
        Admin admin = new Admin();

        admin.setId_admin(10);
        admin.setIdentificacion("admin123");
        admin.setNombre_completo("Administrador");
        admin.setContrasenia("pass123");
        admin.setEmail("admin@mail.com");
        admin.setTelefono("123456789");


        assertEquals(10, admin.getId_admin());
        assertEquals("admin123", admin.getIdentificacion());
        assertEquals("Administrador", admin.getNombre_completo());
        assertEquals("pass123", admin.getContrasenia());
        assertEquals("admin@mail.com", admin.getEmail());
        assertEquals("123456789", admin.getTelefono());
        assertNull(admin.getCuenta());
    }


    @Test
    void testEqualsYHashCode() {
        Admin admin1 = new Admin();
        admin1.setId_admin(1);
        admin1.setIdentificacion("id1");

        Admin admin2 = new Admin();
        admin2.setId_admin(1);
        admin2.setIdentificacion("id1");

        assertEquals(admin1, admin2);
        assertEquals(admin1.hashCode(), admin2.hashCode());

        Admin admin3 = new Admin();
        admin3.setId_admin(2);
        admin3.setIdentificacion("id2");

        assertNotEquals(admin1, admin3);
    }
}
