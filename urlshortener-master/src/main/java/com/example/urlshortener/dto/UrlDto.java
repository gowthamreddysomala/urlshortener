package com.example.urlshortener.dto;

import lombok.Data;

@Data
public class UrlDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
}