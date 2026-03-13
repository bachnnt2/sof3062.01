package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Photo", length = 200)
    private String photo;

    @ColumnDefault("1")
    @Column(name = "Activated", nullable = false)
    private Boolean activated = false;

    @ColumnDefault("0")
    @Column(name = "Admin", nullable = false)
    private Boolean admin = false;
}
