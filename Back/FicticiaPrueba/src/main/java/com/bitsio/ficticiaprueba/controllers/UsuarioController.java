package com.bitsio.ficticiaprueba.controllers;

import com.bitsio.ficticiaprueba.models.DTO.AltaDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioDTO;
import com.bitsio.ficticiaprueba.models.DTO.UsuarioFiltro;
import com.bitsio.ficticiaprueba.services.Usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping("/usuario/filtro")
    public ResponseEntity<List<UsuarioDTO>> filtrarUsuarios(@RequestBody UsuarioFiltro filtro) {
        List<UsuarioDTO> usuarios = usuarioService.filtrarUsuarios(filtro);
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/usuario/{identificacion}")
    public ResponseEntity<UsuarioDTO> listarUsuarioPorIndentifiacion(@PathVariable String identificacion) {
        return ResponseEntity.ok(usuarioService.findByIdentificacion(identificacion));
    }


    @PostMapping("/create/usuario")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody @Valid AltaDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuarioDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}/userE")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioEmail(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuarioSuEmail(id, usuarioDTO));
    }

    @PutMapping("/update/{id}/userC")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioContra(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuarioSuConstrania(id, usuarioDTO));
    }

    @GetMapping("/usuario/cantidad")
    public ResponseEntity<Long> contarUsuarios() {
        Long total = usuarioService.cantidadUser();
        return ResponseEntity.ok(total);
    }
}
