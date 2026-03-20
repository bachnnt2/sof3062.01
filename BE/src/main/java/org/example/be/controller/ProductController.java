package org.example.be.controller;

import jakarta.validation.Valid;
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
}
