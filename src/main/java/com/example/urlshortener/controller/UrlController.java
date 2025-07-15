package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlCreateRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/url/{shortUrl}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortUrl,
                                          @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        urlService.deleteUrl(shortUrl, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/user")
    public ResponseEntity<List<UrlResponse>> getUserUrls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ) {
        String email = authentication.getName();
        Page<Url> urls = urlService.getUserUrls(email, page, size);

        List<UrlResponse> response = urls.getContent().stream()
                .map(url -> new UrlResponse(url.getOriginalUrl(), url.getShortUrl()))
                .toList();

        return ResponseEntity.ok(response);
    }

}