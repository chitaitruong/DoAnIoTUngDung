package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.Lop;

public interface LopRepository extends JpaRepository<Lop, Long> {
    //List<Lop> findByUserId(Long id);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
}
