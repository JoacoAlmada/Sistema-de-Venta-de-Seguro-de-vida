package com.bitsio.ficticiaprueba.controllers;

import com.bitsio.ficticiaprueba.models.DTO.AdminDTO;
import com.bitsio.ficticiaprueba.services.Admin.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Test
    void ListaAdmins() throws Exception {
        AdminDTO admin = new AdminDTO();
        admin.setIdentificacion("1");
        admin.setNombre_completo("Administrador1");

        List<AdminDTO> listaAdmins = List.of(admin);
        Mockito.when(adminService.getAllEstudios()).thenReturn(listaAdmins);

        mockMvc.perform(get("/api/v1/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identificacion").value("1"))
                .andExpect(jsonPath("$[0].nombre_completo").value("Administrador1"));
    }
}
