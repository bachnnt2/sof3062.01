package org.example.be.controller;

import org.example.be.dto.BookResponseDTO;
import org.example.be.dto.ChiTietSanPhamResponseDTO;
import org.example.be.repository.BookRepository;
import org.example.be.repository.ChiTietSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/chitietsanpham")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @GetMapping
    public ArrayList getAllChiTietSanPham() {
        ArrayList<ChiTietSanPhamResponseDTO> lstChitiet =
                (ArrayList<ChiTietSanPhamResponseDTO>) chiTietSanPhamRepository.getAllChiTietSanPham();
        return lstChitiet;
    }
}
