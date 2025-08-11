package com.bitsio.ficticiaprueba.controllers;

import com.bitsio.ficticiaprueba.models.DTO.CuentaDTO;
import com.bitsio.ficticiaprueba.models.DTO.LoginDTO;
import com.bitsio.ficticiaprueba.models.DTO.TokenDTO;
import com.bitsio.ficticiaprueba.services.Cuenta.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaService cuentaService;

    @GetMapping("/cuenta")
    public ResponseEntity<List<CuentaDTO>> listarCuenta() {
        return ResponseEntity.ok(cuentaService.getAllCuentas());
    }

    @PostMapping("/cuenta/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            TokenDTO token = cuentaService.login(loginDTO);
            return ResponseEntity.ok(token);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
