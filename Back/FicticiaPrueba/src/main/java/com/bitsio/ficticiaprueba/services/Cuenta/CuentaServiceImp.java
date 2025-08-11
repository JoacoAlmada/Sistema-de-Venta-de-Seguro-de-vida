package com.bitsio.ficticiaprueba.services.Cuenta;

import com.bitsio.ficticiaprueba.configs.AuthenticacionConfig;
import com.bitsio.ficticiaprueba.models.DTO.CuentaDTO;
import com.bitsio.ficticiaprueba.models.DTO.LoginDTO;
import com.bitsio.ficticiaprueba.models.DTO.TokenDTO;
import com.bitsio.ficticiaprueba.models.entites.Cuenta;
import com.bitsio.ficticiaprueba.models.enums.Rol;
import com.bitsio.ficticiaprueba.repository.cuentaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImp implements CuentaService {
    private final cuentaRepository cuentaRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticacionConfig authenticacionConfig;


    @Override
    public List<CuentaDTO> getAllCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return modelMapper.map(cuentas,new TypeToken<List<CuentaDTO>>(){}.getType());
    }

    @Override
    public TokenDTO login(LoginDTO L) {
        Cuenta cuenta = cuentaRepository.findByIdentificacion(L.getIdentificacion())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!L.getContrasenia().equals(cuenta.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        //bcrypt cifrado
       // if (!passwordEncoder.matches(loginDTO.getContrasenia(), cuenta.getContrasenia())) {
       //     throw new RuntimeException("Contraseña incorrecta");
       // }

        Rol rolReal = cuenta.getRol();
        String token = authenticacionConfig.generateToken(cuenta.getIdentificacion(), rolReal);

        return new TokenDTO(token);
    }
}
