package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.attendance_system.entities.DiemDanh;
import com.example.attendance_system.entities.DiemDanhKey;
import com.example.attendance_system.entities.IDiemDanh;

public interface DiemDanhRepository extends JpaRepository<DiemDanh, DiemDanhKey> {
    @Query(value = "select tam.id as sinhvien_id, tam.ho_ten, isnull(tam1.trang_thai,0) trang_thai, tam1.thoi_gian_quet_van_tay, tam1.thoi_gian_cap_nhat, tam1.ghi_chu from (select sv.id, sv.ho_ten from sinhvien sv join (select lsv.sinhvien_id from lop_sinhvien lsv where lsv.lop_id=:lop_id) ds_id_sv on ds_id_sv.sinhvien_id=sv.id) tam left join (select * from diemdanh dd where dd.lich_id=:lich_id and dd.phong_id=:phong_id) tam1 on tam.id=tam1.sinhvien_id", nativeQuery = true)
    List<IDiemDanh> findDiemDanhByLichPhong(@Param("lop_id") Long lop_id, @Param("phong_id") Long phong_id, @Param("lich_id") Long lich_id);
    /*select t.lich_id, t.phong_id, isnull(dd.trang_thai,0) trang_thai, dd.thoi_gian_quet_van_tay, dd.thoi_gian_cap_nhat, dd.ghi_chu  from (select pl.lich_id, pl.phong_id from phong_lich pl where pl.lop_id=1) t
    left join diemdanh dd on dd.lich_id=t.lich_id and dd.phong_id=t.phong_id and dd.sinhvien_id=1  
    */
}
