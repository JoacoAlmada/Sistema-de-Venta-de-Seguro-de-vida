package com.bitsio.ficticiaprueba.services.Admin;

import com.bitsio.ficticiaprueba.models.DTO.AdminDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<AdminDTO> getAllEstudios();
}
