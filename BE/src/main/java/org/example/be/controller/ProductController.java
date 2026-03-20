package org.example.be.controller;

import jakarta.validation.Valid;
import org.example.be.dto.BeerRequestDTO;
import org.example.be.dto.BeerResponseDTO;
import org.example.be.entity.Product;
import org.example.be.exception.BeerResponseException;
import org.example.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ArrayList getAllProducts() {
        ArrayList<Product> listProducts = (ArrayList<Product>) productRepository.findAll();
        return listProducts;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        // tìm xong xoá
        Product p = productRepository.findById(id).orElseThrow(
        );
        productRepository.delete(p);
        return ResponseEntity.ok(
                new BeerResponseDTO("200",
                        "Đã xoá beer có mã là " + p.getId() +
                                " có tên là " + p.getName()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody BeerRequestDTO beerRequestDTO) {
        // tạo mới đối tượng
        Product p = new Product();

        // lấy dữ liệu từ DTO và lưu vào đối tượng
        p.setName(beerRequestDTO.getName());
        p.setImage(beerRequestDTO.getImage());
        p.setPrice(beerRequestDTO.getPrice());

        // lưu
        productRepository.save(p);

        // trả response
        return ResponseEntity.ok(
                new BeerResponseDTO("200",
                        "Đã lưu beer mới có mã là " + p.getId() +
                                " có tên là " + p.getName()));
    }
}
