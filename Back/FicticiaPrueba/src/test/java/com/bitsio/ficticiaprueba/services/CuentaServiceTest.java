package com.bitsio.ficticiaprueba.services;

import com.bitsio.ficticiaprueba.configs.AuthenticacionConfig;
import com.bitsio.ficticiaprueba.models.DTO.CuentaDTO;
import com.bitsio.ficticiaprueba.models.DTO.LoginDTO;
import com.bitsio.ficticiaprueba.models.DTO.TokenDTO;
import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import com.bitsio.ficticiaprueba.models.enums.Rol;
import com.bitsio.ficticiaprueba.repository.cuentaRepository;
import com.bitsio.ficticiaprueba.services.Cuenta.CuentaServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CuentaServiceTest {

    private cuentaRepository cuentaRepositoryMock;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoderMock;
    private AuthenticacionConfig authenticacionConfigMock;
    private CuentaServiceImp cuentaService;

    @BeforeEach
    void setUp() {
        cuentaRepositoryMock = mock(cuentaRepository.class);
        modelMapper = new ModelMapper();
        passwordEncoderMock = mock(BCryptPasswordEncoder.class);
        authenticacionConfigMock = mock(AuthenticacionConfig.class);
        cuentaService = new CuentaServiceImp(
                cuentaRepositoryMock,
                modelMapper,
                passwordEncoderMock,
                authenticacionConfigMock
        );
    }

    @Test
    void testGetAllCuentas() {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIdentificacion("123");
        cuenta1.setRol(Rol.Usuario);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setIdentificacion("456");
        cuenta2.setRol(Rol.Usuario);

        List<Cuenta> cuentas = Arrays.asList(cuenta1, cuenta2);
        when(cuentaRepositoryMock.findAll()).thenReturn(cuentas);

        List<CuentaDTO> cuentaDTOs = cuentaService.getAllCuentas();

        assertEquals(2, cuentaDTOs.size());
        assertEquals("123", cuentaDTOs.get(0).getIdentificacion());
        assertEquals("456", cuentaDTOs.get(1).getIdentificacion());
        verify(cuentaRepositoryMock, times(1)).findAll();
    }

    @Test
    void testLoginExitoso() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentificacion("user1");
        loginDTO.setContrasenia("password");

        Cuenta cuenta = new Cuenta();
        cuenta.setIdentificacion("user1");
        cuenta.setContrasenia("password");
        cuenta.setRol(Rol.Administrador);

        when(cuentaRepositoryMock.findByIdentificacion("user1"))
                .thenReturn(Optional.of(cuenta));
        when(authenticacionConfigMock.generateToken("user1", Rol.Administrador))
                .thenReturn("fake-jwt-token");

        TokenDTO tokenDTO = cuentaService.login(loginDTO);

        assertNotNull(tokenDTO);
        assertEquals("fake-jwt-token", tokenDTO.getToken());
        verify(cuentaRepositoryMock, times(1)).findByIdentificacion("user1");
        verify(authenticacionConfigMock, times(1)).generateToken("user1", Rol.Administrador);
    }

    @Test
    void testLoginUsuarioNoEncontrado() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentificacion("inexistente");
        loginDTO.setContrasenia("123");

        when(cuentaRepositoryMock.findByIdentificacion("inexistente"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> cuentaService.login(loginDTO));
        assertEquals("Usuario no encontrado", ex.getMessage());
        verify(cuentaRepositoryMock, times(1)).findByIdentificacion("inexistente");
    }

    @Test
    void testLoginContraseniaIncorrecta() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setIdentificacion("user1");
        loginDTO.setContrasenia("incorrecta");

        Cuenta cuenta = new Cuenta();
        cuenta.setIdentificacion("user1");
        cuenta.setContrasenia("password");
        cuenta.setRol(Rol.Usuario);

        when(cuentaRepositoryMock.findByIdentificacion("user1"))
                .thenReturn(Optional.of(cuenta));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> cuentaService.login(loginDTO));
        assertEquals("Contraseña incorrecta", ex.getMessage());
        verify(cuentaRepositoryMock, times(1)).findByIdentificacion("user1");
    }
}