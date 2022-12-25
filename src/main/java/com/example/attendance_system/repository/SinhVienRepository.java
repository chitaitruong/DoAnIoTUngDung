package com.example.attendance_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.SinhVien;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    boolean existsById(Long id);
}
