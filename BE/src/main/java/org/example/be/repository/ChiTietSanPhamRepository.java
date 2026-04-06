package org.example.be.repository;

import org.example.be.dto.ChiTietSanPhamResponseDTO;
import org.example.be.entity.ChiTietSanPham;
import org.example.be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
               SELECT new org.example.be.dto.ChiTietSanPhamResponseDTO (
                   c.id, c.sanpham.id, c.mauSac, c.kickCo,
                               c.chatLieu, c.baoHanh, c.xuatXu, c.sanpham.ten,
                                            c.sanpham.loai )
               FROM ChiTietSanPham c WHERE c.baoHanh > :baohanh
            """)
    Page<ChiTietSanPhamResponseDTO> findPageChiTietSanPhamByBaohanh(@Param("baohanh") int baohanh,
                                                                    Pageable pageable);

    @Query("""
               SELECT new org.example.be.dto.ChiTietSanPhamResponseDTO (
                   c.id, c.sanpham.id, c.mauSac, c.kickCo,
                               c.chatLieu, c.baoHanh, c.xuatXu, c.sanpham.ten,
                                            c.sanpham.loai )
               FROM ChiTietSanPham c WHERE c.chatLieu LIKE :chatlieu
            """)
    List<ChiTietSanPhamResponseDTO> findChiTietSanPhamByChatlieu(@Param("chatlieu") String chatlieu);
}
