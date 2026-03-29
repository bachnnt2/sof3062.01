package org.example.be.controller;

import org.example.be.dto.BookResponseDTO;
import org.example.be.entity.Product;
import org.example.be.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ArrayList getAllBooks() {
        ArrayList<BookResponseDTO> listBooks = (ArrayList<BookResponseDTO>) bookRepository.getListBook();
        return listBooks;
    }
}
