package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.UrlShortenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public Url createShortUrl(String originalUrl, User user) {
        Optional<Url> existing = urlRepository.findByOriginalUrl(originalUrl);
        if (existing.isPresent()) {
            return existing.get();
        }
        String shortUrl = UrlShortenerUtil.generateShortUrl();
        while (urlRepository.findByShortUrl(shortUrl).isPresent()) {
            shortUrl = UrlShortenerUtil.generateShortUrl();
        }

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setUser(user);
        return urlRepository.save(url);
    }

    public Url getByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .orElse(null);
    }

    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"))
                .getOriginalUrl();
    }
}