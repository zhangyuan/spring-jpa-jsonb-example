package com.example.eventstore.events;

public interface DomainEvent {
    String dump();
    String getType();
}
