package com.example.eventstore.controllers;

import com.example.eventstore.entities.DomainEventEntity;
import com.example.eventstore.events.OrderCreatedEvent;
import com.example.eventstore.repositories.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class EventsController {
    @Autowired
    EventStore eventStore;

    @PostMapping("/events")
    public ResponseEntity<Object> create(@RequestBody OrderRequest orderRequest) {
        OrderCreatedEvent event = new OrderCreatedEvent(orderRequest.getOrderId());
        DomainEventEntity entity = new DomainEventEntity("OrderCreated", LocalDateTime.now(), event);
        eventStore.save(entity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(eventStore.findAll());
    }
}
