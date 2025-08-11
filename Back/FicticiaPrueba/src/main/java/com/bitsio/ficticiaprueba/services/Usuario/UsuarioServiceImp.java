package com.bitsio.ficticiaprueba.services.Usuario;

import com.bitsio.ficticiaprueba.models.DTO.AltaDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioFiltro;
import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import com.bitsio.ficticiaprueba.models.entites.Usuario;
import com.bitsio.ficticiaprueba.models.enums.Rol;
import com.bitsio.ficticiaprueba.models.enums.Seguros;
import com.bitsio.ficticiaprueba.repository.cuentaRepository;
import com.bitsio.ficticiaprueba.repository.usuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
    private final usuarioRepository UsuarioRepository;
    private final cuentaRepository CuentaRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = UsuarioRepository.findAll();
        return modelMapper.map(usuarios,new TypeToken<List<UsuarioDTO>>(){}.getType());
    }

    @Override
    public UsuarioDTO findByIdentificacion(String identificacion) {
        Optional<Usuario> usuario = UsuarioRepository.findByIdentificacion(identificacion);
        if (usuario.isPresent()) {
            return modelMapper.map(usuario.get(),UsuarioDTO.class);
        }
        else {
            throw new EntityNotFoundException("Usuario no encontrado por identificacion" + identificacion);
        }
    }

    @Override
    public List<UsuarioDTO> filtrarUsuarios(UsuarioFiltro filtro) {
        List<Usuario> usuarios = UsuarioRepository.findAll();

        if (filtro.getGenero() != null && !filtro.getGenero().isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getGenero().toString().equalsIgnoreCase(filtro.getGenero()))
                    .collect(Collectors.toList());
        }

        if (filtro.getEstado() != null && !filtro.getEstado().isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getEstado().toString().equalsIgnoreCase(filtro.getEstado()))
                    .collect(Collectors.toList());
        }

        if (filtro.getSeguro() != null && !filtro.getSeguro().isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getSeguro().toString().equalsIgnoreCase(filtro.getSeguro()))
                    .collect(Collectors.toList());
        }

        if (filtro.getUsa_lentes() != null) {
            usuarios = usuarios.stream()
                    .filter(u -> Objects.equals(u.getUsa_lentes(), filtro.getUsa_lentes()))
                    .collect(Collectors.toList());
        }

        if (filtro.getManeja() != null) {
            usuarios = usuarios.stream()
                    .filter(u -> Objects.equals(u.getManeja(), filtro.getManeja()))
                    .collect(Collectors.toList());
        }

        if (filtro.getDiabetico() != null) {
            usuarios = usuarios.stream()
                    .filter(u -> Objects.equals(u.getDiabetico(), filtro.getDiabetico()))
                    .collect(Collectors.toList());
        }

        if (filtro.getOtra_enfermedad() != null) {
            usuarios = usuarios.stream()
                    .filter(u -> Objects.equals(u.getOtra_enfermedad(), filtro.getOtra_enfermedad()))
                    .collect(Collectors.toList());
        }

        return modelMapper.map(usuarios, new TypeToken<List<UsuarioDTO>>(){}.getType());
    }

    @Override
    @Transactional
    public UsuarioDTO createUsuario(AltaDTO usuario) {
        if (CuentaRepository.existsById(usuario.getIdentificacion())) {
            throw new IllegalStateException("Ya existe una cuenta con esa identificacion");
        }

        try {
            Cuenta cuenta = new Cuenta();
            cuenta.setIdentificacion(usuario.getIdentificacion());
            cuenta.setRol(Rol.Usuario);
            cuenta.setContrasenia(usuario.getContrasenia());

            Cuenta savedCuenta = CuentaRepository.save(cuenta);
            CuentaRepository.flush(); // Forzar a la base de datos

            if (!CuentaRepository.existsById(usuario.getIdentificacion())) {
                throw new RuntimeException("La cuenta no se pudo crear correctamente");
            }

            System.out.println("Cuenta creada exitosamente: " + savedCuenta.getIdentificacion());

            Usuario U = modelMapper.map(usuario, Usuario.class);
            U.setIdentificacion(usuario.getIdentificacion());

            Usuario saved = UsuarioRepository.save(U);
            return modelMapper.map(saved, UsuarioDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public UsuarioDTO updateUsuario(Integer id_usuario, UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setNombre_completo(usuarioDTO.getNombre_completo());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setSeguro(usuarioDTO.getSeguro());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setManeja(usuarioDTO.getManeja());
        usuario.setUsa_lentes(usuarioDTO.getUsa_lentes());
        usuario.setDiabetico(usuarioDTO.getDiabetico());
        usuario.setOtra_enfermedad(usuarioDTO.getOtra_enfermedad());
        usuario.setCual_enfermedad(usuarioDTO.getCual_enfermedad());

        if (usuario.getCuenta() != null && usuarioDTO.getContrasenia() != null) {
            Cuenta cuenta = usuario.getCuenta();
            cuenta.setContrasenia(usuarioDTO.getContrasenia());
            CuentaRepository.save(cuenta);
        }

        Usuario updated = UsuarioRepository.save(usuario);
        return modelMapper.map(updated, UsuarioDTO.class);
    }

    @Override
    public void deleteUsuario(Integer idUsuario) {
        Usuario usuario =  UsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        UsuarioRepository.delete(usuario);
        CuentaRepository.deleteById(usuario.getIdentificacion());
    }

    @Override
    public UsuarioDTO updateUsuarioSuConstrania(Integer id_usuario, UsuarioDTO UsuarioDTO) {
        Usuario usuario = UsuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setContrasenia(UsuarioDTO.getContrasenia());

        Cuenta cuenta = CuentaRepository.findByIdentificacion(UsuarioDTO.getIdentificacion())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));

        cuenta.setContrasenia(UsuarioDTO.getContrasenia());
        CuentaRepository.save(cuenta);

        //Para dejarla encriptada
        //cuenta.setContrasenia(passwordEncoder.encode(UsuarioDTO.getContrasenia()));
        //CuentaRepository.save(cuenta);

        Usuario updated = UsuarioRepository.save(usuario);
        return modelMapper.map(updated,UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO updateUsuarioSuEmail(Integer id_usuario, UsuarioDTO UsuarioDTO) {
        Usuario usuario = UsuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setEmail(UsuarioDTO.getEmail());

        Usuario updated = UsuarioRepository.save(usuario);
        return modelMapper.map(updated,UsuarioDTO.class);
    }

    @Override
    public Long cantidadUser() {
        return UsuarioRepository.count();
    }


}
