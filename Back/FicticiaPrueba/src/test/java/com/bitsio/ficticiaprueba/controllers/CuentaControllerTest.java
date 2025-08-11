package com.bitsio.ficticiaprueba.controllers;


import com.bitsio.ficticiaprueba.models.DTO.CuentaDTO;
import com.bitsio.ficticiaprueba.models.DTO.LoginDTO;
import com.bitsio.ficticiaprueba.models.DTO.TokenDTO;
import com.bitsio.ficticiaprueba.models.enums.Rol;
import com.bitsio.ficticiaprueba.services.Cuenta.CuentaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
 class CuentaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Test
    void ListaCuentas() throws Exception {
        CuentaDTO cuenta = new CuentaDTO();
        cuenta.setIdentificacion("12345");
        cuenta.setRol(Rol.Administrador);

        List<CuentaDTO> listaCuenta = List.of(cuenta);
        Mockito.when(cuentaService.getAllCuentas()).thenReturn(listaCuenta);

        mockMvc.perform(get("/api/v1/cuenta")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identificacion").value("12345"))
                .andExpect(jsonPath("$[0].rol").value("Administrador"));
    }

    @Test
    void loginExitoso() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentificacion("user123");
        loginDTO.setContrasenia("contra123");

        TokenDTO tokenDTO = new TokenDTO("tokenValido");


        Mockito.when(cuentaService.login(Mockito.any(LoginDTO.class)))
                .thenReturn(tokenDTO);

        mockMvc.perform(post("/api/v1/cuenta/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "identificacion": "user123",
                    "contrasenia": "contra123"
                }
                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("tokenValido"));
    }

    @Test
    void loginFallido() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentificacion("user123");
        loginDTO.setContrasenia("contra123");

        Mockito.when(cuentaService.login(Mockito.any(LoginDTO.class)))
                .thenThrow(new RuntimeException("Credenciales invalidas"));

        mockMvc.perform(post("/api/v1/cuenta/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "identificacion": "user123",
                                    "contrasenia": "contra123"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }
}
