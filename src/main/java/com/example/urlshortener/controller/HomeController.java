package com.example.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {
    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/active")
    public String active() {
        return "this is for Cronjob, current time in server is : " + System.currentTimeMillis();
    }
}
