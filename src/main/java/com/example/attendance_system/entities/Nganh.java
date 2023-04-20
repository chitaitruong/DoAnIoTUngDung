package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nganh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nganh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ma_nganh", nullable = false, unique = true)
    private String ma_nganh;
    @Column(name = "ten_nganh", nullable = false, unique = true)
    private String ten_nganh;
    @Column(name = "so_nam", nullable = false)
    private Double so_nam;
    @OneToMany(mappedBy = "nganh")
    private Set<Lop> ds_lop;
    @ManyToOne
    @JoinColumn(name = "khoa_id", nullable = false)
    private Khoa khoa;
}
