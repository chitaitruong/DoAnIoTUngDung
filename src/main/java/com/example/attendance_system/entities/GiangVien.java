package com.example.attendance_system.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "giangvien")
@Getter
@Setter
@NoArgsConstructor
public class GiangVien extends User {
    // @OneToMany(mappedBy = "sinhvien")
    // private Set<DiemDanh> danhsach_diemdanh;
    @OneToMany(mappedBy = "giangvien")
    @JsonIgnore
    private Set<Lop> ds_lop;
    @OneToMany(mappedBy = "giangvien")
    @JsonIgnore
    private Set<LopTinChi> ds_loptinchi;
    @ManyToOne
    @JoinColumn(name="khoa_id", nullable=true)
    @JsonIgnore
    private Khoa khoa;
    public GiangVien(@Size(max = 20) String username, @Size(max = 120) String password, String hoten,
            @Size(max = 50) @Email String email, String phone, Date ngaysinh, String address) {
        super(username, password, hoten, email, phone, ngaysinh, address);
    }
    
}
