package com.example.eventstore.repositories;

import com.example.eventstore.entities.DomainEventEntity;

import java.util.ArrayList;
import java.util.List;

public interface EventStore {
    DomainEventEntity save(DomainEventEntity event);

    List<DomainEventEntity> findAll();
}
