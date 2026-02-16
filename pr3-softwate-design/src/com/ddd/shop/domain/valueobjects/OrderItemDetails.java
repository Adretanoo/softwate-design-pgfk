package com.ddd.shop.domain.valueobjects;

import java.util.UUID;

public record OrderItemDetails(UUID productId, int quantity, Money price) {

    public OrderItemDetails {
        if (productId == null) throw new IllegalArgumentException("ID товару є обов'язковим");
        if (price == null) throw new IllegalArgumentException("Ціна є обов'язковою");
        if (quantity < 1) throw new IllegalArgumentException("Кількість має бути не менше 1");
    }

    public Money getTotalPrice() {
        return price.multiply(quantity);
    }
}