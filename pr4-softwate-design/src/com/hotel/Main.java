package com.hotel;

import com.hotel.application.handlers.AdminNotificationHandler;
import com.hotel.application.handlers.BookingConfirmationHandler;
import com.hotel.domain.model.RoomBooking;
import com.hotel.infrastructure.SimpleEventBus;

public class Main {
    public static void main(String[] args) {
        // 1. Ініціалізація шини подій та реєстрація обробників
        SimpleEventBus eventBus = new SimpleEventBus();
        eventBus.registerHandler(new BookingConfirmationHandler());
        eventBus.registerHandler(new AdminNotificationHandler());

        // 2. Створення агрегату (Номера в готелі)
        RoomBooking room101 = new RoomBooking("101-A");

        System.out.println("--- Крок 1: Гість бронює номер ---");
        room101.bookRoom("Олександр Іванов");

        // Витягуємо події з агрегату та відправляємо в EventBus
        eventBus.publish(room101.getDomainEvents());
        room101.clearEvents();

        System.out.println("\n--- Крок 2: Гість заселяється ---");
        room101.checkIn();

        // Знову публікуємо нові події
        eventBus.publish(room101.getDomainEvents());
        room101.clearEvents();
    }
}