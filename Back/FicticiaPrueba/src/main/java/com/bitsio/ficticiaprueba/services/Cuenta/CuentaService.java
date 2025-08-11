package com.bitsio.ficticiaprueba.services.Cuenta;

import com.bitsio.ficticiaprueba.models.DTO.CuentaDTO;
import com.bitsio.ficticiaprueba.models.DTO.LoginDTO;
import com.bitsio.ficticiaprueba.models.DTO.TokenDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CuentaService {
     List<CuentaDTO> getAllCuentas();
     TokenDTO login (LoginDTO L);
}
