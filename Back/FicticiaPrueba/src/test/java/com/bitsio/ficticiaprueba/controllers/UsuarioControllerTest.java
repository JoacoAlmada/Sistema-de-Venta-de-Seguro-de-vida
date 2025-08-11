package com.bitsio.ficticiaprueba.controllers;

import com.bitsio.ficticiaprueba.models.DTO.AltaDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioFiltro;
import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import com.bitsio.ficticiaprueba.services.Usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarUsuarios() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId_usuario(1);
        usuario.setNombre_completo("Juan Perez");
        usuario.setIdentificacion("1234");
        usuario.setEdad(22);
        usuario.setGenero(Genero.Masculino);
        usuario.setEstado(Estado.Activo);

        List<UsuarioDTO> lista = List.of(usuario);

        Mockito.when(usuarioService.findAll()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/usuario")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_usuario").value(1))
                .andExpect(jsonPath("$[0].nombre_completo").value("Juan Perez"))
                .andExpect(jsonPath("$[0].identificacion").value("1234"))
                .andExpect(jsonPath("$[0].edad").value(22))
                .andExpect(jsonPath("$[0].genero").value("Masculino"))
                .andExpect(jsonPath("$[0].estado").value("Activo"));

    }

    @Test
    void crearUsuario() throws Exception {
        AltaDTO usuarioInput = new AltaDTO();
        usuarioInput.setNombre_completo("Nuevo Usuario");
        usuarioInput.setIdentificacion("5678");
        usuarioInput.setEdad(22);
        usuarioInput.setGenero(Genero.Masculino);
        usuarioInput.setEstado(Estado.Activo);
        usuarioInput.setContrasenia("pass123");
        usuarioInput.setSeguro(Seguros.Estandar);

        UsuarioDTO usuarioReturn = new UsuarioDTO();
        usuarioReturn.setId_usuario(2);
        usuarioReturn.setNombre_completo("Nuevo Usuario");
        usuarioReturn.setIdentificacion("5678");
        usuarioReturn.setEdad(22);
        usuarioReturn.setGenero(Genero.Masculino);
        usuarioReturn.setEstado(Estado.Activo);
        usuarioReturn.setSeguro(Seguros.Estandar);

        Mockito.when(usuarioService.createUsuario(any(AltaDTO.class))).thenReturn(usuarioReturn);

        mockMvc.perform(post("/api/v1/create/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_usuario").value(2))
                .andExpect(jsonPath("$.nombre_completo").value("Nuevo Usuario"))
                .andExpect(jsonPath("$.identificacion").value("5678"))
                .andExpect(jsonPath("$.edad").value(22))
                .andExpect(jsonPath("$.genero").value("Masculino"))
                .andExpect(jsonPath("$.estado").value("Activo"))
                .andExpect(jsonPath("$.seguro").value("Estandar"));
    }

    @Test
    void actualizarUsuario() throws Exception {
        UsuarioDTO usuarioUpdate = new UsuarioDTO();
        usuarioUpdate.setNombre_completo("Usuario Actualizado");
        usuarioUpdate.setIdentificacion("1234");
        usuarioUpdate.setEdad(22);
        usuarioUpdate.setGenero(Genero.Masculino);
        usuarioUpdate.setEstado(Estado.Activo);
        usuarioUpdate.setContrasenia("pass123");
        usuarioUpdate.setSeguro(Seguros.Basico);
        usuarioUpdate.setEmail("usuario@example.com");
        usuarioUpdate.setTelefono("1234567890");

        UsuarioDTO usuarioReturn = new UsuarioDTO();
        usuarioReturn.setId_usuario(1);
        usuarioReturn.setNombre_completo("Usuario Actualizado");
        usuarioReturn.setIdentificacion("1234");
        usuarioReturn.setEdad(22);
        usuarioReturn.setGenero(Genero.Masculino);
        usuarioReturn.setEstado(Estado.Activo);
        usuarioReturn.setSeguro(Seguros.Basico);
        usuarioReturn.setEmail("usuario@example.com");
        usuarioReturn.setTelefono("1234567890");

        Mockito.when(usuarioService.updateUsuario(eq(1), any(UsuarioDTO.class))).thenReturn(usuarioReturn);

        mockMvc.perform(put("/api/v1/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_usuario").value(1))
                .andExpect(jsonPath("$.nombre_completo").value("Usuario Actualizado"))
                .andExpect(jsonPath("$.identificacion").value("1234"))
                .andExpect(jsonPath("$.edad").value(22))
                .andExpect(jsonPath("$.genero").value("Masculino"))
                .andExpect(jsonPath("$.estado").value("Activo"))
                .andExpect(jsonPath("$.seguro").value("Basico"))
                .andExpect(jsonPath("$.email").value("usuario@example.com"))
                .andExpect(jsonPath("$.telefono").value("1234567890"));

        verify(usuarioService).updateUsuario(eq(1), Mockito.any(UsuarioDTO.class));
    }
    @Test
    void eliminarUsuario() throws Exception {
        doNothing().when(usuarioService).deleteUsuario(1);

        mockMvc.perform(delete("/api/v1/delete/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).deleteUsuario(1);
    }

    @Test
    void filtrarUsuarios() throws Exception {
        UsuarioFiltro filtro = new UsuarioFiltro();
        filtro.setGenero("Masculino");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId_usuario(1);
        usuario.setNombre_completo("Filtrado");
        usuario.setIdentificacion("999");
        usuario.setEdad(30);
        usuario.setGenero(Genero.Masculino);
        usuario.setEstado(Estado.Activo);

        List<UsuarioDTO> lista = List.of(usuario);

        Mockito.when(usuarioService.filtrarUsuarios(any(UsuarioFiltro.class))).thenReturn(lista);

        mockMvc.perform(post("/api/v1/usuario/filtro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filtro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_usuario").value(1))
                .andExpect(jsonPath("$[0].nombre_completo").value("Filtrado"))
                .andExpect(jsonPath("$[0].identificacion").value("999"));
    }

    @Test
    void listarUsuarioPorIdentificacion() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId_usuario(1);
        usuario.setNombre_completo("Encontrado");
        usuario.setIdentificacion("abc123");
        usuario.setEdad(25);
        usuario.setGenero(Genero.Femenino);
        usuario.setEstado(Estado.Activo);

        Mockito.when(usuarioService.findByIdentificacion("abc123")).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuario/abc123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_completo").value("Encontrado"))
                .andExpect(jsonPath("$.identificacion").value("abc123"));
    }

    @Test
    void actualizarUsuarioEmail() throws Exception {
        UsuarioDTO usuarioUpdate = new UsuarioDTO();
        usuarioUpdate.setNombre_completo("Cambio Email");
        usuarioUpdate.setIdentificacion("1234");
        usuarioUpdate.setEdad(22);
        usuarioUpdate.setGenero(Genero.Masculino);
        usuarioUpdate.setEstado(Estado.Activo);
        usuarioUpdate.setEmail("nuevo@mail.com");
        usuarioUpdate.setTelefono("123456789");
        usuarioUpdate.setContrasenia("contrasenia123");
        usuarioUpdate.setSeguro(Seguros.Basico);

        UsuarioDTO usuarioReturn = new UsuarioDTO();
        usuarioReturn.setId_usuario(1);
        usuarioReturn.setNombre_completo("Cambio Email");
        usuarioReturn.setIdentificacion("1234");
        usuarioReturn.setEdad(22);
        usuarioReturn.setGenero(Genero.Masculino);
        usuarioReturn.setEstado(Estado.Activo);
        usuarioReturn.setEmail("nuevo@mail.com");
        usuarioUpdate.setTelefono("123456789");
        usuarioReturn.setSeguro(Seguros.Basico);

        Mockito.when(usuarioService.updateUsuarioSuEmail(eq(1), any(UsuarioDTO.class)))
                .thenReturn(usuarioReturn);

        mockMvc.perform(put("/api/v1/update/1/userE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("nuevo@mail.com"));
    }

    @Test
    void actualizarUsuarioContra() throws Exception {
        UsuarioDTO usuarioUpdate = new UsuarioDTO();
        usuarioUpdate.setNombre_completo("Cambio Contra");
        usuarioUpdate.setIdentificacion("1234");
        usuarioUpdate.setEdad(22);
        usuarioUpdate.setGenero(Genero.Masculino);
        usuarioUpdate.setEstado(Estado.Activo);
        usuarioUpdate.setContrasenia("nueva123");
        usuarioUpdate.setEmail("usuario@mail.com");
        usuarioUpdate.setTelefono("123456789");
        usuarioUpdate.setSeguro(Seguros.Premium);

        UsuarioDTO usuarioReturn = new UsuarioDTO();
        usuarioReturn.setId_usuario(1);
        usuarioReturn.setNombre_completo("Cambio Contra");
        usuarioReturn.setIdentificacion("1234");
        usuarioReturn.setEdad(22);
        usuarioReturn.setGenero(Genero.Masculino);
        usuarioReturn.setEstado(Estado.Activo);
        usuarioReturn.setContrasenia("nueva123");
        usuarioUpdate.setEmail("usuario@mail.com");
        usuarioUpdate.setTelefono("123456789");
        usuarioReturn.setSeguro(Seguros.Premium);

        Mockito.when(usuarioService.updateUsuarioSuConstrania(eq(1), any(UsuarioDTO.class)))
                .thenReturn(usuarioReturn);

        mockMvc.perform(put("/api/v1/update/1/userC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contrasenia").value("nueva123"));
    }
}
