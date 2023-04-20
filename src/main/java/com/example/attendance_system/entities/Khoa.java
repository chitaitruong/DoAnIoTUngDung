package com.example.attendance_system.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "khoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ten_khoa", nullable = false, unique = true)
    private String ten_khoa;
    @OneToMany(mappedBy = "khoa")
    private Set<Nganh> ds_nganh;
    
    
}