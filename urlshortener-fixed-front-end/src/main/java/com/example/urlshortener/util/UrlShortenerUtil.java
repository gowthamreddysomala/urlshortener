package com.example.urlshortener.util;

import java.util.UUID;

public class UrlShortenerUtil {
    public static String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}