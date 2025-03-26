package org.interstellar.urlanalytics.service;

import org.interstellar.urlanalytics.dao.UrlEventEntityRepository;
import org.interstellar.urlanalytics.dto.UrlEventData;
import org.interstellar.urlanalytics.entity.UrlEventEntity;
import org.interstellar.urlshortenercommonlib.dto.UrlShortenerMessage;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class UrlEventEntityService {
    private final UrlEventEntityRepository urlEventEntityRepository;

    public UrlEventEntityService(UrlEventEntityRepository urlEventEntityRepository) {
        this.urlEventEntityRepository = urlEventEntityRepository;
    }

    public void save(UrlShortenerMessage urlShortenerMessage) {
        UrlEventData urlEventData = new UrlEventData();
        urlEventData.setOriginalUrl(urlShortenerMessage.getOriginalUrl());
        urlEventData.setShortUrl(urlShortenerMessage.getShortUrl());
        urlEventData.setUserId(urlShortenerMessage.getUserId());

        ZoneOffset zoneOffSet= ZoneOffset.of("+02:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);

        UrlEventEntity urlEventEntity = new UrlEventEntity();
        urlEventEntity.setEventName(urlShortenerMessage.getEventType());
        urlEventEntity.setEventData(urlEventData);
        urlEventEntity.setCreatedAt(offsetDateTime);

        urlEventEntityRepository.save(urlEventEntity);
    }
}
