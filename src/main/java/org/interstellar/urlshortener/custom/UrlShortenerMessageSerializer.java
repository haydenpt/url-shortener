package org.interstellar.urlshortener.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.interstellar.urlshortener.dto.UrlShortenerMessage;

import java.io.IOException;

public class UrlShortenerMessageSerializer implements Serializer<UrlShortenerMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, UrlShortenerMessage data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (IOException e) {
            // TODO: add logging
            e.printStackTrace();
            return null;
        }
    }
}
