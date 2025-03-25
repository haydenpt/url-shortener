package org.interstellar.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.interstellar.urlshortener.dto.UrlShortenerRequest;
import org.interstellar.urlshortener.dto.UrlShortenerResponse;
import org.interstellar.urlshortener.service.url.UrlRetrievalService;
import org.interstellar.urlshortener.service.url.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    private final UrlShortenerService urlShortenerService;
    private final UrlRetrievalService urlRetrievalService;

    public UrlController(UrlShortenerService urlShortenerService, UrlRetrievalService urlRetrievalService) {
        this.urlShortenerService = urlShortenerService;
        this.urlRetrievalService = urlRetrievalService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlShortenerResponse> shortenUrl(@RequestBody UrlShortenerRequest request) {
        return ResponseEntity.ok(urlShortenerService.performShortenProcess(request));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl, HttpServletRequest request) {
        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/url/";
        String url = urlRetrievalService.performUrlRetrievalProcess(shortUrl, domain);
        return new RedirectView(url);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/url/404NOTFOUND")
    public String notFound() {
        return "404NOTFOUND";
    }
}
