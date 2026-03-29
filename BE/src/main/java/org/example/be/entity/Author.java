package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Authors")
public class Author {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Books> listBook;

}
