package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Books")
public class Books {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Author author;
}
