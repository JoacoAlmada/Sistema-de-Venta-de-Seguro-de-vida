package com.bitsio.ficticiaprueba.services.Admin;

import com.bitsio.ficticiaprueba.models.DTO.AdminDTO;
import com.bitsio.ficticiaprueba.models.entites.Admin;
import com.bitsio.ficticiaprueba.repository.adminRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final adminRepository AdminRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<AdminDTO> getAllEstudios() {
       List<Admin> admins = AdminRepository.findAll();
       return modelMapper.map(admins,new TypeToken<List<AdminDTO>>(){}.getType());
    }
}
