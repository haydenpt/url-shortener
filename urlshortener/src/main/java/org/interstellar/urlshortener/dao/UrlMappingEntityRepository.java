package org.interstellar.urlshortener.dao;

import org.interstellar.urlshortener.entity.UrlShortenerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlMappingEntityRepository extends MongoRepository<UrlShortenerEntity, String> {

    Optional<UrlShortenerEntity> findByShortUrl(String shortUrl);
}
