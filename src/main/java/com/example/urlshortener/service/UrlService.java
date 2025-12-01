package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.UrlShortenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Url> getUserUrls(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return urlRepository.findByUserEmail(email, pageable);
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

    public void deleteUrl(String shortUrl, String userEmail) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        if (!url.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized to delete this URL");
        }

        urlRepository.delete(url);
    }
}