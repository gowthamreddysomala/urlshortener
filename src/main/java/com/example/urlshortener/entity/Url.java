package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
} 