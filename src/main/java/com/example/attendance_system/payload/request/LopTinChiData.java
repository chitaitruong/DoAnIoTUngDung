package com.example.attendance_system.payload.request;

import java.util.ArrayList;
import java.util.Set;

import com.example.attendance_system.entities.Lop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopTinChiData {
    private Set<Long> ds_idlop;
    private Long sl_sinhvien;
    private Long so_tiet;
    private Long tiet_bd;
    private String thu;
    private Long giangvien_id;
    private Long hocky_id;
    private Long monhoc_id;
    private Long phong_id;
}
