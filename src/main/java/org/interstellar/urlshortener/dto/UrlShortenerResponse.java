package org.interstellar.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlShortenerResponse {
    private boolean success = false;
    private String shortUrl;
    private String message = "Unable to create short URL";
}
