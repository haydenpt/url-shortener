package org.interstellar.urlshortener.service;

import org.interstellar.urlshortener.dao.UrlMappingEntityRepository;
import org.interstellar.urlshortener.entity.UrlShortenerEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlMappingEntityService {

    private final UrlMappingEntityRepository urlMappingEntityRepository;

    public UrlMappingEntityService(UrlMappingEntityRepository urlMappingEntityRepository) {
        this.urlMappingEntityRepository = urlMappingEntityRepository;
    }

    public Optional<UrlShortenerEntity> findByShortUrl(String shortUrl) {
        return urlMappingEntityRepository.findByShortUrl(shortUrl);
    }

    public void save(String shortUrl, String originalUrl) {
        UrlShortenerEntity entity = new UrlShortenerEntity();
        entity.setShortUrl(shortUrl);
        entity.setOriginalUrl(originalUrl);
        urlMappingEntityRepository.save(entity);
    }

    public boolean urlMappingExists(String shortUrl, String originalUrl) {
        Optional<UrlShortenerEntity> entity = findByShortUrl(shortUrl);
        return entity.map(urlShortenerEntity -> urlShortenerEntity.getOriginalUrl().equals(originalUrl)).orElse(false);
    }
}
