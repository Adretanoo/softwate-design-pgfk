package com.hotel.application.handlers;

import com.hotel.domain.base.DomainEvent;

public interface EventHandler<T extends DomainEvent> {
    void handle(T event);
    Class<T> getEventClass();
}