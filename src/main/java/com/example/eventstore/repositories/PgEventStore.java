package com.example.eventstore.repositories;

import com.example.eventstore.entities.DomainEventEntity;
import com.example.eventstore.events.OrderCreatedEvent;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ConditionalOnProperty(name = "eventstore.storage", havingValue = "pg")
@Component
public class PgEventStore implements EventStore {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public DomainEventEntity save(DomainEventEntity event) {
        String payload = event.getPayload().dump();
        String sqlString = "insert into domain_event(id, event_type, payload, received_time) values (:id, :event_type, :payload\\:\\:jsonb, :received_time)";
        Query nativeQuery = entityManager.createNativeQuery(sqlString);

        nativeQuery.setParameter("id", UUID.randomUUID());
        nativeQuery.setParameter("event_type", event.getPayload().getType());
        nativeQuery.setParameter("payload", payload);
        nativeQuery.setParameter("received_time", event.getReceivedTime());
        nativeQuery.executeUpdate();

        return event;
    }

    @Override
    public List<DomainEventEntity> findAll() {
        List<Object[]> rows = entityManager.createNativeQuery("select * from domain_event")
                .unwrap(NativeQuery.class)
                .addScalar("id", StringType.INSTANCE)
                .addScalar("event_type", StringType.INSTANCE)
                .addScalar("received_time", TimestampType.INSTANCE)
                .addScalar("payload", JsonNodeBinaryType.INSTANCE)
                .list();

        return rows.stream().map(row -> {
            String id = (String) row[0];
            String eventType = (String) row[1];
            Timestamp receivedTime = (Timestamp) row[2];
            ObjectNode payload = (ObjectNode) row[3];

            return buildEventEntities(id, eventType, receivedTime, payload);
        }).collect(Collectors.toList());
    }

    private DomainEventEntity buildEventEntities(String id, String eventType, Timestamp receivedTime, ObjectNode payload) {
        if (eventType.equals("OrderCreated")) {
            OrderCreatedEvent event = new OrderCreatedEvent(payload.get("orderId").asText());
            return new DomainEventEntity(id, eventType, receivedTime.toLocalDateTime(), event);
        }
        return null;
    }
}
