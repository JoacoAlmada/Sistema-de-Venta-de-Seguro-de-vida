package com.bitsio.ficticiaprueba.models.DTO;

import com.bitsio.ficticiaprueba.models.enums.Rol;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CuentaDTOTest {

    @Test
    void testGettersYSetters() {
        CuentaDTO cuenta = new CuentaDTO();

        cuenta.setIdentificacion("user123");
        cuenta.setContrasenia("pass123");
        cuenta.setRol(Rol.Administrador);

        assertEquals("user123", cuenta.getIdentificacion());
        assertEquals("pass123", cuenta.getContrasenia());
        assertEquals(Rol.Administrador, cuenta.getRol());
    }


}
