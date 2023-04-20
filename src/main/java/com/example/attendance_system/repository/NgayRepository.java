package com.example.attendance_system.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance_system.entities.Lop;
import com.example.attendance_system.entities.Ngay;

@Repository
public interface NgayRepository extends JpaRepository<Ngay, Long> {
    //List<Lop> findByUserId(Long id);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
    Optional<Ngay> findByNgay(Date ngay);
}
