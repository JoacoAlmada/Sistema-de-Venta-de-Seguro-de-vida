package com.bitsio.ficticiaprueba.models.DTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TokenDTOTest {

    @Test
    void testConstructorYGetter() {
        String tokenValor = "token123";
        TokenDTO tokenDTO = new TokenDTO(tokenValor);

        assertEquals(tokenValor, tokenDTO.getToken());
    }

    @Test
    void testSetterYGetter() {
        TokenDTO tokenDTO = new TokenDTO(null);
        tokenDTO.setToken("nuevoToken");

        assertEquals("nuevoToken", tokenDTO.getToken());
    }
}