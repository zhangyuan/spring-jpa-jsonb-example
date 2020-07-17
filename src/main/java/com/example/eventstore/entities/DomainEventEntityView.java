package com.example.eventstore.entities;

import com.example.eventstore.events.DomainEvent;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalTime;

public class DomainEventEntityView {
    private LocalTime receivedTime;
    private JsonNode payload;
    private String eventType;

    public LocalTime getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(LocalTime receivedTime) {
        this.receivedTime = receivedTime;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
