package com.example.attendance_system.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loptinchingay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopTinChiNgay {
    @EmbeddedId
    private LopTinChiNgayId id;
    @ManyToOne
    @MapsId("loptinchi_id")
    @JoinColumn(name = "loptinchi_id")
    private LopTinChi loptinchi;
    @ManyToOne
    @MapsId("ngay_id")
    @JoinColumn(name = "ngay_id")
    private Ngay ngay;
    @OneToMany(mappedBy = "loptinchingay")
    @JsonIgnore
    private Set<DiemDanh> ds_diemdanh = new HashSet<>();
    public LopTinChiNgay(LopTinChiNgayId id, LopTinChi loptinchi, Ngay ngay) {
        this.id = id;
        this.loptinchi = loptinchi;
        this.ngay = ngay;
    }
    
}
