package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hocky", uniqueConstraints = { @UniqueConstraint(name = "UK_namhoc_hocky", columnNames = { "namhoc", "hocky" })})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HocKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "hocky", nullable = false)
    private Long hocky;
    @Column(name = "namhoc", nullable = false)
    private String namhoc;
    @OneToMany(mappedBy = "hocky")
    @JsonIgnore
    private Set<LopTinChi> ds_loptinchi;
}
