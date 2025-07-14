package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlCreateRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final UserRepository userRepository;

    @PostMapping
    public UrlResponse create(@RequestBody UrlCreateRequest request,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        Url url = urlService.createShortUrl(request.getOriginalUrl(), user);
        return new UrlResponse(url.getOriginalUrl(), url.getShortUrl());
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
}