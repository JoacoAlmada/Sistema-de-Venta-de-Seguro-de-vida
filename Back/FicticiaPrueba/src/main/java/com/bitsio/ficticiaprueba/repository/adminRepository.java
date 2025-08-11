package com.bitsio.ficticiaprueba.repository;

import com.bitsio.ficticiaprueba.models.entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface adminRepository extends JpaRepository<Admin, Integer>  {
}
