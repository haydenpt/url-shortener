package org.interstellar.urlshortener.service;

import org.interstellar.urlshortener.dto.UrlShortenerRequest;
import org.interstellar.urlshortener.dto.UrlShortenerResponse;
import org.interstellar.urlshortener.factory.URLShortenerStrategyFactory;
import org.interstellar.urlshortener.impl.UrlShortenerStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    private final URLShortenerStrategyFactory strategyFactory;
    private final UrlMappingEntityService urlMappingEntityService;

    public UrlShortenerService(URLShortenerStrategyFactory strategyFactory, UrlMappingEntityService urlMappingEntityService) {
        this.strategyFactory = strategyFactory;
        this.urlMappingEntityService = urlMappingEntityService;
    }

    public UrlShortenerResponse performShortenProcess(UrlShortenerRequest request) {
        UrlShortenerResponse response = new UrlShortenerResponse();

        String shortUrl = shortenUrl(request.getOriginalUrl(), request.getStrategy());

        int attempts = 0;
        boolean saveSuccessful = saveUrl(shortUrl, request.getOriginalUrl());
        while (!saveSuccessful && attempts < 5) {
            shortUrl = shortenUrl(request.getOriginalUrl(), request.getStrategy());
            saveSuccessful = saveUrl(shortUrl, request.getOriginalUrl());
            attempts++;
        }

        if (saveSuccessful) {
            response.setSuccess(true);
            response.setShortUrl(shortUrl);
            response.setMessage("Success");
            // TODO: Add more logic
        }
        return response;
    }

    private String shortenUrl(String originalUrl, String strategy) {
        UrlShortenerStrategy urlShortener = this.strategyFactory.createStrategy(strategy);
        return urlShortener.shorten(originalUrl);
    }

    private boolean saveUrl(String shortUrl, String originalUrl) {
        try {
            this.urlMappingEntityService.save(shortUrl, originalUrl);
            return true;
        } catch (Exception e) {
            if (e.getMessage().contains("E11000 duplicate key error collection") && this.urlMappingEntityService.urlMappingExists(shortUrl, originalUrl)) {
                logger.info("Short URL already exists for the original URL. Skipping save.");
                return true;
            }
            logger.error("Error saving URL mapping", e);
        }
        return false;
    }

}
