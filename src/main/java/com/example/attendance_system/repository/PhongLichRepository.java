package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.attendance_system.entities.ILich;
import com.example.attendance_system.entities.ILichHoc;
// import com.example.attendance_system.entities.PhongLich;
// import com.example.attendance_system.entities.PhongLichKey;

// public interface PhongLichRepository extends JpaRepository<PhongLich, PhongLichKey> {
//     @Query(value = "select l.id lich_id, l.ngay, iif(l.ca=0,'SANG','CHIEU') ca, tam1.phong_id, tam1.ten_phong from (select tam.lich_id, p.id phong_id, p.ten_phong from (select phong_id, lich_id from phong_lich pl where pl.lop_id=:id) tam join phong p on p.id=tam.phong_id) tam1 join lich l on l.id=tam1.lich_id", nativeQuery = true)
//     List<ILich> findByLopId(Long id);
//     @Query(value = "select tam1.lich_id as lich_id, tam1.phong_id as phong_id from (select lsv.lop_id from lop_sinhvien lsv where lsv.sinhvien_id=:sinhvien_id) tam join (select pl.lich_id, pl.phong_id, pl.lop_id from phong_lich pl join (select l.id lich_id from lich l where l.ngay=:ngay and l.ca=:ca ) t on pl.lich_id=t.lich_id) tam1 on tam1.lop_id=tam.lop_id", nativeQuery = true)
//     ILichHoc findLichHocBySinhVienIdAndNgay(@Param("sinhvien_id") Long sinhvien_id, @Param("ngay") String ngay, @Param("ca") Boolean ca);
// }
