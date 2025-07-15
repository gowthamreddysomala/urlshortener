package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponse {
    private String originalUrl;
    private String shortUrl;

}



