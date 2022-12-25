package com.example.attendance_system.entities;

import java.time.LocalDateTime;

public interface IDiemDanh {
    Long getSinhvien_id();
    String getHo_ten();
    Long getTrang_thai();
    LocalDateTime getThoi_gian_quet_van_tay();
    LocalDateTime getThoi_gian_cap_nhat();
    String getGhi_chu();
}
