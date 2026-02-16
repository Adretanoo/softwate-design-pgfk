package com.hotel.domain.events;

import com.hotel.domain.base.DomainEvent;
import java.time.LocalDateTime;

public record RoomBookedEvent(
        String roomId,
        String guestName,
        LocalDateTime occurredOn
) implements DomainEvent {
    public RoomBookedEvent(String roomId, String guestName) {
        this(roomId, guestName, LocalDateTime.now());
    }
}
