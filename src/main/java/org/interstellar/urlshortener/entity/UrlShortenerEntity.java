package org.interstellar.urlshortener.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "urlMapping")
public class UrlShortenerEntity {
    @Id
    private ObjectId id;

    @Indexed (unique = true)
    private String shortUrl;

    private String originalUrl;
}
