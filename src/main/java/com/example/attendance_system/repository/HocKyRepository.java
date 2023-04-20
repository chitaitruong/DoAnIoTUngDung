package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.HocKy;

public interface HocKyRepository extends JpaRepository<HocKy, Long> {
    //List<Lop> findByUserId(Long id);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
}
