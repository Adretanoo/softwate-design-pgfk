package com.hotel.domain.model;

import com.hotel.domain.base.AggregateRoot;
import com.hotel.domain.events.RoomBookedEvent;
import com.hotel.domain.events.RoomCheckedInEvent;

public class RoomBooking extends AggregateRoot {
    private final String roomId;
    private String guestName;
    private BookingState state;

    public enum BookingState {
        AVAILABLE, BOOKED, OCCUPIED
    }

    public RoomBooking(String roomId) {
        this.roomId = roomId;
        this.state = BookingState.AVAILABLE;
    }

    public void bookRoom(String guestName) {
        if (this.state != BookingState.AVAILABLE) {
            throw new IllegalStateException("Номер вже зайнятий або заброньований!");
        }

        this.guestName = guestName;
        this.state = BookingState.BOOKED;

        registerEvent(new RoomBookedEvent(this.roomId, this.guestName));
    }

    public void checkIn() {
        if (this.state != BookingState.BOOKED) {
            throw new IllegalStateException("Номер має бути заброньований перед заселенням!");
        }

        this.state = BookingState.OCCUPIED;

        registerEvent(new RoomCheckedInEvent(this.roomId, this.guestName));
    }
}
