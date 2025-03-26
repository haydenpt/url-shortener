package org.interstellar.urlanalytics.service;

import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.springframework.stereotype.Service;

@Service
public class AnalyticService {
    private final UrlEventEntityService urlEventEntityService;

    public AnalyticService(UrlEventEntityService urlEventEntityService) {
        this.urlEventEntityService = urlEventEntityService;
    }

    public void recordEvent(UrlShortenerMessage urlShortenerMessage) {
        urlEventEntityService.save(urlShortenerMessage);
    }
}
