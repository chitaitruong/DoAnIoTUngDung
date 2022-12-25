package com.example.attendance_system.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PhongLichKey implements Serializable {
    @Column(name = "phong_id")
    private Long phong_id;
    @Column(name = "lich_id")
    private Long lich_id;
}
