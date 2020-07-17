package com.example.eventstore.utilities;

import com.example.eventstore.events.OrderCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHelper {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static String dump(OrderCreatedEvent value)  {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
