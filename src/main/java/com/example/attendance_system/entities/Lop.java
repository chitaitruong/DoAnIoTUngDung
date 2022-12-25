package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lop", uniqueConstraints = {
        @UniqueConstraint(name = "UK_tenlop_nam", columnNames = { "ten_lop", "nam" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ten_lop", nullable = false)
    private String ten_lop;
    @Column(name = "nam", nullable = false)
    private Long nam;
    @Column(name = "so_luong_sinhvien", nullable = false)
    private Long so_luong_sinhvien;
    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    private Set<PhongLich> danhsach_phonglich;
    @ManyToMany
    @JoinTable(name = "lop_sinhvien", joinColumns = @JoinColumn(name = "lop_id"), inverseJoinColumns = @JoinColumn(name = "sinhvien_id"))
    @JsonIgnore
    private Set<SinhVien> sinhviens;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;
}
