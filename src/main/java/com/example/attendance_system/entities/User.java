package com.example.attendance_system.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(name = "username", unique = true, nullable = false)
	@Size(max = 20)
	protected String username;
	@Column(name = "password", nullable = false)
	@Size(max = 120)
	protected String password;
	@Column(name = "hoten", nullable = false)
	protected String hoten;
	@Column(name = "email", unique = true, nullable = false)
	@Size(max = 50)
	@Email
	protected String email;
	@Column(name = "phone", unique = true, nullable = false)
	protected String phone;
	@Column(name = "ngaysinh", nullable = false)
	@Temporal(TemporalType.DATE)
	protected Date ngaysinh;
	@Column(name = "address", nullable = false)
	protected String address;
	@Column(name = "isactive", nullable = false)
	protected Boolean isactive = false;
	@Column(name = "otp", nullable = false)
	protected String otp;
	@Column(name = "exp_otp", nullable = false)
	protected LocalDateTime exp_otp;
	// @ManyToMany
    // @JoinTable(name = "lop_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "lop_id"))
    // private Set<Lop> ds_lop;
    // @ManyToMany
    // @JoinTable(name = "loptinchi_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "loptinchi_id"))
    // private Set<LopTinChi> ds_loptinchi;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	public User(@Size(max = 20) String username, @Size(max = 120) String password, String hoten,
			@Size(max = 50) @Email String email, String phone, Date ngaysinh, String address, String otp, LocalDateTime exp_otp) {
		this.username = username;
		this.password = password;
		this.hoten = hoten;
		this.email = email;
		this.phone = phone;
		this.ngaysinh = ngaysinh;
		this.address = address;
		this.otp = otp;
		this.exp_otp = exp_otp;
	}
	
}
