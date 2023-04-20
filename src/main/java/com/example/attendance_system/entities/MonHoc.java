package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = " monhoc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma", nullable = false, unique = true)
    private String ma;
    @Column(name = "monhoc", nullable = false, unique = true)
    private String monhoc;
    @Column(name = "stc", nullable = false)
    private int stc;
    @Column(name = "stc_hp", nullable = false)
    private int stc_hp;
    @Column(name = "so_buoilt", nullable = false)
    private int so_buoilt;
    @Column(name = "so_buoith", nullable = false)
    private int so_buoith;
    @OneToMany(mappedBy = "monhoc")
    @JsonIgnore
    private Set<LopTinChi> ds_loptinchi;
    public MonHoc(String ma_monhoc, String monhoc, int stc, int stc_hp, int so_buoilt, int so_buoith) {
        this.ma = ma_monhoc;
        this.monhoc = monhoc;
        this.stc = stc;
        this.stc_hp = stc_hp;
        this.so_buoilt = so_buoilt;
        this.so_buoith = so_buoith;
    }
    
}
