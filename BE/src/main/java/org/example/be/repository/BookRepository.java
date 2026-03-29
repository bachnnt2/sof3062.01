package org.example.be.repository;

import org.example.be.dto.BookResponseDTO;
import org.example.be.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Integer> {
    @Query("""
               SELECT new org.example.be.dto.BookResponseDTO (
                   b.id, b.title, b.author.id, b.author.name
               )
               FROM Books b
            """)
    List<BookResponseDTO> getListBook();

}
