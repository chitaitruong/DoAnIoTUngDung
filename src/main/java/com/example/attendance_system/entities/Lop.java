package com.example.attendance_system.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma_lop", nullable = false, unique = true)
    private String ma_lop;
    @Column(name = "khoahoc", nullable = false)
    private String khoahoc;
    @ManyToOne
    @JoinColumn(name = "nganh_id", nullable = false)
    private Nganh nganh;
    @ManyToOne
    @JoinColumn(name = "hedaotao_id", nullable = false)
    private HeDaoTao hedaotao;
    @ManyToOne
    @JoinColumn(name = "giangvien_id", nullable = false)
    private GiangVien giangvien;
    @OneToMany(mappedBy = "lop")
    private Set<SinhVien> ds_sinhvien;
    @ManyToMany(mappedBy = "ds_lop")
    private Set<LopTinChi> ds_loptinchi;
}
