package com.example.attendance_system.entities;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ngay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ngay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ngay", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ngay;
    // @ManyToOne
    // @JoinColumn(name="loptinchi_id", nullable=false)
    // private LopTinChi loptinchi;
    // @OneToMany(mappedBy = "ngay")
    // @JsonIgnore
    // private Set<DiemDanh> ds_diemdanh;
    @OneToMany(mappedBy = "ngay")
    @JsonIgnore
    private Set<LopTinChiNgay> ds_loptinchingay = new HashSet<>();
    public Ngay(Date ngay) {
        this.ngay = ngay;
    }
}
