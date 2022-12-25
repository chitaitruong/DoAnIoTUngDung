package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sinhvien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ho_ten", nullable = false)
    private String ho_ten;
    @ManyToMany
    @JoinTable(name = "lop_sinhvien", joinColumns = @JoinColumn(name = "sinhvien_id"), inverseJoinColumns = @JoinColumn(name = "lop_id"))
    private Set<Lop> lops;
    @OneToMany(mappedBy = "sinhvien")
    private Set<DiemDanh> danhsach_diemdanh;
}