package com.ddd.shop.domain.entities;

import com.ddd.shop.domain.enums.OrderStatus;
import com.ddd.shop.domain.valueobjects.Address;
import com.ddd.shop.domain.valueobjects.Money;
import com.ddd.shop.domain.valueobjects.OrderItemDetails;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private Customer customer;
    private final List<OrderItemDetails> items;
    private Money totalPrice;
    private OrderStatus status;
    private Address shippingAddress;

    public Order(UUID id, Customer customer, Address shippingAddress, String currency) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
        this.totalPrice = new Money(BigDecimal.ZERO, currency); // Початкова сума 0
    }

    public void addItem(OrderItemDetails item) {
        if (this.status != OrderStatus.NEW) {
            throw new IllegalStateException("Додавати товари можна лише у нове замовлення");
        }
        this.items.add(item);
        recalculateTotalPrice();
    }

    private void recalculateTotalPrice() {
        if (items.isEmpty()) return;
        Money total = new Money(BigDecimal.ZERO, items.get(0).price().currency());
        for (OrderItemDetails item : items) {
            total = total.add(item.getTotalPrice());
        }
        this.totalPrice = total;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (!this.status.canChangeTo(newStatus)) {
            throw new IllegalStateException("Недопустимий перехід статусу з " + this.status + " на " + newStatus);
        }
        this.status = newStatus;
    }

    public void changeShippingAddress(Address newAddress) {
        if (this.status == OrderStatus.SHIPPED || this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Не можна змінити адресу для вже відправленого замовлення");
        }
        this.shippingAddress = newAddress;
    }

    public UUID getId() { return id; }
    public OrderStatus getStatus() { return status; }
    public Money getTotalPrice() { return totalPrice; }
    public Address getShippingAddress() { return shippingAddress; }
    public List<OrderItemDetails> getItems() { return new ArrayList<>(items); }
}