package com.example.attendance_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.Phong;

public interface PhongRepository extends JpaRepository<Phong, Long> {
}
