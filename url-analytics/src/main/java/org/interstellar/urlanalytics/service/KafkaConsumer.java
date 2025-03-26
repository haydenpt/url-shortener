package org.interstellar.urlanalytics.service;

import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final AnalyticService analyticService;

    public KafkaConsumer(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    @KafkaListener(topics = "url_event", groupId = "url_shortener_group")
    public void consume(UrlShortenerMessage message) {
        analyticService.recordEvent(message);
    }
}
