package com.hotel.infrastructure;

import com.hotel.application.handlers.EventHandler;
import com.hotel.domain.base.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class SimpleEventBus {
    private final List<EventHandler<?>> handlers = new ArrayList<>();

    public void registerHandler(EventHandler<?> handler) {
        handlers.add(handler);
    }

    @SuppressWarnings("unchecked")
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            for (EventHandler<?> handler : handlers) {
                if (handler.getEventClass().equals(event.getClass())) {
                    ((EventHandler<DomainEvent>) handler).handle(event);
                }
            }
        }
    }
}

