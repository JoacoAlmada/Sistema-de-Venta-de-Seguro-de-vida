package com.bitsio.ficticiaprueba.controllers;

import com.bitsio.ficticiaprueba.models.DTO.AdminDTO;
import com.bitsio.ficticiaprueba.services.Admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public ResponseEntity<List<AdminDTO>> listarAdmin() {
        return ResponseEntity.ok(adminService.getAllEstudios());
    }
}
