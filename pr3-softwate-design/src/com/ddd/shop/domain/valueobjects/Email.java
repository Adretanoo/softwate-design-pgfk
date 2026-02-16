package com.ddd.shop.domain.valueobjects;

public record Email(String value) {
    public Email {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Некоректний формат email");
        }
    }
}