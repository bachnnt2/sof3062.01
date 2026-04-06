package org.example.be.service;

import jakarta.validation.Valid;
import org.example.be.dto.BeerRequestDTO;
import org.example.be.entity.Product;
import org.example.be.exception.BeerResponseException;
import org.example.be.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ArrayList getAllProducts() {
        ArrayList<Product> listProducts = (ArrayList<Product>) productRepository.findAll();
        return listProducts;
    }

    public ArrayList getAllProductsByName(@RequestParam("name") String name) {
        ArrayList<Product> listProducts = (ArrayList<Product>) productRepository.findProductByName(name);
        return listProducts;
    }

    public void delete(@PathVariable("id") Integer id) {
        // tìm xong xoá
        Product p = productRepository.findById(id).orElseThrow();
        productRepository.delete(p);
    }

    public Product add(@Valid @RequestBody BeerRequestDTO beerRequestDTO) {
        // tạo mới đối tượng
        Product p = new Product();

        // lấy dữ liệu từ DTO và lưu vào đối tượng
        p.setName(beerRequestDTO.getName());
        p.setImage(beerRequestDTO.getImage());
        p.setPrice(beerRequestDTO.getPrice());

        // lưu
        productRepository.save(p);

        // trả response
        return p;
    }

    // luồng update
    @PutMapping("/update/{id}")
    public Product update(@PathVariable("id") Integer id,
                          @Valid @RequestBody BeerRequestDTO beerRequestDTO) {
        // Tìm đối tượng để sửa
        Product pSua = productRepository.findById(id).orElseThrow(
                () -> new BeerResponseException("Tao không tìm thấy nó"));

        // Ghi đè thằng request lên thằng sửa,
        // không ghi đè trường id để tránh bị hiểu lầm là tạo mới

        BeanUtils.copyProperties(beerRequestDTO, pSua, "id");

        // lưu
        productRepository.save(pSua);

        // trả response
        return pSua;
    }

    public Page<Product> getProductPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
}
