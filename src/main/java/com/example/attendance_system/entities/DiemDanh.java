package com.example.attendance_system.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diemdanh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiemDanh {
    @EmbeddedId
    private DiemDanhKey id;
    @ManyToOne
    @MapsId("sinhvien_id")
    @JoinColumn(name = "sinhvien_id")
    private SinhVien sinhvien;
    @ManyToOne
    @MapsId("phonglich_id")
    @JoinColumns({
            @JoinColumn(name = "lich_id"), 
            @JoinColumn(name = "phong_id")
    })
    private PhongLich phonglich;
    @Column(name = "trang_thai", nullable = false)
    private Long trang_thai;
    @Column(name = "ghi_chu")
    private String ghi_chu;
    @Column(name = "thoi_gian_quet_van_tay")
    private LocalDateTime thoi_gian_quet_van_tay;
    @Column(name = "thoi_gian_cap_nhat")
    private LocalDateTime thoi_gian_cap_nhat;
}
