package com.example.attendance_system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance_system.entities.ERole;
import com.example.attendance_system.entities.GiangVien;
import com.example.attendance_system.entities.RefreshToken;
import com.example.attendance_system.entities.Role;
import com.example.attendance_system.entities.SinhVien;
import com.example.attendance_system.entities.User;
import com.example.attendance_system.exception.TokenRefreshException;
import com.example.attendance_system.payload.request.SignInRequest;
import com.example.attendance_system.payload.request.SignupRequest;
import com.example.attendance_system.payload.request.TokenRefreshRequest;
import com.example.attendance_system.payload.response.JwtResponse;
import com.example.attendance_system.payload.response.MessageResponse;
import com.example.attendance_system.payload.response.TokenRefreshResponse;
import com.example.attendance_system.repository.GiangVienRepository;
import com.example.attendance_system.repository.RoleRepository;
import com.example.attendance_system.repository.SinhVienRepository;
import com.example.attendance_system.repository.UserRepository;
import com.example.attendance_system.security.jwt.JwtUtils;
import com.example.attendance_system.security.services.RefreshTokenService;
import com.example.attendance_system.security.services.UserDetailsImpl;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  GiangVienRepository giangVienRepository;

  @Autowired
  SinhVienRepository sinhVienRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    if (userRepository.existsByPhone(signUpRequest.getPhone())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Phone is already in use!"));
    }

    User user;
    // Create new user's account
    Date ngaysinh = null;
    try {
      ngaysinh = new SimpleDateFormat("dd/MM/yyyy").parse(signUpRequest.getNgaysinh());
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (signUpRequest.getIsGiangVien()) {
      user = new GiangVien(signUpRequest.getUsername(),
      encoder.encode(signUpRequest.getPassword()), signUpRequest.getHoten(), signUpRequest.getEmail(), signUpRequest.getPhone(), ngaysinh, signUpRequest.getAddress());
    } else {
      user = new SinhVien(signUpRequest.getUsername(), 
      encoder.encode(signUpRequest.getPassword()), signUpRequest.getHoten(), signUpRequest.getEmail(), signUpRequest.getPhone(), ngaysinh, signUpRequest.getAddress());
    }
    
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "teacher":
          Role modRole = roleRepository.findByName(ERole.ROLE_TEACHER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }
    user.setRoles(roles);
    if (signUpRequest.getIsGiangVien()) {
      giangVienRepository.save((GiangVien)user);
    } else {
      sinhVienRepository.save((SinhVien)user);
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
  
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();
    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
