package com.ddd.shop.domain.valueobjects;

public record Name(String firstName, String lastName) {
    public Name {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("Ім'я обов'язкове");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Прізвище обов'язкове");
    }
    public String getFullName() { return firstName + " " + lastName; }
}