package com.bitsio.ficticiaprueba.services.Usuario;

import com.bitsio.ficticiaprueba.models.DTO.AltaDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioFiltro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findByIdentificacion(String identificacion);
    List<UsuarioDTO> filtrarUsuarios(UsuarioFiltro filtro);
    UsuarioDTO createUsuario(AltaDTO usuario);
    UsuarioDTO updateUsuario(Integer id_usuario, UsuarioDTO usuarioDTO);
    void deleteUsuario(Integer idUsuario);
    UsuarioDTO updateUsuarioSuConstrania(Integer id_usuario, UsuarioDTO UsuarioDTO);
    UsuarioDTO updateUsuarioSuEmail(Integer id_usuario, UsuarioDTO UsuarioDTO);
    Long cantidadUser();
}
