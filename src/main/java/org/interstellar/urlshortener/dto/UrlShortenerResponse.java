package org.interstellar.urlshortener.dto;

public class UrlShortenerResponse {
    private boolean success = false;
    private String shortUrl;
    private String message = "Unable to create short URL";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
