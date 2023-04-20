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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private DiemDanhId id;
    @ManyToOne
    @MapsId("sinhvien_id")
    @JoinColumn(name = "sinhvien_id")
    @JsonIgnore
    private SinhVien sinhvien;
    @ManyToOne
    @MapsId("loptinchingay_id")
    @JsonIgnore
    @JoinColumns({
        @JoinColumn(name="loptinchi_id", referencedColumnName="loptinchi_id"),
        @JoinColumn(name="ngay_id", referencedColumnName="ngay_id")
    })
    private LopTinChiNgay loptinchingay;
    @Column(name = "trang_thai", nullable = false)
    private Long trang_thai;
    @Column(name = "ghi_chu")
    private String ghi_chu;
    @Column(name = "thoi_gian_quet_van_tay")
    private LocalDateTime thoi_gian_quet_van_tay;
    @Column(name = "thoi_gian_cap_nhat")
    private LocalDateTime thoi_gian_cap_nhat;
    
}
