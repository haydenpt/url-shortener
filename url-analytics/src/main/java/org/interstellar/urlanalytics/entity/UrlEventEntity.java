package org.interstellar.urlanalytics.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import org.interstellar.urlanalytics.dto.UrlEventData;

import java.time.OffsetDateTime;

@Entity
@Table(name = "url_event", schema = "analytics")
public class UrlEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "event_data", columnDefinition = "jsonb")
    private UrlEventData eventData;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public UrlEventData getEventData() {
        return eventData;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventData(UrlEventData eventData) {
        this.eventData = eventData;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
