package org.interstellar.urlshortener.service.message;

import org.interstellar.urlshortener.dto.UrlShortenerMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, UrlShortenerMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, UrlShortenerMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, UrlShortenerMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
