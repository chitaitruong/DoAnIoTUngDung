package com.example.attendance_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.attendance_system.entities.DiemDanh;
import com.example.attendance_system.entities.DiemDanhId;
import com.example.attendance_system.entities.IDiemDanh;
import com.example.attendance_system.entities.IDiemDanhSinhVien;

public interface DiemDanhRepository extends JpaRepository<DiemDanh, DiemDanhId> {
    @Query(value = "select ds_sv.id,ds_sv.username,ds_sv.hoten,ISNULL(ds_dd.trang_thai,0) trang_thai,ds_dd.thoi_gian_quet_van_tay,ds_dd.ghi_chu,ds_dd.thoi_gian_cap_nhat from (select sv.id, sv.username, sv.hoten from users sv join (select ltc_sv.sinhvien_id from loptinchi_sinhvien ltc_sv where ltc_sv.loptinchi_id = :lop_id) ds_svid on sv.id=ds_svid.sinhvien_id) ds_sv left join (select * from diemdanh dd where dd.loptinchi_id = :lop_id and dd.ngay_id = :ngay_id) ds_dd on ds_sv.id = ds_dd.sinhvien_id", nativeQuery = true)
    List<IDiemDanh> findDiemDanhByNgay(@Param("lop_id") Long lop_id, @Param("ngay_id") Long ngay_id);
    /*select t.lich_id, t.phong_id, isnull(dd.trang_thai,0) trang_thai, dd.thoi_gian_quet_van_tay, dd.thoi_gian_cap_nhat, dd.ghi_chu  from (select pl.lich_id, pl.phong_id from phong_lich pl where pl.lop_id=1) t
    left join diemdanh dd on dd.lich_id=t.lich_id and dd.phong_id=t.phong_id and dd.sinhvien_id=1  
    */
    @Query(value = "select ltc_sv.sinhvien_id, tam1.id from loptinchi_sinhvien ltc_sv join  (select ltc.id,ltc.monhoc_id,ltc.tiet_bd,ltc.so_tiet from loptinchi ltc join (select loptinchi_id from loptinchingay where ngay_id = :ngay_id) tam on tam.loptinchi_id = ltc.id where tiet_bd<=:gio and (tiet_bd+so_tiet)>=:gio) tam1 on ltc_sv.loptinchi_id = tam1.id and ltc_sv.sinhvien_id = :sinhvien_id",nativeQuery = true)
    IDiemDanhSinhVien findDiemDanh(@Param("ngay_id") Long ngay_id, @Param("sinhvien_id") Long sinhvien_id,@Param("gio") Long gio);
}
