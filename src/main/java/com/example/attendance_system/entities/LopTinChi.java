package com.example.attendance_system.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loptinchi")//, uniqueConstraints = { @UniqueConstraint(name = "UK_id_ltth_so_lop", columnNames = { "id", "lt_th","so_lop" })})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopTinChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sl_sinhvien", nullable = false)
    private Long sl_sinhvien;
    @Column(name = "thu", nullable = false)
    private String thu;
    @Column(name = "tiet_bd", nullable = false)
    private Long tiet_bd;
    @Column(name = "so_tiet", nullable = false)
    private Long so_tiet;
    @ManyToMany
    @JoinTable(name = "loptinchi_lop", joinColumns = @JoinColumn(name = "loptinchi_id"), inverseJoinColumns = @JoinColumn(name = "lop_id"))
    @JsonIgnore
    private Set<Lop> ds_lop;
    @ManyToMany
    @JoinTable(name = "loptinchi_sinhvien", joinColumns = @JoinColumn(name = "loptinchi_id"), inverseJoinColumns = @JoinColumn(name = "sinhvien_id"))
    @JsonIgnore
    private Set<SinhVien> ds_sinhvien;
    @ManyToOne()
    @JoinColumn(name="monhoc_id", nullable=false)
    // @JsonIgnore
    private MonHoc monhoc;
    @ManyToOne()
    @JoinColumn(name="hocky_id", nullable=false)
    // @JsonIgnore
    private HocKy hocky;
    @ManyToOne()
    @JoinColumn(name="phong_id", nullable=false)
    // @JsonIgnore
    private Phong phong;
    @ManyToOne()
    @JoinColumn(name="giangvien_id", nullable=false)
    @JsonIgnore
    private GiangVien giangvien;
    @OneToMany(mappedBy = "loptinchi")
    @JsonIgnore
    private Set<LopTinChiNgay> ds_loptinchingay = new HashSet<>();
    public LopTinChi(Long sl_sinhvien, String thu, Long tiet_bd, Long so_tiet, Set<Lop> ds_lop, MonHoc monhoc,
            HocKy hocky, Phong phong, GiangVien giangvien) {
        this.sl_sinhvien = sl_sinhvien;
        this.thu = thu;
        this.tiet_bd = tiet_bd;
        this.so_tiet = so_tiet;
        this.ds_lop = ds_lop;
        this.monhoc = monhoc;
        this.hocky = hocky;
        this.phong = phong;
        this.giangvien = giangvien;
    }
    
}
