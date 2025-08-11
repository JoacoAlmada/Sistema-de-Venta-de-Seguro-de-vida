package com.bitsio.ficticiaprueba.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bitsio.ficticiaprueba.models.DTO.AltaDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioFiltro;
import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import com.bitsio.ficticiaprueba.models.entites.Usuario;
import com.bitsio.ficticiaprueba.models.enums.Estado;
import com.bitsio.ficticiaprueba.models.enums.Genero;
import com.bitsio.ficticiaprueba.models.enums.Rol;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import com.bitsio.ficticiaprueba.repository.cuentaRepository;
import com.bitsio.ficticiaprueba.repository.usuarioRepository;
import com.bitsio.ficticiaprueba.services.Usuario.UsuarioServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class UsuarioServiceTest {
    @Mock
    private usuarioRepository usuarioRepository;

    @Mock
    private cuentaRepository cuentaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UsuarioServiceImp usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerUsuarios() {
        List<Usuario> usuarios = List.of(new Usuario());
        List<UsuarioDTO> usuariosDTO = List.of(new UsuarioDTO());

        when(usuarioRepository.findAll()).thenReturn(usuarios);
        when(modelMapper.map(usuarios, new TypeToken<List<UsuarioDTO>>() {}.getType())).thenReturn(usuariosDTO);

        List<UsuarioDTO> result = usuarioService.findAll();

        assertEquals(usuariosDTO, result);
        verify(usuarioRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(usuarios, new TypeToken<List<UsuarioDTO>>() {}.getType());
    }

    @Test
    void testCrearUsuario_VerificarCuenta_CaminoError() {
        AltaDTO usuarioDTO = new AltaDTO();
        usuarioDTO.setIdentificacion("123");

        when(cuentaRepository.existsById("123")).thenReturn(true);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> usuarioService.createUsuario(usuarioDTO));
        assertEquals("Ya existe una cuenta con esa identificacion", ex.getMessage());

        verify(cuentaRepository, times(1)).existsById("123");
        verify(cuentaRepository, never()).save(any());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testCrearUsuario() {
        AltaDTO usuarioDTO = new AltaDTO();
        usuarioDTO.setIdentificacion("123");
        usuarioDTO.setNombre_completo("Juan Perez");
        usuarioDTO.setSeguro(Seguros.Basico);

        Cuenta cuenta = new Cuenta();
        cuenta.setIdentificacion("123");
        cuenta.setRol(Rol.Usuario);

        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setIdentificacion("123");
        usuarioEntity.setNombre_completo("Juan Perez");
        usuarioEntity.setSeguro(Seguros.Basico);

        Usuario savedUsuario = new Usuario();
        savedUsuario.setId_usuario(1);
        savedUsuario.setIdentificacion("123");
        savedUsuario.setNombre_completo("Juan Perez");
        savedUsuario.setSeguro(Seguros.Basico);

        UsuarioDTO savedUsuarioDTO = new UsuarioDTO();
        savedUsuarioDTO.setId_usuario(1);
        savedUsuarioDTO.setIdentificacion("123");
        savedUsuarioDTO.setNombre_completo("Juan Perez");
        savedUsuarioDTO.setSeguro(Seguros.Basico);

        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);
        doNothing().when(cuentaRepository).flush();
        when(cuentaRepository.existsById("123")).thenReturn(false, true);
        when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuarioEntity);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(savedUsuario);
        when(modelMapper.map(savedUsuario, UsuarioDTO.class)).thenReturn(savedUsuarioDTO);

        UsuarioDTO result = usuarioService.createUsuario(usuarioDTO);

        assertNotNull(result);
        assertEquals("123", result.getIdentificacion());
        assertEquals("Juan Perez", result.getNombre_completo());
        assertEquals(Seguros.Basico, result.getSeguro());

        verify(cuentaRepository, times(2)).existsById("123");
        verify(cuentaRepository).save(any(Cuenta.class));
        verify(cuentaRepository).flush();
        verify(usuarioRepository).save(usuarioEntity);
        verify(modelMapper).map(usuarioDTO, Usuario.class);
        verify(modelMapper).map(savedUsuario, UsuarioDTO.class);
    }


    @Test
    void testActualizarUsuario() {
        Usuario existing = new Usuario();
        existing.setId_usuario(1);
        existing.setNombre_completo("Old Name");
        existing.setSeguro(Seguros.Basico);

        UsuarioDTO updateDTO = new UsuarioDTO();
        updateDTO.setNombre_completo("New Name");
        updateDTO.setEdad(30);
        updateDTO.setGenero(Genero.Masculino);
        updateDTO.setEstado(Estado.Activo);
        updateDTO.setManeja(true);
        updateDTO.setUsa_lentes(false);
        updateDTO.setDiabetico(false);
        updateDTO.setOtra_enfermedad(false);
        updateDTO.setCual_enfermedad(null);
        updateDTO.setSeguro(Seguros.Premium);

        Usuario updated = new Usuario();
        updated.setId_usuario(1);
        updated.setNombre_completo("New Name");
        updated.setSeguro(Seguros.Premium);

        UsuarioDTO updatedDTO = new UsuarioDTO();
        updatedDTO.setId_usuario(1);
        updatedDTO.setNombre_completo("New Name");
        updatedDTO.setSeguro(Seguros.Premium);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(existing));
        when(usuarioRepository.save(existing)).thenReturn(updated);
        when(modelMapper.map(updated, UsuarioDTO.class)).thenReturn(updatedDTO);

        UsuarioDTO result = usuarioService.updateUsuario(1, updateDTO);

        assertEquals("New Name", result.getNombre_completo());
        assertEquals(Seguros.Premium, result.getSeguro());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).save(existing);
        verify(modelMapper).map(updated, UsuarioDTO.class);
    }

    @Test
    void testActualizarUsuario_404() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.updateUsuario(1, new UsuarioDTO());
        });

        assertEquals("Usuario no encontrado", ex.getMessage());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setIdentificacion("123");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);
        doNothing().when(cuentaRepository).deleteById("123");

        usuarioService.deleteUsuario(1);

        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).delete(usuario);
        verify(cuentaRepository).deleteById("123");
    }

    @Test
    void testEliminarUsuario_404() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            usuarioService.deleteUsuario(1);
        });

        assertEquals("Usuario no encontrado", ex.getMessage());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository, never()).delete(any());
        verify(cuentaRepository, never()).deleteById(any());
    }
    @Test
    void testFindByIdentificacion_Exito() {
        Usuario usuario = new Usuario();
        usuario.setIdentificacion("abc");
        usuario.setNombre_completo("Juan");

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdentificacion("abc");
        usuarioDTO.setNombre_completo("Juan");

        when(usuarioRepository.findByIdentificacion("abc")).thenReturn(Optional.of(usuario));
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.findByIdentificacion("abc");

        assertEquals("abc", result.getIdentificacion());
        assertEquals("Juan", result.getNombre_completo());
        verify(usuarioRepository).findByIdentificacion("abc");
        verify(modelMapper).map(usuario, UsuarioDTO.class);
    }

    @Test
    void testFindByIdentificacion_NoEncontrado() {
        when(usuarioRepository.findByIdentificacion("xyz")).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> usuarioService.findByIdentificacion("xyz"));

        assertTrue(ex.getMessage().contains("Usuario no encontrado"));
        verify(usuarioRepository).findByIdentificacion("xyz");
    }

    @Test
    void testFiltrarUsuarios_PorGeneroEstadoYSeguro() {
        Usuario u1 = new Usuario();
        u1.setGenero(Genero.Masculino);
        u1.setEstado(Estado.Activo);
        u1.setSeguro(Seguros.Basico);

        Usuario u2 = new Usuario();
        u2.setGenero(Genero.Femenino);
        u2.setEstado(Estado.Inactivo);
        u2.setSeguro(Seguros.Premium);

        List<Usuario> lista = Arrays.asList(u1, u2);
        when(usuarioRepository.findAll()).thenReturn(lista);

        UsuarioFiltro filtro = new UsuarioFiltro();
        filtro.setGenero("Masculino");
        filtro.setEstado("Activo");
        filtro.setSeguro("Basico");

        List<UsuarioDTO> listaDTO = List.of(new UsuarioDTO());
        when(modelMapper.map(anyList(), any(Type.class))).thenReturn(listaDTO);

        List<UsuarioDTO> result = usuarioService.filtrarUsuarios(filtro);

        assertEquals(1, result.size());
        verify(usuarioRepository).findAll();
        verify(modelMapper).map(anyList(), any(Type.class));
    }

    @Test
    void testUpdateUsuarioSuConstrania_Exito() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setIdentificacion("abc");

        Cuenta cuenta = new Cuenta();
        cuenta.setIdentificacion("abc");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdentificacion("abc");
        dto.setContrasenia("newpass");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(cuentaRepository.findByIdentificacion("abc")).thenReturn(Optional.of(cuenta));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(dto);

        UsuarioDTO result = usuarioService.updateUsuarioSuConstrania(1, dto);

        assertEquals("newpass", result.getContrasenia());
        verify(usuarioRepository).findById(1);
        verify(cuentaRepository).findByIdentificacion("abc");
        verify(cuentaRepository).save(cuenta);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testUpdateUsuarioSuConstrania_UsuarioNoEncontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> usuarioService.updateUsuarioSuConstrania(1, new UsuarioDTO()));

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void testUpdateUsuarioSuConstrania_CuentaNoEncontrada() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setIdentificacion("abc");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdentificacion("abc");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(cuentaRepository.findByIdentificacion("abc")).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> usuarioService.updateUsuarioSuConstrania(1, dto));

        assertEquals("Cuenta no encontrada", ex.getMessage());
    }

    @Test
    void testUpdateUsuarioSuEmail_Exito() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail("nuevo@email.com");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(dto);

        UsuarioDTO result = usuarioService.updateUsuarioSuEmail(1, dto);

        assertEquals("nuevo@email.com", result.getEmail());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testUpdateUsuarioSuEmail_UsuarioNoEncontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> usuarioService.updateUsuarioSuEmail(1, new UsuarioDTO()));

        assertEquals("Usuario no encontrado", ex.getMessage());
    }
}
