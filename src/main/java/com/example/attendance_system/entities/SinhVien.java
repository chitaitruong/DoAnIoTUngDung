package com.example.attendance_system.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sinhvien")
@Getter
@Setter
@NoArgsConstructor
public class SinhVien extends User {
    // @OneToMany(mappedBy = "sinhvien")
    // private Set<DiemDanh> danhsach_diemdanh;
    public SinhVien(@Size(max = 20) String username, @Size(max = 120) String password, String hoten,
            @Size(max = 50) @Email String email, String phone, Date ngaysinh, String address, String otp, LocalDateTime exp_otp) {
        super(username, password, hoten, email, phone, ngaysinh, address, otp, exp_otp);
    }
    @ManyToMany(mappedBy = "ds_sinhvien")
    @JsonIgnore
    private Set<LopTinChi> ds_loptinchidk;
    @OneToMany(mappedBy = "sinhvien")
    @JsonIgnore
    private Set<DiemDanh> ds_diemdanh;
    @ManyToOne
    @JoinColumn(name="lop_id", nullable=true)
    private Lop lop;
}