package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phong_lich")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PhongLich {
    @EmbeddedId
    private PhongLichKey id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("phong_id")
    @JoinColumn(name = "phong_id")
    private Phong phong;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("lich_id")
    @JoinColumn(name = "lich_id")
    private Lich lich;
    @ManyToOne
    @JoinColumn(name = "lop_id", nullable = false)
    @JsonIgnore
    private Lop lop;
    @OneToMany(mappedBy = "phonglich")
    @JsonIgnore
    private Set<DiemDanh> danhsach_diemdanh;
}
