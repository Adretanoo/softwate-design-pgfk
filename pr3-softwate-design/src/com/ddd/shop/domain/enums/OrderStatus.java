package com.ddd.shop.domain.enums;

public enum OrderStatus {
    NEW, CONFIRMED, SHIPPED, DELIVERED;

    public boolean canChangeTo(OrderStatus newStatus) {
        return this.ordinal() < newStatus.ordinal();
    }
}