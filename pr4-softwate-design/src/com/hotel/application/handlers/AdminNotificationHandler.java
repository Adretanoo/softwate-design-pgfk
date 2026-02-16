package com.hotel.application.handlers;

import com.hotel.domain.events.RoomCheckedInEvent;

public class AdminNotificationHandler implements EventHandler<RoomCheckedInEvent> {
    @Override
    public void handle(RoomCheckedInEvent event) {
        System.out.println("[АДМІН] Увага! Гість " + event.guestName()
                + " щойно заселився у номер " + event.roomId() + ".");
    }

    @Override
    public Class<RoomCheckedInEvent> getEventClass() {
        return RoomCheckedInEvent.class;
    }
}

