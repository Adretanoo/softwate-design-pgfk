package com.ddd.shop.domain.entities;

import com.ddd.shop.domain.valueobjects.Address;
import com.ddd.shop.domain.valueobjects.Email;
import com.ddd.shop.domain.valueobjects.Name;
import com.ddd.shop.domain.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private Name name;
    private Email email;
    private Address address;
    private final List<Order> orders;

    public Customer(UUID id, Name name, Email email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public boolean hasActiveOrders() {
        for (Order order : orders) {
            if (order.getStatus() != OrderStatus.DELIVERED) {
                return true;
            }
        }
        return false;
    }

    public UUID getId() { return id; }
    public Name getName() { return name; }
    public Email getEmail() { return email; }
    public Address getAddress() { return address; }
    public List<Order> getOrders() { return new ArrayList<>(orders); }
}