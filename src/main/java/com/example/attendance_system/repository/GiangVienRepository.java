package com.example.attendance_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance_system.entities.GiangVien;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    boolean existsById(Long id);
}
