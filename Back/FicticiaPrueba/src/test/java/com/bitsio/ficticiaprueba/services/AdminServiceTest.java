package com.bitsio.ficticiaprueba.services;



import com.bitsio.ficticiaprueba.models.DTO.AdminDTO;
import com.bitsio.ficticiaprueba.models.entites.Admin;
import com.bitsio.ficticiaprueba.repository.adminRepository;
import com.bitsio.ficticiaprueba.services.Admin.AdminServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @Mock
    private adminRepository adminRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AdminServiceImp adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEstudios_CasoFeliz() {
        Admin admin1 = new Admin();
        admin1.setIdentificacion("A1");
        admin1.setNombre_completo("Admin 1");

        Admin admin2 = new Admin();
        admin2.setIdentificacion("A2");
        admin2.setNombre_completo("Admin 2");

        List<Admin> adminList = Arrays.asList(admin1, admin2);

        AdminDTO dto1 = new AdminDTO();
        dto1.setIdentificacion("A1");
        dto1.setNombre_completo("Admin 1");

        AdminDTO dto2 = new AdminDTO();
        dto2.setIdentificacion("A2");
        dto2.setNombre_completo("Admin 2");

        List<AdminDTO> expectedDtos = Arrays.asList(dto1, dto2);

        when(adminRepository.findAll()).thenReturn(adminList);
        Type listType = new TypeToken<List<AdminDTO>>() {}.getType();
        when(modelMapper.map(adminList, listType)).thenReturn(expectedDtos);

        List<AdminDTO> result = adminService.getAllEstudios();

        assertEquals(2, result.size());
        assertEquals("Admin 1", result.get(0).getNombre_completo());
        assertEquals("Admin 2", result.get(1).getNombre_completo());
        verify(adminRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(adminList, listType);
    }

    @Test
    void testGetAllEstudios_ListaVacia() {
        when(adminRepository.findAll()).thenReturn(Collections.emptyList());
        Type listType = new TypeToken<List<AdminDTO>>() {}.getType();
        when(modelMapper.map(Collections.emptyList(), listType)).thenReturn(Collections.emptyList());

        List<AdminDTO> result = adminService.getAllEstudios();

        assertEquals(0, result.size());
        verify(adminRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(Collections.emptyList(), listType);
    }

    @Test
    void testGetAllEstudios_ErrorEnRepositorio() {
        when(adminRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        try {
            adminService.getAllEstudios();
        } catch (RuntimeException e) {
            assertEquals("DB error", e.getMessage());
        }

        verify(adminRepository, times(1)).findAll();
        verifyNoInteractions(modelMapper);
    }
}
