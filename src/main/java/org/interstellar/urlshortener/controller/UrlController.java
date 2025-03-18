package org.interstellar.urlshortener.controller;

import org.interstellar.urlshortener.dto.UrlShortenerRequest;
import org.interstellar.urlshortener.dto.UrlShortenerResponse;
import org.interstellar.urlshortener.service.url.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    private final UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlShortenerResponse> shortenUrl(@RequestBody UrlShortenerRequest request) {
        return ResponseEntity.ok(urlShortenerService.performShortenProcess(request));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        return new RedirectView("https://www.google.com");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
