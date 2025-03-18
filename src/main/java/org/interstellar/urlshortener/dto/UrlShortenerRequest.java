package org.interstellar.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlShortenerRequest {
    private String originalUrl;
    private String strategy = "default";
    private UserDto user;
}
