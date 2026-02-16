package com.hotel.domain.base;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredOn();
}

