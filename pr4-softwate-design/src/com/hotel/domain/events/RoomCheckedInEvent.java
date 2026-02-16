package com.hotel.domain.events;

import com.hotel.domain.base.DomainEvent;
import java.time.LocalDateTime;

public record RoomCheckedInEvent(
        String roomId,
        String guestName,
        LocalDateTime occurredOn
) implements DomainEvent {
    public RoomCheckedInEvent(String roomId, String guestName) {
        this(roomId, guestName, LocalDateTime.now());
    }
}
