package com.example.eventstore.controllers;

import com.example.eventstore.entities.DomainEventEntity;
import com.example.eventstore.repositories.EventStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(name = "eventstore.storage", havingValue = "memory")
@Component
public class InMemoryEventStore implements EventStore {
    ArrayList<DomainEventEntity> eventEntities = new ArrayList<>();

    @Override
    public DomainEventEntity save(DomainEventEntity event) {
        eventEntities.add(event);
        return event;
    }

    @Override
    public List<DomainEventEntity> findAll() {
        return eventEntities;
    }
}
