package com.example.attendance_system.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance_system.entities.DiemDanh;
import com.example.attendance_system.entities.DiemDanhKey;
import com.example.attendance_system.entities.IDiemDanh;
import com.example.attendance_system.entities.ILich;
import com.example.attendance_system.entities.ILichHoc;
import com.example.attendance_system.entities.Lop;
import com.example.attendance_system.entities.PhongLich;
import com.example.attendance_system.entities.PhongLichKey;
import com.example.attendance_system.entities.SinhVien;
import com.example.attendance_system.payload.request.DiemDanhRequest;
import com.example.attendance_system.payload.request.ListDiemDanhRequest;
import com.example.attendance_system.payload.request.ListLichRequest;
import com.example.attendance_system.payload.request.ListLopRequest;
import com.example.attendance_system.payload.request.UpdateDiemDanhRequest;
import com.example.attendance_system.payload.response.MessageResponse;
import com.example.attendance_system.repository.DiemDanhRepository;
import com.example.attendance_system.repository.LopRepository;
import com.example.attendance_system.repository.PhongLichRepository;
import com.example.attendance_system.repository.SinhVienRepository;
import com.example.attendance_system.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final SinhVienRepository sinhVienRepository;
    @Autowired
    private final LopRepository lopRepository;
    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final PhongLichRepository phongLichRepository;
    @Autowired
    private final DiemDanhRepository diemDanhRepository;

    @PostMapping("/sinhvien/save")
    public ResponseEntity<?> saveSinhVien(@RequestBody SinhVien sinhVien) {
        try {
            if (sinhVienRepository.existsById(sinhVien.getId())) {
                return ResponseEntity.ok(new MessageResponse("DUPLICATED_SINHVIEN_ID"));
            } else {
                sinhVienRepository.save(sinhVien);
                return ResponseEntity.ok(new MessageResponse("SinhVien saved successfully!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
        }
    }

    @GetMapping("/giangvien/{jwt}/lop")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<List<Lop>> getDanhSachLopByIdUser(@PathVariable("jwt") String jwt) {
        try {
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                List<Lop> danhsach_lop = new ArrayList<>();
                lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
                if (danhsach_lop.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(danhsach_lop, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/giangvien/{jwt}/lop/{lop_id}/lich")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<List<ILich>> getDanhSachLichByIdLop(@PathVariable("jwt") String jwt,
            @PathVariable("lop_id") Long lop_id) {
        try {
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                List<Lop> danhsach_lop = new ArrayList<>();
                List<ILich> danhsach_phonglich = new ArrayList<>();
                lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
                if (danhsach_lop.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    for (Lop i : danhsach_lop) {
                        if (i.getId() == lop_id) {
                            phongLichRepository.findByLopId(lop_id).forEach(danhsach_phonglich::add);
                            break;
                        }
                    }
                }
                if (danhsach_phonglich.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(danhsach_phonglich, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/giangvien/{jwt}/lop/{lop_id}/lich/{lich_id}/phong/{phong_id}/diemdanh")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<List<IDiemDanh>> getDiemDanhByPhongLichLop(
            @PathVariable("jwt") String jwt, @PathVariable("lop_id") Long lop_id,
            @PathVariable("phong_id") Long phong_id, @PathVariable("lich_id") Long lich_id) {
        try {
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                List<Lop> danhsach_lop = new ArrayList<>();
                List<IDiemDanh> danhsach_diemdanh = new ArrayList<>();
                lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
                if (danhsach_lop.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    for (Lop i : danhsach_lop) {
                        if (i.getId() == lop_id) {
                            diemDanhRepository.findDiemDanhByLichPhong(lop_id, phong_id, lich_id)
                                    .forEach(danhsach_diemdanh::add);
                            break;
                        }
                    }
                }
                if (danhsach_diemdanh.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(danhsach_diemdanh, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sinhvien/diemdanh")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveDiemDanh(@Valid @RequestBody DiemDanhRequest diemDanhRequest) {
        try {
            Optional<SinhVien> sinhVien = sinhVienRepository.findById(diemDanhRequest.getId());
            if (sinhVien.isPresent()) {
                Boolean ca;
                Long trangThai = 1L;
                LocalTime bdca1 = LocalTime.of(7, 0);
                LocalTime ktca1 = LocalTime.of(11, 0);
                LocalTime bdca2 = LocalTime.of(13, 0);
                LocalTime ktca2 = LocalTime.of(17, 0);
                int bdca1s = bdca1.getHour() * 3600 + bdca1.getMinute() * 60 + bdca1.getSecond();
                int ktca1s = ktca1.getHour() * 3600 + ktca1.getMinute() * 60 + ktca1.getSecond();
                int bdca2s = bdca2.getHour() * 3600 + bdca2.getMinute() * 60 + bdca2.getSecond();
                int ktca2s = ktca2.getHour() * 3600 + ktca2.getMinute() * 60 + ktca2.getSecond();
                LocalTime gioQuetVanTay = LocalTime.now();
                int gioQuetVanTays = gioQuetVanTay.getHour() * 3600 + gioQuetVanTay.getMinute() * 60
                        + gioQuetVanTay.getSecond();
                if (gioQuetVanTays >= bdca1s && gioQuetVanTays <= ktca1s) {
                    ca = false;
                    if (gioQuetVanTays > (bdca1s + 1 * 3600)) {
                        trangThai = 2L;
                    }
                } else if (gioQuetVanTays >= bdca2s && gioQuetVanTays <= ktca2s) {
                    ca = true;
                    if (gioQuetVanTays > (bdca2s + 1 * 3600)) {
                        trangThai = 2L;
                    }
                } /* else {
                    return ResponseEntity.badRequest().body(new MessageResponse("ATTENDAN TIMEOUT"));
                 }*/
                  
                ILichHoc lich = phongLichRepository.findLichHocBySinhVienIdAndNgay(sinhVien.get().getId(),
                        /* LocalDate.now().toString() */ "2023-01-02", false);
                if (lich != null) {
                    DiemDanh _diemDanh = new DiemDanh();
                    PhongLichKey phongLichKey = new PhongLichKey(lich.getPhong_id(), lich.getLich_id());
                    System.out.println(phongLichKey.getLich_id());
                    System.out.println(phongLichKey.getPhong_id());
                    DiemDanhKey diemDanhKey = new DiemDanhKey(sinhVien.get().getId(), phongLichKey);
                    System.out.println(diemDanhKey.getPhonglich_id().getLich_id());
                    System.out.println(diemDanhKey.getPhonglich_id().getPhong_id());
                    Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
                    String ho_ten = sinhVien.get().getHo_ten();
                    String ten = ho_ten.substring(ho_ten.lastIndexOf(" ")+1);
                    if (diemDanh.isPresent()) {
                        return ResponseEntity.badRequest().body(
                            new MessageResponse(ten + "(" + String.valueOf(sinhVien.get().getId()) + ")" + "'S PRESENT"));
                    }
                    _diemDanh.setId(diemDanhKey);
                    System.out.println("lich: " + diemDanhKey.getPhonglich_id().getLich_id());
                    _diemDanh.setSinhvien(sinhVien.get());
                    _diemDanh.setPhonglich(phongLichRepository.findById(phongLichKey).get());
                    System.out.println("lich: " + phongLichRepository.findById(phongLichKey).get().getLich().getId());
                    _diemDanh.setTrang_thai(trangThai);
                    _diemDanh.setGhi_chu(null);
                    _diemDanh.setThoi_gian_quet_van_tay(LocalDateTime.now());
                    _diemDanh.setThoi_gian_cap_nhat(null);
                    diemDanhRepository.save(_diemDanh);
                    return ResponseEntity.ok(
                        new MessageResponse("HI " + ten + "(" + String.valueOf(sinhVien.get().getId()) + ")"));
                } else {
                    return ResponseEntity.badRequest().body(new MessageResponse("NO SCHEDULE"));
                }
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("STUDENT NO EXIST"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("SERVER ERROR"));
        }
    }

    @PostMapping("/giangvien/{jwt}/diemdanh/update")
    public ResponseEntity<?> updateDiemDanh(@PathVariable("jwt") String jwt, @RequestBody UpdateDiemDanhRequest updateDiemDanhRequest) {
        try {
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                List<Lop> danhsach_lop = new ArrayList<>();
                lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
                if (danhsach_lop.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    for (Lop i : danhsach_lop) {
                        if (i.getId() == updateDiemDanhRequest.getLop_id()) {
                            Optional<PhongLich> lich = phongLichRepository.findById(new PhongLichKey(
                                    updateDiemDanhRequest.getPhong_id(), updateDiemDanhRequest.getLich_id()));
                            Optional<SinhVien> sinhVien = sinhVienRepository
                                    .findById(updateDiemDanhRequest.getSinhvien_id());
                            if (lich.isPresent() && sinhVien.isPresent()) {
                                if (lich.get().getLop().getId() == i.getId()) {
                                    List<Lop> lops = lopRepository.findLopBySinhviensId(sinhVien.get().getId());
                                    for (Lop tam : lops) {
                                        if (tam.getId() == updateDiemDanhRequest.getLop_id()) {
                                            DiemDanhKey diemDanhKey = new DiemDanhKey(
                                                    updateDiemDanhRequest.getSinhvien_id(), lich.get().getId());
                                            Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
                                            if (diemDanh.isPresent()) {
                                                DiemDanh _diemDanh = diemDanh.get();
                                                _diemDanh.setGhi_chu(updateDiemDanhRequest.getGhi_chu());
                                                _diemDanh.setThoi_gian_cap_nhat(LocalDateTime.now());
                                                _diemDanh.setTrang_thai(updateDiemDanhRequest.getTrang_thai());
                                                diemDanhRepository.save(_diemDanh);
                                                return ResponseEntity
                                                        .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
                                            } else {
                                                DiemDanh _diemDanh = new DiemDanh(diemDanhKey, sinhVien.get(),
                                                        lich.get(), updateDiemDanhRequest.getTrang_thai(),
                                                        updateDiemDanhRequest.getGhi_chu(), null, LocalDateTime.now());
                                                diemDanhRepository.save(_diemDanh);
                                                return ResponseEntity
                                                        .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
                                            }
                                        }
                                    }
                                    return ResponseEntity.badRequest()
                                            .body(new MessageResponse("Sinh vien khong hoc lop nay!"));
                                } else {
                                    return ResponseEntity.badRequest()
                                            .body(new MessageResponse("Lich khong thuoc lop nay!"));
                                }
                            } else {
                                return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Lich hoac sinh vien khong ton tai!"));
                            }
                        }
                    }
                    return ResponseEntity.badRequest()
                            .body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
                }
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(lopRepository.findLopBySinhviensId(1L));
    }
    @GetMapping("/all")
    public String getAll() {
        return "All";
    }
    @GetMapping("user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getUser() {
        return "User";
    }
}
