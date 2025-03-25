package org.interstellar.urlshortener.service.url;

import org.interstellar.urlshortener.dto.UrlShortenerRequest;
import org.interstellar.urlshortener.dto.UrlShortenerResponse;
import org.interstellar.urlshortener.factory.URLShortenerStrategyFactory;
import org.interstellar.urlshortener.impl.UrlShortenerStrategy;
import org.interstellar.urlshortener.service.message.KafkaProducer;
import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    private final URLShortenerStrategyFactory strategyFactory;
    private final UrlMappingEntityService urlMappingEntityService;
    private final KafkaProducer kafkaProducer;

    public UrlShortenerService(URLShortenerStrategyFactory strategyFactory, UrlMappingEntityService urlMappingEntityService, KafkaProducer kafkaProducer) {
        this.strategyFactory = strategyFactory;
        this.urlMappingEntityService = urlMappingEntityService;
        this.kafkaProducer = kafkaProducer;
    }

    public UrlShortenerResponse performShortenProcess(UrlShortenerRequest request) {
        UrlShortenerResponse response = new UrlShortenerResponse();
        UrlShortenerStrategy urlShortener = this.strategyFactory.createStrategy(request.getStrategy());
        String shortUrl;

        int attempts = 0;
        boolean saveSuccessful;
        do {
            shortUrl = urlShortener.shorten(request.getOriginalUrl());
            saveSuccessful = this.saveUrl(shortUrl, request.getOriginalUrl());
            attempts++;
        } while (!saveSuccessful && attempts < 2);
        

        if (saveSuccessful) {
            response.setSuccess(true);
            response.setShortUrl(shortUrl);
            response.setMessage("Success");

            UrlShortenerMessage message = new UrlShortenerMessage();
            message.setOriginalUrl(request.getOriginalUrl());
            message.setShortUrl(shortUrl);
            message.setUserId(request.getUser().getUserId());
            message.setStrategy(request.getStrategy());
            message.setEventType("CREATE");

            this.kafkaProducer.sendMessage("url_shortened_topic", message);
        }
        return response;
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
