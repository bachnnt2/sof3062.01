package org.example.be.controller;

import jakarta.validation.Valid;
import org.example.be.dto.BeerRequestDTO;
import org.example.be.dto.BeerResponseDTO;
import org.example.be.entity.Product;
import org.example.be.exception.BeerResponseException;
import org.example.be.repository.ProductRepository;
import org.example.be.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ArrayList getAllProducts() {
        ArrayList<Product> listProducts = productService.getAllProducts();
        return listProducts;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        // tìm xong xoá
        productService.delete(id);
        return ResponseEntity.ok(
                new BeerResponseDTO("200",
                        "Đã xoá beer có mã là " + id));
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody BeerRequestDTO beerRequestDTO) {
        Product p = productService.add(beerRequestDTO);

        // trả response
        return ResponseEntity.ok(
                new BeerResponseDTO("200",
                        "Đã lưu beer mới có mã là " + p.getId() +
                                " có tên là " + p.getName()));
    }

    // luồng update
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @Valid @RequestBody BeerRequestDTO beerRequestDTO) {
        Product pSua = productService.update(id, beerRequestDTO);

        // trả response
        return ResponseEntity.ok(
                new BeerResponseDTO("200",
                        "Đã sửa thằng beer có mã là " + pSua.getId() +
                                " có tên là " + pSua.getName()));
    }
}
