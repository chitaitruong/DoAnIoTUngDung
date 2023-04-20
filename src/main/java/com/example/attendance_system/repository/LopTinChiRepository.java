package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.attendance_system.entities.GiangVien;
import com.example.attendance_system.entities.LopTinChi;

@Repository
public interface LopTinChiRepository extends JpaRepository<LopTinChi, Long> {
    
    List<LopTinChi> findByGiangvien(GiangVien giangvien);
    @Query(value = "SELECT u FROM LopTinChi u WHERE u.giangvien.id = :id")
    List<LopTinChi> findByGiangvienId(@Param("id") Long id);
}
