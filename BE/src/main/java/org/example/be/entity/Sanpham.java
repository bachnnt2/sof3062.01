package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "san_pham")
public class Sanpham {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_san_pham", nullable = false, length = 100)
    private String ten;

    @Column(name = "loai", nullable = false, length = 100)
    private String loai;

    @Column(name = "gia")
    private Float gia;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "mo_ta", length = 255)
    private String moTa;

    @OneToMany(mappedBy = "sanpham")
    private List<ChiTietSanPham> lstChiTiet;
}
