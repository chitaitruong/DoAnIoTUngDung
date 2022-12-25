package com.example.attendance_system.entities;

import java.time.LocalDate;

public interface ILich {
    Long getLich_id();
    LocalDate getNgay();
    String getCa();
    Long getPhong_id();
    String getTen_phong();
}
