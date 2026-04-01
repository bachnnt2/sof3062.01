package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mau_sac", length = 50)
    private String mauSac;

    @Column(name = "kich_co", length = 50)
    private String kickCo;

    @Column(name = "chat_lieu", length = 100)
    private String chatLieu;

    @Column(name = "bao_hanh")
    private Integer baoHanh;

    @Column(name = "xuat_xu", length = 100)
    private String xuatXu;
    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private Sanpham sanpham;

    @Override
    public String toString() {
        return "ChiTietSanPham{" +
                "id=" + id +
                ", mauSac='" + mauSac + '\'' +
                ", kickCo='" + kickCo + '\'' +
                ", chatLieu='" + chatLieu + '\'' +
                ", baoHanh=" + baoHanh +
                ", xuatXu='" + xuatXu + '\'' +
                ", sanpham=" + sanpham +
                '}';
    }
}
