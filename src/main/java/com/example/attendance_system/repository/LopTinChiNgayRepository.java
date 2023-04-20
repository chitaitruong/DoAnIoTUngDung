package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance_system.entities.Lop;
import com.example.attendance_system.entities.LopTinChiNgay;
import com.example.attendance_system.entities.LopTinChiNgayId;

@Repository
public interface LopTinChiNgayRepository extends JpaRepository<LopTinChiNgay, LopTinChiNgayId> {
    //List<Lop> findByUserId(Long id);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
    //List<Lop> findLopBySinhviensId(Long sinhVienId);
}
