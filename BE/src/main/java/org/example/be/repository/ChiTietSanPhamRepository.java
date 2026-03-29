package org.example.be.repository;

import org.example.be.dto.ChiTietSanPhamResponseDTO;
import org.example.be.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    @Query("""
               SELECT new org.example.be.dto.ChiTietSanPhamResponseDTO (
                   c.id, c.sanpham.id, c.mauSac, c.kickCo,
                               c.chatLieu, c.baoHanh, c.xuatXu, c.sanpham.ten,
                                            c.sanpham.loai )
               FROM ChiTietSanPham c
            """)
    List<ChiTietSanPhamResponseDTO> getAllChiTietSanPham();
}
