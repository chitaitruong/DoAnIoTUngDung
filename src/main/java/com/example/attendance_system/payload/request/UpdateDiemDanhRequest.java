package com.example.attendance_system.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDiemDanhRequest {
    private Long sinhvien_id;
    private Long ngay_id;
    private Long loptinchi_id;
    private String ghi_chu;
    private Long trang_thai;
}
