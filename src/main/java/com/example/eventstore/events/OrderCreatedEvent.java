package com.example.eventstore.events;

import com.example.eventstore.utilities.JSONHelper;

public class OrderCreatedEvent implements DomainEvent {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String dump() {
        return JSONHelper.dump(this);
    }

    @Override
    public String getType() {
        return "OrderCreated";
    }
}
