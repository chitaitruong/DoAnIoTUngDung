package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, Long> {

    boolean existsByMonhoc(String monhoc);
    //List<Lop> findByUserId(Long id);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);

    boolean existsByMa(String ma_monhoc);
}
