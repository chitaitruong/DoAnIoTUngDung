package com.example.attendance_system.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance_system.entities.DiemDanh;
import com.example.attendance_system.entities.DiemDanhId;
import com.example.attendance_system.entities.GiangVien;
import com.example.attendance_system.entities.HocKy;
import com.example.attendance_system.entities.IDiemDanh;
import com.example.attendance_system.entities.IDiemDanhSinhVien;
import com.example.attendance_system.entities.ILich;
import com.example.attendance_system.entities.ILichHoc;
import com.example.attendance_system.entities.Lop;
import com.example.attendance_system.entities.LopTinChi;
import com.example.attendance_system.entities.LopTinChiNgay;
import com.example.attendance_system.entities.LopTinChiNgayId;
import com.example.attendance_system.entities.MonHoc;
import com.example.attendance_system.entities.Ngay;
import com.example.attendance_system.entities.Phong;
import com.example.attendance_system.entities.SinhVien;
import com.example.attendance_system.entities.User;
import com.example.attendance_system.payload.request.DiemDanhRequest;
import com.example.attendance_system.payload.request.ListDiemDanhRequest;
import com.example.attendance_system.payload.request.ListLichRequest;
import com.example.attendance_system.payload.request.ListLopRequest;
import com.example.attendance_system.payload.request.LopTinChiData;
import com.example.attendance_system.payload.request.TaoLopTinChiRequest;
import com.example.attendance_system.payload.request.UpdateDiemDanhRequest;
import com.example.attendance_system.payload.response.MessageResponse;
import com.example.attendance_system.repository.DiemDanhRepository;
import com.example.attendance_system.repository.GiangVienRepository;
import com.example.attendance_system.repository.HocKyRepository;
import com.example.attendance_system.repository.LopRepository;
import com.example.attendance_system.repository.LopTinChiNgayRepository;
import com.example.attendance_system.repository.LopTinChiRepository;
import com.example.attendance_system.repository.MonHocRepository;
import com.example.attendance_system.repository.NgayRepository;
import com.example.attendance_system.repository.PhongRepository;
import com.example.attendance_system.repository.SinhVienRepository;
import com.example.attendance_system.repository.UserRepository;
import com.example.attendance_system.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    // @Autowired
    // private final SinhVienRepository sinhVienRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SinhVienRepository sinhVienRepository;
    @Autowired
    private final NgayRepository ngayRepository;
    @Autowired
    private final LopTinChiRepository lopTinChiRepository;
    @Autowired
    private final LopTinChiNgayRepository lopTinChiNgayRepository;
    @Autowired
    private final LopRepository lopRepository;
    @Autowired
    private final MonHocRepository monHocRepository;
    @Autowired
    private final GiangVienRepository giangVienRepository;
    @Autowired
    private final HocKyRepository hocKyRepository;
    @Autowired
    private final PhongRepository phongRepository;
    @Autowired
    private final JwtUtils jwtUtils;
    // @Autowired
    // private final PhongLichRepository phongLichRepository;
    @Autowired
    private final DiemDanhRepository diemDanhRepository;
    //Tu Nguyen Quoc Huy
    @PostMapping("/admin/monhoc/save")
    public ResponseEntity<?> saveMonHoc(@RequestBody MonHoc monHoc) {
        try {
            if (monHocRepository.existsByMonhoc(monHoc.getMonhoc())) {
                return ResponseEntity.ok(new MessageResponse("Trung ten mon hoc: " + monHoc.getMonhoc()));
            } else if (monHocRepository.existsByMa(monHoc.getMa())) {
                return ResponseEntity.ok(new MessageResponse("Trung ma mon hoc: " + monHoc.getMa()));
            }
            MonHoc _monHoc = new MonHoc(monHoc.getMa(), monHoc.getMonhoc(), monHoc.getStc(), monHoc.getStc_hp(), monHoc.getSo_buoilt(), monHoc.getSo_buoith());
            return new ResponseEntity<>(monHocRepository.save(_monHoc),HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
        }
    }
    @PutMapping("/admin/monhoc/{id}")
    public ResponseEntity<?> updateMonHoc(@PathVariable("id") long id, @RequestBody MonHoc monHoc) {
        if (!monHocRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(new MessageResponse("Khong tim thay mon hoc co id: " + id));
        }
        MonHoc _monHoc = monHocRepository.findById(id).get();
        if (monHocRepository.existsByMonhoc(monHoc.getMonhoc()) && !monHoc.getMonhoc().equals(_monHoc.getMonhoc())) {
            return ResponseEntity.ok(new MessageResponse("Trung ten mon hoc: " + monHoc.getMonhoc()));
        } else if (monHocRepository.existsByMa(monHoc.getMa()) && !monHoc.getMa().equals(_monHoc.getMa())) {
            return ResponseEntity.ok(new MessageResponse("Trung ma mon hoc: " + monHoc.getMa()));
        }
        _monHoc.setMa(monHoc.getMa());
        _monHoc.setMonhoc(monHoc.getMonhoc());
        _monHoc.setStc(monHoc.getStc());
        _monHoc.setStc_hp(monHoc.getStc_hp());
        _monHoc.setSo_buoilt(monHoc.getSo_buoilt());
        _monHoc.setSo_buoith(monHoc.getSo_buoith());
        return new ResponseEntity<>(monHocRepository.save(_monHoc), HttpStatus.OK);
    }
    @DeleteMapping("/admin/monhoc/{id}")
    public ResponseEntity<HttpStatus> deleteMonHoc(@PathVariable("id") long id) {
        monHocRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/admin/monhoc")
    public ResponseEntity<List<MonHoc>> findByPublished() {
        List<MonHoc> ds_monhoc = monHocRepository.findAll();

        if (ds_monhoc.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(ds_monhoc, HttpStatus.OK);
    }
    //Nguyen Thanh Bang
    @PostMapping("/admin/loptinchi/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveLopTinChi(@RequestBody TaoLopTinChiRequest taoLopTinChiRequest) {
        try {
            HocKy hocky = hocKyRepository.findById(taoLopTinChiRequest.getHocky_id()).get();
            GiangVien giangvien = giangVienRepository.findById(taoLopTinChiRequest.getGiangvien_id()).get();
            Phong phong = phongRepository.findById(taoLopTinChiRequest.getPhong_id()).get();
            MonHoc monhoc = monHocRepository.findById(taoLopTinChiRequest.getMonhoc_id()).get();
            Long sl_sv = taoLopTinChiRequest.getSl_sinhvien();
            Long so_tiet = taoLopTinChiRequest.getSo_tiet();
            Long tiet_bd = taoLopTinChiRequest.getTiet_bd();
            String thu = taoLopTinChiRequest.getThu();
            Set<Lop> ds_lop = new HashSet<>(lopRepository.findAllById(taoLopTinChiRequest.getDs_idlop()));
            Set<String> ds_ngay = taoLopTinChiRequest.getDs_ngay();
            LopTinChi loptinchi = lopTinChiRepository.save(new LopTinChi(sl_sv,thu,tiet_bd,so_tiet,ds_lop,monhoc,hocky,phong,giangvien));
            Ngay _ngay;
            for (String tam :ds_ngay) {
                Date ngay = new SimpleDateFormat("dd/MM/yyyy").parse(tam);
                Optional<Ngay> _ngaytam = ngayRepository.findByNgay(ngay);
                
                if (!_ngaytam.isPresent()) {
                    _ngay = ngayRepository.save(new Ngay(ngay));
                } else {
                    _ngay = _ngaytam.get();
                }
                LopTinChiNgay lopTinChiNgay = new LopTinChiNgay(new LopTinChiNgayId(loptinchi.getId(), _ngay.getId()), loptinchi, _ngay);
                lopTinChiNgayRepository.save(lopTinChiNgay);
                _ngay.getDs_loptinchingay().add(lopTinChiNgay);
                ngayRepository.save(_ngay);
                loptinchi.getDs_loptinchingay().add(lopTinChiNgay);
            }
            lopTinChiRepository.save(loptinchi);
            return ResponseEntity.ok(new MessageResponse("Tao lop tin chi thanh cong"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
        }
    }
    @GetMapping("/giangvien/loptinchi")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<LopTinChi>> getDanhSachLopByIdGiangVien(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        try {
            jwt = jwt.split(" ")[1];
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                GiangVien gv = (GiangVien)userRepository.findByUsername(username).get();
                List<LopTinChi> ds_loptinchi = new ArrayList<>();
                lopTinChiRepository.findByGiangvienId(gv.getId()).forEach(ds_loptinchi::add);
                System.out.println("SIZE OF DSLTC:" + ds_loptinchi.size());
                if (ds_loptinchi.isEmpty()) {
                    System.out.println("SIZE OF DSLTC:" + ds_loptinchi.size());
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(ds_loptinchi, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/giangvien/loptinchi/{id}/lich")
    public ResponseEntity<List<Ngay>> getLichByLopTinChiId(@PathVariable("id") long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        try {
            jwt = jwt.split(" ")[1];
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                GiangVien gv = (GiangVien)userRepository.findByUsername(username).get();
                for (LopTinChi ltc : lopTinChiRepository.findByGiangvienId(gv.getId())) {
                    if (ltc.getId()==id) {
                        List<Ngay> ds_ngay = new ArrayList<>();
                        for (LopTinChiNgay ltcn: ltc.getDs_loptinchingay()) {
                            ds_ngay.add(ngayRepository.findById(ltcn.getId().getNgay_id()).get());
                        }
                        Collections.sort(ds_ngay, new com.example.attendance_system.helper.sortCompare());
                        return new ResponseEntity<>(ds_ngay, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/giangvien/loptinchi/{id}/lich/{ngayid}/diemdanh")
    public ResponseEntity<?> getLichByLopTinChiId(@PathVariable("id") Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @PathVariable("ngayid") Long ngayid) {
        try {
            jwt = jwt.split(" ")[1];
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                GiangVien gv = (GiangVien)userRepository.findByUsername(username).get();
                for (LopTinChi ltc : lopTinChiRepository.findByGiangvienId(gv.getId())) {
                    if (ltc.getId()==id) {
                        if (lopTinChiNgayRepository.existsById(new LopTinChiNgayId(id, ngayid))){
                            List<IDiemDanh> ds_diemdanh = diemDanhRepository.findDiemDanhByNgay(id, ngayid);
                            return new ResponseEntity<>(ds_diemdanh,HttpStatus.OK);
                        }
                    }
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/diemdanh")
    public ResponseEntity<?> getLichByLopTinChiId(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody SinhVien sinhVien) {
        try {
            Optional<SinhVien> sv = sinhVienRepository.findById(sinhVien.getId());
            if (!sv.isPresent()) {
                return ResponseEntity.ok(new MessageResponse("Id khong ton tai trong he thong"));
            }
            SinhVien _sinhVien = sv.get();
            Long trangThai = 1L;
            Long gio = 100L;
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
                gio = 1L;
                if (gioQuetVanTays > (bdca1s + 1 * 3600)) {
                    trangThai = 2L;
                }
            } else if (gioQuetVanTays >= bdca2s && gioQuetVanTays <= ktca2s) {
                gio = 7L;
                if (gioQuetVanTays > (bdca2s + 1 * 3600)) {
                    trangThai = 2L;
                }
            }
            Ngay _ngay = new Ngay();//new SimpleDateFormat("dd/MM/yyyy").parse("12/03/2023")
            if (ngayRepository.findByNgay(new SimpleDateFormat("dd/MM/yyyy").parse("12/03/2023")).isPresent()) {
                _ngay = ngayRepository.findByNgay(new SimpleDateFormat("dd/MM/yyyy").parse("12/03/2023")).get();
            } else {
                return ResponseEntity.ok(new MessageResponse("ATTENDANCE TIMEOUT"));
            }
            Long ngayId = _ngay.getId();
            IDiemDanhSinhVien tam = diemDanhRepository.findDiemDanh(ngayId, _sinhVien.getId(), 1L);
            if (tam == null ) {
                return ResponseEntity.ok(new MessageResponse("Ban khong co lich hoc luc nay!"));
            }
            DiemDanh diemDanh = new DiemDanh();
            LopTinChiNgayId lopTinChiNgayId = new LopTinChiNgayId(tam.getId(),ngayId);
            DiemDanhId diemDanhId = new DiemDanhId(lopTinChiNgayId, _sinhVien.getId());
            if (diemDanhRepository.existsById(diemDanhId)) 
                return ResponseEntity.ok(new MessageResponse("Ban da diem danh roi!"));
            diemDanh.setId(diemDanhId);
            LopTinChiNgay lopTinChiNgay = lopTinChiNgayRepository.findById(lopTinChiNgayId).get();
            diemDanh.setLoptinchingay(lopTinChiNgay);
            diemDanh.setSinhvien(_sinhVien);
            diemDanh.setTrang_thai(trangThai);
            diemDanh.setGhi_chu(null);
            diemDanh.setThoi_gian_cap_nhat(LocalDateTime.now());
            diemDanh.setThoi_gian_quet_van_tay(LocalDateTime.now());
            DiemDanh dd = diemDanhRepository.save(diemDanh);
            return new ResponseEntity<>(dd, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/giangvien/diemdanh")
    public ResponseEntity<?> updateDiemDanh(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt, @RequestBody UpdateDiemDanhRequest updateDiemDanhRequest) {
        try {
            Optional<DiemDanh> dd = diemDanhRepository.findById(new DiemDanhId(new LopTinChiNgayId(updateDiemDanhRequest.getLoptinchi_id(), updateDiemDanhRequest.getNgay_id()), updateDiemDanhRequest.getSinhvien_id()));
            if (!dd.isPresent())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            DiemDanh _diemDanh = dd.get();
            _diemDanh.setGhi_chu(updateDiemDanhRequest.getGhi_chu());
            _diemDanh.setThoi_gian_cap_nhat(LocalDateTime.now());
            _diemDanh.setTrang_thai(updateDiemDanhRequest.getTrang_thai());
            return new ResponseEntity<>(diemDanhRepository.save(_diemDanh), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // @PostMapping("/sinhvien/save")
    // public ResponseEntity<?> saveSinhVien(@RequestBody SinhVien sinhVien) {
    //     try {
    //         if (sinhVienRepository.existsById(sinhVien.getId())) {
    //             return ResponseEntity.ok(new MessageResponse("DUPLICATED_SINHVIEN_ID"));
    //         } else {
    //             sinhVienRepository.save(sinhVien);
    //             return ResponseEntity.ok(new MessageResponse("SinhVien saved successfully!"));
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
    //     }
    // }

    // @GetMapping("/giangvien/{jwt}/lop")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<Lop>> getDanhSachLopByIdUser(@PathVariable("jwt") String jwt) {
    //     try {
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(danhsach_lop, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @GetMapping("/giangvien/{jwt}/lop/{lop_id}/lich")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<ILich>> getDanhSachLichByIdLop(@PathVariable("jwt") String jwt,
    //         @PathVariable("lop_id") Long lop_id) {
    //     try {
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             List<ILich> danhsach_phonglich = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == lop_id) {
    //                         phongLichRepository.findByLopId(lop_id).forEach(danhsach_phonglich::add);
    //                         break;
    //                     }
    //                 }
    //             }
    //             if (danhsach_phonglich.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(danhsach_phonglich, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @GetMapping("/giangvien/{jwt}/lop/{lop_id}/lich/{lich_id}/phong/{phong_id}/diemdanh")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<IDiemDanh>> getDiemDanhByPhongLichLop(
    //         @PathVariable("jwt") String jwt, @PathVariable("lop_id") Long lop_id,
    //         @PathVariable("phong_id") Long phong_id, @PathVariable("lich_id") Long lich_id) {
    //     try {
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             List<IDiemDanh> danhsach_diemdanh = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == lop_id) {
    //                         diemDanhRepository.findDiemDanhByLichPhong(lop_id, phong_id, lich_id)
    //                                 .forEach(danhsach_diemdanh::add);
    //                         break;
    //                     }
    //                 }
    //             }
    //             if (danhsach_diemdanh.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(danhsach_diemdanh, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @PostMapping("/sinhvien/diemdanh")
    // // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<?> saveDiemDanh(@Valid @RequestBody DiemDanhRequest diemDanhRequest) {
    //     try {
    //         Optional<SinhVien> sinhVien = sinhVienRepository.findById(diemDanhRequest.getId());
    //         if (sinhVien.isPresent()) {
    //             Boolean ca;
    //             Long trangThai = 1L;
    //             LocalTime bdca1 = LocalTime.of(7, 0);
    //             LocalTime ktca1 = LocalTime.of(11, 0);
    //             LocalTime bdca2 = LocalTime.of(13, 0);
    //             LocalTime ktca2 = LocalTime.of(17, 0);
    //             int bdca1s = bdca1.getHour() * 3600 + bdca1.getMinute() * 60 + bdca1.getSecond();
    //             int ktca1s = ktca1.getHour() * 3600 + ktca1.getMinute() * 60 + ktca1.getSecond();
    //             int bdca2s = bdca2.getHour() * 3600 + bdca2.getMinute() * 60 + bdca2.getSecond();
    //             int ktca2s = ktca2.getHour() * 3600 + ktca2.getMinute() * 60 + ktca2.getSecond();
    //             LocalTime gioQuetVanTay = LocalTime.now();
    //             int gioQuetVanTays = gioQuetVanTay.getHour() * 3600 + gioQuetVanTay.getMinute() * 60
    //                     + gioQuetVanTay.getSecond();
    //             if (gioQuetVanTays >= bdca1s && gioQuetVanTays <= ktca1s) {
    //                 ca = false;
    //                 if (gioQuetVanTays > (bdca1s + 1 * 3600)) {
    //                     trangThai = 2L;
    //                 }
    //             } else if (gioQuetVanTays >= bdca2s && gioQuetVanTays <= ktca2s) {
    //                 ca = true;
    //                 if (gioQuetVanTays > (bdca2s + 1 * 3600)) {
    //                     trangThai = 2L;
    //                 }
    //             } /* else {
    //                 return ResponseEntity.badRequest().body(new MessageResponse("ATTENDAN TIMEOUT"));
    //              }*/
                  
    //             ILichHoc lich = phongLichRepository.findLichHocBySinhVienIdAndNgay(sinhVien.get().getId(),
    //                     /* LocalDate.now().toString() */ "2023-01-02", false);
    //             if (lich != null) {
    //                 DiemDanh _diemDanh = new DiemDanh();
    //                 PhongLichKey phongLichKey = new PhongLichKey(lich.getPhong_id(), lich.getLich_id());
    //                 System.out.println(phongLichKey.getLich_id());
    //                 System.out.println(phongLichKey.getPhong_id());
    //                 DiemDanhKey diemDanhKey = new DiemDanhKey(sinhVien.get().getId(), phongLichKey);
    //                 System.out.println(diemDanhKey.getPhonglich_id().getLich_id());
    //                 System.out.println(diemDanhKey.getPhonglich_id().getPhong_id());
    //                 Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
    //                 String ho_ten = sinhVien.get().getHo_ten();
    //                 String ten = ho_ten.substring(ho_ten.lastIndexOf(" ")+1);
    //                 if (diemDanh.isPresent()) {
    //                     return ResponseEntity.badRequest().body(
    //                         new MessageResponse(ten + "(" + String.valueOf(sinhVien.get().getId()) + ")" + "'S PRESENT"));
    //                 }
    //                 _diemDanh.setId(diemDanhKey);
    //                 System.out.println("lich: " + diemDanhKey.getPhonglich_id().getLich_id());
    //                 _diemDanh.setSinhvien(sinhVien.get());
    //                 _diemDanh.setPhonglich(phongLichRepository.findById(phongLichKey).get());
    //                 System.out.println("lich: " + phongLichRepository.findById(phongLichKey).get().getLich().getId());
    //                 _diemDanh.setTrang_thai(trangThai);
    //                 _diemDanh.setGhi_chu(null);
    //                 _diemDanh.setThoi_gian_quet_van_tay(LocalDateTime.now());
    //                 _diemDanh.setThoi_gian_cap_nhat(null);
    //                 diemDanhRepository.save(_diemDanh);
    //                 return ResponseEntity.ok(
    //                     new MessageResponse("HI " + ten + "(" + String.valueOf(sinhVien.get().getId()) + ")"));
    //             } else {
    //                 return ResponseEntity.badRequest().body(new MessageResponse("NO SCHEDULE"));
    //             }
    //         } else {
    //             return ResponseEntity.badRequest().body(new MessageResponse("STUDENT NO EXIST"));
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body(new MessageResponse("SERVER ERROR"));
    //     }
    // }

    // @PostMapping("/giangvien/{jwt}/diemdanh/update")
    // public ResponseEntity<?> updateDiemDanh(@PathVariable("jwt") String jwt, @RequestBody UpdateDiemDanhRequest updateDiemDanhRequest) {
    //     try {
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == updateDiemDanhRequest.getLop_id()) {
    //                         Optional<PhongLich> lich = phongLichRepository.findById(new PhongLichKey(
    //                                 updateDiemDanhRequest.getPhong_id(), updateDiemDanhRequest.getLich_id()));
    //                         Optional<SinhVien> sinhVien = sinhVienRepository
    //                                 .findById(updateDiemDanhRequest.getSinhvien_id());
    //                         if (lich.isPresent() && sinhVien.isPresent()) {
    //                             if (lich.get().getLop().getId() == i.getId()) {
    //                                 List<Lop> lops = lopRepository.findLopBySinhviensId(sinhVien.get().getId());
    //                                 for (Lop tam : lops) {
    //                                     if (tam.getId() == updateDiemDanhRequest.getLop_id()) {
    //                                         DiemDanhKey diemDanhKey = new DiemDanhKey(
    //                                                 updateDiemDanhRequest.getSinhvien_id(), lich.get().getId());
    //                                         Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
    //                                         if (diemDanh.isPresent()) {
    //                                             DiemDanh _diemDanh = diemDanh.get();
    //                                             _diemDanh.setGhi_chu(updateDiemDanhRequest.getGhi_chu());
    //                                             _diemDanh.setThoi_gian_cap_nhat(LocalDateTime.now());
    //                                             _diemDanh.setTrang_thai(updateDiemDanhRequest.getTrang_thai());
    //                                             diemDanhRepository.save(_diemDanh);
    //                                             return ResponseEntity
    //                                                     .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
    //                                         } else {
    //                                             DiemDanh _diemDanh = new DiemDanh(diemDanhKey, sinhVien.get(),
    //                                                     lich.get(), updateDiemDanhRequest.getTrang_thai(),
    //                                                     updateDiemDanhRequest.getGhi_chu(), null, LocalDateTime.now());
    //                                             diemDanhRepository.save(_diemDanh);
    //                                             return ResponseEntity
    //                                                     .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
    //                                         }
    //                                     }
    //                                 }
    //                                 return ResponseEntity.badRequest()
    //                                         .body(new MessageResponse("Sinh vien khong hoc lop nay!"));
    //                             } else {
    //                                 return ResponseEntity.badRequest()
    //                                         .body(new MessageResponse("Lich khong thuoc lop nay!"));
    //                             }
    //                         } else {
    //                             return ResponseEntity.badRequest()
    //                                     .body(new MessageResponse("Lich hoac sinh vien khong ton tai!"));
    //                         }
    //                     }
    //                 }
    //                 return ResponseEntity.badRequest()
    //                         .body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
    //             }
    //         } else {
    //             return ResponseEntity.badRequest().body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
    //     }
    // }

    // @GetMapping("/test")
    // public ResponseEntity<?> test() {
    //     return ResponseEntity.ok(lopRepository.findLopBySinhviensId(1L));
    // }
    @GetMapping("/all")
    public String getAll() {
        return "All";
    }
    // @GetMapping("user")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    // public String getUser() {
    //     return "User";
    // }
    // @PostMapping("/sinhvien/diemdanh")
    // // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<?> saveDiemDanh(@Valid @RequestBody DiemDanhRequest diemDanhRequest) {
    //     try {
    //         Optional<SinhVien> sinhVien = sinhVienRepository.findById(diemDanhRequest.getId());
    //         if (sinhVien.isPresent()) {
    //             Boolean ca;
    //             Long trangThai = 1L;
    //             LocalTime bdca1 = LocalTime.of(7, 0);
    //             LocalTime ktca1 = LocalTime.of(11, 0);
    //             LocalTime bdca2 = LocalTime.of(13, 0);
    //             LocalTime ktca2 = LocalTime.of(17, 0);
    //             int bdca1s = bdca1.getHour() * 3600 + bdca1.getMinute() * 60 + bdca1.getSecond();
    //             int ktca1s = ktca1.getHour() * 3600 + ktca1.getMinute() * 60 + ktca1.getSecond();
    //             int bdca2s = bdca2.getHour() * 3600 + bdca2.getMinute() * 60 + bdca2.getSecond();
    //             int ktca2s = ktca2.getHour() * 3600 + ktca2.getMinute() * 60 + ktca2.getSecond();
    //             LocalTime gioQuetVanTay = LocalTime.now();
    //             int gioQuetVanTays = gioQuetVanTay.getHour() * 3600 + gioQuetVanTay.getMinute() * 60
    //                     + gioQuetVanTay.getSecond();
    //             if (gioQuetVanTays >= bdca1s && gioQuetVanTays <= ktca1s) {
    //                 ca = false;
    //                 if (gioQuetVanTays > (bdca1s + 1 * 3600)) {
    //                     trangThai = 2L;
    //                 }
    //             } else if (gioQuetVanTays >= bdca2s && gioQuetVanTays <= ktca2s) {
    //                 ca = true;
    //                 if (gioQuetVanTays > (bdca2s + 1 * 3600)) {
    //                     trangThai = 2L;
    //                 }
    //             } /* else {
    //                 return ResponseEntity.badRequest().body(new MessageResponse("ATTENDAN TIMEOUT"));
    //              }*/
                  
    //             ILichHoc lich = phongLichRepository.findLichHocBySinhVienIdAndNgay(sinhVien.get().getId(),
    //                     /* LocalDate.now().toString() */ "2023-01-02", false);
    //             if (lich != null) {
    //                 DiemDanh _diemDanh = new DiemDanh();
    //                 PhongLichKey phongLichKey = new PhongLichKey(lich.getPhong_id(), lich.getLich_id());
    //                 System.out.println(phongLichKey.getLich_id());
    //                 System.out.println(phongLichKey.getPhong_id());
    //                 DiemDanhKey diemDanhKey = new DiemDanhKey(sinhVien.get().getId(), phongLichKey);
    //                 System.out.println(diemDanhKey.getPhonglich_id().getLich_id());
    //                 System.out.println(diemDanhKey.getPhonglich_id().getPhong_id());
    //                 Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
    //                 String ho_ten = sinhVien.get().getHo_ten();
    //                 String ten = ho_ten.substring(ho_ten.lastIndexOf(" ")+1);
    //                 if (diemDanh.isPresent()) {
    //                     return ResponseEntity.badRequest().body(
    //                         new MessageResponse(ten + "(" + String.valueOf(sinhVien.get().getId()) + ")" + "'S PRESENT"));
    //                 }
    //                 _diemDanh.setId(diemDanhKey);
    //                 System.out.println("lich: " + diemDanhKey.getPhonglich_id().getLich_id());
    //                 _diemDanh.setSinhvien(sinhVien.get());
    //                 _diemDanh.setPhonglich(phongLichRepository.findById(phongLichKey).get());
    //                 System.out.println("lich: " + phongLichRepository.findById(phongLichKey).get().getLich().getId());
    //                 _diemDanh.setTrang_thai(trangThai);
    //                 _diemDanh.setGhi_chu(null);
    //                 _diemDanh.setThoi_gian_quet_van_tay(LocalDateTime.now());
    //                 _diemDanh.setThoi_gian_cap_nhat(null);
    //                 diemDanhRepository.save(_diemDanh);
    //                 return ResponseEntity.ok(
    //                     new MessageResponse("HI " + ten + "(" + String.valueOf(sinhVien.get().getId()) + ")"));
    //             } else {
    //                 return ResponseEntity.badRequest().body(new MessageResponse("NO SCHEDULE"));
    //             }
    //         } else {
    //             return ResponseEntity.badRequest().body(new MessageResponse("STUDENT NO EXIST"));
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().body(new MessageResponse("SERVER ERROR"));
    //     }
    // }
    // @GetMapping("/giangvien/loptinchi")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<LopTinChi>> getDanhSachLopByIdUser1(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    //     try {
    //         String jwt = token.split(" ")[1];
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             Long user_id = jwtUtils.getIdFromJwtToken(jwt);
    //             List<LopTinChi> ds_loptinchi = new ArrayList<>();
    //             lopTinChiRepository.findLopTinChiByGiangvienId(user_id).forEach(ds_loptinchi::add);
    //             if (ds_loptinchi.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(ds_loptinchi, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @GetMapping("/test")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public String test1(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            String jwt = token.split(" ")[1];
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                return String.valueOf(userRepository.findByUsername(username).get().getId());
            } else {
                return "Invalid JWT";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    // @GetMapping("/giangvien/lop/{lop_id}/lich")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<ILich>> getDanhSachLichByIdLop1(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
    //         @PathVariable("lop_id") Long lop_id) {
    //     try {
    //         String jwt = token.split(" ")[1];
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             List<ILich> danhsach_phonglich = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == lop_id) {
    //                         phongLichRepository.findByLopId(lop_id).forEach(danhsach_phonglich::add);
    //                         break;
    //                     }
    //                 }
    //             }
    //             if (danhsach_phonglich.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(danhsach_phonglich, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @GetMapping("/giangvien/lop/{lop_id}/lich/{lich_id}/phong/{phong_id}/diemdanh")
    // @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    // public ResponseEntity<List<IDiemDanh>> getDiemDanhByPhongLichLop1(
    //         @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("lop_id") Long lop_id,
    //         @PathVariable("phong_id") Long phong_id, @PathVariable("lich_id") Long lich_id) {
    //     try {
    //         String jwt = token.split(" ")[1];
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             List<IDiemDanh> danhsach_diemdanh = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == lop_id) {
    //                         diemDanhRepository.findDiemDanhByLichPhong(lop_id, phong_id, lich_id)
    //                                 .forEach(danhsach_diemdanh::add);
    //                         break;
    //                     }
    //                 }
    //             }
    //             if (danhsach_diemdanh.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             }
    //             return new ResponseEntity<>(danhsach_diemdanh, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    

    // @PostMapping("/giangvien/diemdanh/update")
    // public ResponseEntity<?> updateDiemDanh1(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody UpdateDiemDanhRequest updateDiemDanhRequest) {
    //     try {
    //         String jwt = token.split(" ")[1];
    //         if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    //             String username = jwtUtils.getUserNameFromJwtToken(jwt);
    //             List<Lop> danhsach_lop = new ArrayList<>();
    //             lopRepository.findByUserUsername(username).forEach(danhsach_lop::add);
    //             if (danhsach_lop.isEmpty()) {
    //                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //             } else {
    //                 for (Lop i : danhsach_lop) {
    //                     if (i.getId() == updateDiemDanhRequest.getLop_id()) {
    //                         Optional<PhongLich> lich = phongLichRepository.findById(new PhongLichKey(
    //                                 updateDiemDanhRequest.getPhong_id(), updateDiemDanhRequest.getLich_id()));
    //                         Optional<SinhVien> sinhVien = sinhVienRepository
    //                                 .findById(updateDiemDanhRequest.getSinhvien_id());
    //                         if (lich.isPresent() && sinhVien.isPresent()) {
    //                             if (lich.get().getLop().getId() == i.getId()) {
    //                                 List<Lop> lops = lopRepository.findLopBySinhviensId(sinhVien.get().getId());
    //                                 for (Lop tam : lops) {
    //                                     if (tam.getId() == updateDiemDanhRequest.getLop_id()) {
    //                                         DiemDanhKey diemDanhKey = new DiemDanhKey(
    //                                                 updateDiemDanhRequest.getSinhvien_id(), lich.get().getId());
    //                                         Optional<DiemDanh> diemDanh = diemDanhRepository.findById(diemDanhKey);
    //                                         if (diemDanh.isPresent()) {
    //                                             DiemDanh _diemDanh = diemDanh.get();
    //                                             _diemDanh.setGhi_chu(updateDiemDanhRequest.getGhi_chu());
    //                                             _diemDanh.setThoi_gian_cap_nhat(LocalDateTime.now());
    //                                             _diemDanh.setTrang_thai(updateDiemDanhRequest.getTrang_thai());
    //                                             diemDanhRepository.save(_diemDanh);
    //                                             return ResponseEntity
    //                                                     .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
    //                                         } else {
    //                                             DiemDanh _diemDanh = new DiemDanh(diemDanhKey, sinhVien.get(),
    //                                                     lich.get(), updateDiemDanhRequest.getTrang_thai(),
    //                                                     updateDiemDanhRequest.getGhi_chu(), null, LocalDateTime.now());
    //                                             diemDanhRepository.save(_diemDanh);
    //                                             return ResponseEntity
    //                                                     .ok(new MessageResponse("Cap nhat diem danh thanh cong!"));
    //                                         }
    //                                     }
    //                                 }
    //                                 return ResponseEntity.badRequest()
    //                                         .body(new MessageResponse("Sinh vien khong hoc lop nay!"));
    //                             } else {
    //                                 return ResponseEntity.badRequest()
    //                                         .body(new MessageResponse("Lich khong thuoc lop nay!"));
    //                             }
    //                         } else {
    //                             return ResponseEntity.badRequest()
    //                                     .body(new MessageResponse("Lich hoac sinh vien khong ton tai!"));
    //                         }
    //                     }
    //                 }
    //                 return ResponseEntity.badRequest()
    //                         .body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
    //             }
    //         } else {
    //             return ResponseEntity.badRequest().body(new MessageResponse("Khong co quyen truy cap tai nguyen nay!"));
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(new MessageResponse("Co gi do sai sai!"));
    //     }
    // }
}
