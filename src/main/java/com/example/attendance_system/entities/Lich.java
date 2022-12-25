package com.example.attendance_system.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lich", uniqueConstraints = { @UniqueConstraint(name = "UK_ngay_ca", columnNames = { "ngay", "ca" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ngay", nullable = false)
    private LocalDate ngay;
    @Column(name = "ca", nullable = false)
    private Boolean ca;
    @OneToMany(mappedBy = "lich")
    private Set<PhongLich> danhsach_phong_lich;

    public Lich(LocalDate ngay, Boolean ca, Set<PhongLich> danhsach_phong_lich) {
        this.ngay = ngay;
        this.ca = ca;
        this.danhsach_phong_lich = danhsach_phong_lich;
    }
}
