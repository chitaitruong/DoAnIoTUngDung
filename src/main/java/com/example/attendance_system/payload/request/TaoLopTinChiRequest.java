package com.example.attendance_system.payload.request;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaoLopTinChiRequest {
    private Set<Long> ds_idlop;
    private Set<String> ds_ngay;
    private Long sl_sinhvien;
    private Long so_tiet;
    private Long tiet_bd;
    private String thu;
    private Long giangvien_id;
    private Long hocky_id;
    private Long monhoc_id;
    private Long phong_id;
}
