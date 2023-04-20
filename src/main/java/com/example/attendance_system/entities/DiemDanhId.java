package com.example.attendance_system.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DiemDanhId implements Serializable {
    @Column(name = "loptinchingay_id")
    private LopTinChiNgayId loptinchingay_id;
    @Column(name = "sinhvien_id")
    private Long sinhvien_id;
}
