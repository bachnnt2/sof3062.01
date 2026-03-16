package org.example.be.controller;
import org.example.be.entity.Product;
import org.example.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
