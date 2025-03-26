package org.interstellar.urlshortener.service.url;

import org.interstellar.urlshortener.entity.UrlShortenerEntity;
import org.interstellar.urlshortener.service.message.KafkaProducer;
import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlRetrievalService {

    private static final String NOT_FOUND = "404NOTFOUND";
    private static final String HTTPS = "https://";

    private final UrlMappingEntityService urlMappingEntityService;
    private final KafkaProducer kafkaProducer;

    public UrlRetrievalService(UrlMappingEntityService urlMappingEntityService, KafkaProducer kafkaProducer) {
        this.urlMappingEntityService = urlMappingEntityService;
        this.kafkaProducer = kafkaProducer;
    }

    public String performUrlRetrievalProcess(String shortUrl, String domain) {
        String url = getOriginalUrl(shortUrl);
        if (isValidUrl(url)) {
            url= cleanUrl(url);
            UrlShortenerMessage message = new UrlShortenerMessage();
            message.setShortUrl(shortUrl);
            message.setOriginalUrl(url);
            message.setEventType("RETRIEVE");
            this.kafkaProducer.sendMessage("url_event", message);
        }
        else {
            url = domain + NOT_FOUND;
        }
        System.out.println("Redirecting to: " + url);
        return url;
    }

    private String getOriginalUrl(String shortUrl) {
        Optional<UrlShortenerEntity> entity = urlMappingEntityService.findByShortUrl(shortUrl);
        if (entity.isPresent()) {
            return entity.get().getOriginalUrl();
        }
        return NOT_FOUND;
    }

    private String cleanUrl(String url) {
        if (!url.startsWith(HTTPS)) {
            return HTTPS + url.trim();
        }
        return url;
    }

    private boolean isValidUrl(String url) {
        return !url.equals(NOT_FOUND);
    }
}
