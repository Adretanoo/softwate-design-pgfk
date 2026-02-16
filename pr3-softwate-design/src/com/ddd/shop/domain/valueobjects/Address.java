package com.ddd.shop.domain.valueobjects;

public record Address(String country, String city, String street, String postalCode) {

    public Address {
        if (country == null || country.isBlank()) throw new IllegalArgumentException("Країна є обов'язковою");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("Місто є обов'язковим");
        if (street == null || street.isBlank()) throw new IllegalArgumentException("Вулиця є обов'язковою");

        if (postalCode == null || !postalCode.matches("\\d{5,6}")) {
            throw new IllegalArgumentException("Невірний формат поштового індексу");
        }
    }

    public String getFormattedAddress() {
        return String.format("%s, %s, %s, %s", country, city, street, postalCode);
    }
}