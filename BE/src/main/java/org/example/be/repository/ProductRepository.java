package org.example.be.repository;

import org.example.be.dto.ChiTietSanPhamResponseDTO;
import org.example.be.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //    Phân trang
    Page<Product> findAll(Pageable pageable);

    @Query("""
               SELECT p FROM Product p WHERE p.name LIKE :name%
            """)
    List<Product> findProductByName(@Param("name") String name);

    //    Tìm sản phẩm theo tên kết thúc bằng

    //    Tìm sản phẩm theo tên chứa ký tự gì đó

    // tìm theo khoảng giá
}
