package org.interstellar.urlshortener.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.interstellar.urlshortener.dto.UrlShortenerMessage;

import java.io.IOException;

public class UrlShortenerMessageDeserializer implements Deserializer<UrlShortenerMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UrlShortenerMessage deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, UrlShortenerMessage.class);
        } catch (IOException e) {
            // TODO: add logging
            e.printStackTrace();
            return null;
        }
    }
}
