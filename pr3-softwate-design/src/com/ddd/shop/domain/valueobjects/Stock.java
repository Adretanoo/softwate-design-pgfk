package com.ddd.shop.domain.valueobjects;

public record Stock(int quantity) {
    public Stock {
        if (quantity < 0) throw new IllegalArgumentException("Кількість на складі не може бути від'ємною");
    }
    public Stock decrease(int amount) {
        if (amount > quantity) throw new IllegalArgumentException("Недостатньо товару на складі");
        return new Stock(quantity - amount);
    }
}