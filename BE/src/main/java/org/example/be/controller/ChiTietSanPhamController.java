package org.example.be.controller;

import jakarta.validation.Valid;
import org.example.be.dto.*;
import org.example.be.entity.ChiTietSanPham;
import org.example.be.entity.Product;
import org.example.be.entity.Sanpham;
import org.example.be.exception.BeerResponseException;
import org.example.be.repository.ChiTietSanPhamRepository;
import org.example.be.repository.SanPhamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/chitietsanpham")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private SanPhamRepository sanphamRepository;

    // lấy hết (các file liên quan: ChiTietSanPhamRepository, ChiTietSanPhamResponseDTO và file controller này )
    @GetMapping
    public ArrayList getAllChiTietSanPham() {
        ArrayList<ChiTietSanPhamResponseDTO> lstChitiet = (ArrayList<ChiTietSanPhamResponseDTO>) chiTietSanPhamRepository.getAllChiTietSanPham();
        return lstChitiet;
    }

    @GetMapping("/chatlieu")
    public ArrayList getAllChiTietSanPhamByChatlieu(@RequestParam("chatlieu")
                                                    String chatlieu) {
        ArrayList<ChiTietSanPhamResponseDTO> lstChitiet =
                (ArrayList<ChiTietSanPhamResponseDTO>) chiTietSanPhamRepository.findChiTietSanPhamByChatlieu(chatlieu);
        return lstChitiet;
    }

    @GetMapping("/page")
    public Page<ChiTietSanPhamResponseDTO> findPageChiTietSanPhamByBaohanh(@RequestParam("baohanh")
                                                                           int baohanh,
                                                                           @RequestParam int page,
                                                                           @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPhamResponseDTO> pageChiTiet = chiTietSanPhamRepository.findPageChiTietSanPhamByBaohanh(baohanh, pageable);
        return pageChiTiet;
    }

    @PostMapping("/them")
    public ResponseEntity add(@Valid @RequestBody ChiTietSanPhamRequestDTO chiTietSanPhamRequestDTO) {
        // tạo mới đối tượng
        ChiTietSanPham chitiet = new ChiTietSanPham();

        // lấy dữ liệu từ DTO và lưu vào đối tượng
        chitiet.setBaoHanh(chiTietSanPhamRequestDTO.getBaoHanh());
        chitiet.setChatLieu(chiTietSanPhamRequestDTO.getChatLieu());
        chitiet.setKickCo(chiTietSanPhamRequestDTO.getKickCo());
        chitiet.setMauSac(chiTietSanPhamRequestDTO.getMauSac());
        chitiet.setXuatXu(chiTietSanPhamRequestDTO.getXuatXu());

        // tìm Sản phẩm theo trường sanphamid trong request
        // ở đây thầy dùng tạm beerResponseException, anh em viết 1 file exception tuỳ ý nhé
        Sanpham sanpham = sanphamRepository.findById(chiTietSanPhamRequestDTO.getSanphamid()).orElseThrow(() -> new BeerResponseException("Không tìm thấy sản phẩm rồi"));

        // set trường Sản phẩm trong Chitietsanpham đang tạo bằng Sản phẩm tìm được ở trên
        chitiet.setSanpham(sanpham);
        // lưu
        chiTietSanPhamRepository.save(chitiet);

        // trả response
        // Nhớ tạo toString ở SanPham và ChiTietSanPham (thư mục Entity)
        return ResponseEntity.ok(new BeerResponseDTO("200", "Đã lưu chi tiết sản phẩm mới có thông tin là " + chitiet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        // tìm chi tiết sản phẩm xong xoá
        ChiTietSanPham chitiet = chiTietSanPhamRepository.findById(id).orElseThrow(() -> new BeerResponseException("Không tìm thấy cái cần xoá"));
        chiTietSanPhamRepository.delete(chitiet);
        return ResponseEntity.ok(new BeerResponseDTO("200", "Đã xoá chi tiết sản phẩm " + chitiet));
    }

    // luồng update, update bản ghi chi tiết sản phẩm
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @Valid @RequestBody ChiTietSanPhamRequestDTO chiTietSanPhamRequestDTO) {
        // Tìm bản ghi chi tiết sản phẩm cần sửa theo id
        ChiTietSanPham chitiet = chiTietSanPhamRepository.findById(id).orElseThrow(() -> new BeerResponseException("Tao không tìm thấy nó"));

        // Ghi đè thằng request lên thằng sửa,
        // không ghi đè trường id để tránh bị hiểu lầm là tạo mới

        BeanUtils.copyProperties(chiTietSanPhamRequestDTO, chitiet, "id");
        // lưu
        chiTietSanPhamRepository.save(chitiet);

        // trả response
        return ResponseEntity.ok(new BeerResponseDTO("200", "Đã update chi tiết sản phẩm " + chitiet));
    }


}
