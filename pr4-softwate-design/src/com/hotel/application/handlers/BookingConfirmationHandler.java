package com.hotel.application.handlers;

import com.hotel.domain.events.RoomBookedEvent;

public class BookingConfirmationHandler implements EventHandler<RoomBookedEvent> {
    @Override
    public void handle(RoomBookedEvent event) {
        System.out.println("[EMAIL] Відправлення підтвердження: "
                + "Шановний(а) " + event.guestName()
                + ", ваш номер " + event.roomId() + " успішно заброньовано!");
    }

    @Override
    public Class<RoomBookedEvent> getEventClass() {
        return RoomBookedEvent.class;
    }
}
