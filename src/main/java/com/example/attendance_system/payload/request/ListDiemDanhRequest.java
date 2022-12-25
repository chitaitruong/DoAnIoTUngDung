package com.example.attendance_system.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDiemDanhRequest {
    private String jwt;
    private Long lop_id;
    private Long phong_id;
    private Long lich_id;
}
