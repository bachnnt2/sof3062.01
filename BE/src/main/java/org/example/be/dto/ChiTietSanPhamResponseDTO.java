package org.example.be.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPhamResponseDTO {
    private Integer idChiTietSanPham;
    private Integer idSanPham;
    private String mauSac;
    private String kickCo;
    private String chatLieu;
    private Integer baoHanh;
    private String xuatXu;
    private String tenSanPham;
    private String loaiSanPham;
}
