package com.example.eventstore.entities;

import com.example.eventstore.events.DomainEvent;

import java.time.LocalDateTime;

public class DomainEventEntity {
    private String id;
    private LocalDateTime receivedTime;
    private DomainEvent payload;
    private String eventType;

    public DomainEventEntity(String id, String eventType, LocalDateTime receivedTime, DomainEvent payload) {
        this.id = id;
        this.eventType = eventType;
        this.receivedTime = receivedTime;
        this.payload = payload;
    }

    public DomainEventEntity(String eventType, LocalDateTime receivedTime, DomainEvent payload) {
        this.eventType = eventType;
        this.receivedTime = receivedTime;
        this.payload = payload;
    }

    public String getEventType() {
        return eventType;
    }

    public DomainEventEntity(LocalDateTime receivedTime, DomainEvent payload) {
        this.receivedTime = receivedTime;
        this.payload = payload;
    }

    public LocalDateTime getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(LocalDateTime receivedTime) {
        this.receivedTime = receivedTime;
    }

    public DomainEvent getPayload() {
        return payload;
    }

    public void setPayload(DomainEvent evebt) {
        this.payload = evebt;
    }
}
