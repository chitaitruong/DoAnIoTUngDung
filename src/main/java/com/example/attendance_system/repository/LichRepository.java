package com.example.attendance_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance_system.entities.Lich;

public interface LichRepository extends JpaRepository<Lich, Long> {
    
}
