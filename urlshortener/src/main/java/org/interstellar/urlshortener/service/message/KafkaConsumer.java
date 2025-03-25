package org.interstellar.urlshortener.service.message;

import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "url_shortened_topic", groupId = "my-group")
    public void consume(UrlShortenerMessage message) {
        System.out.println("Consumed message: " + message.getShortUrl());
    }
}
