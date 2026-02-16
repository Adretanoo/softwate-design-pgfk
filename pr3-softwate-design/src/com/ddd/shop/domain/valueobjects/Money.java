package com.ddd.shop.domain.valueobjects;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Сума не може бути від'ємною");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Валюта не може бути порожньою");
        }
    }

    public Money add(Money other) {
        checkCurrencyMatch(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        checkCurrencyMatch(other);
        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("Результат віднімання не може бути від'ємним");
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    public Money multiply(int multiplier) {
        if (multiplier < 0) throw new IllegalArgumentException("Множник не може бути від'ємним");
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }

    private void checkCurrencyMatch(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Валюти мають співпадати");
        }
    }

    public String formatted() {
        return amount.toString() + " " + currency;
    }
}