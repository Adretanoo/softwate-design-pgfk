package com.ddd.shop.domain.entities;

import com.ddd.shop.domain.valueobjects.Money;
import com.ddd.shop.domain.valueobjects.ProductDetails;
import com.ddd.shop.domain.valueobjects.Stock;

import java.util.UUID;

public class Product {
    private final UUID id;
    private ProductDetails details;
    private Money price;
    private Stock stock;

    public Product(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = id;
        this.details = details;
        this.price = price;
        this.stock = stock;
    }

    public boolean hasEnoughStock(int quantity) {
        return this.stock.quantity() >= quantity;
    }

    public void decreaseStock(int quantity) {
        if (!hasEnoughStock(quantity)) {
            throw new IllegalArgumentException("Недостатньо товару на складі");
        }
        this.stock = this.stock.decrease(quantity);
    }

    public void updatePrice(Money newPrice) {
        if (newPrice == null) throw new IllegalArgumentException("Ціна не може бути порожньою");
        this.price = newPrice;
    }

    public UUID getId() { return id; }
    public ProductDetails getDetails() { return details; }
    public Money getPrice() { return price; }
    public Stock getStock() { return stock; }
}