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
public class LopTinChiNgayId implements Serializable{
    @Column(name = "loptinchi_id")
    private Long loptinchi_id;
    @Column(name = "ngay_id")
    private Long ngay_id;
}
