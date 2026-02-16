package com.ddd.shop.domain;

import com.ddd.shop.domain.entities.Customer;
import com.ddd.shop.domain.entities.Order;
import com.ddd.shop.domain.entities.Product;
import com.ddd.shop.domain.enums.OrderStatus;
import com.ddd.shop.domain.valueobjects.*;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Name customerName = new Name("Олександр", "Верещагін");
        Email customerEmail = new Email("sasha@gmail.com");
        Address customerAddress = new Address("Україна", "Київ", "Хрещатик 1", "01001");

        Customer customer = new Customer(UUID.randomUUID(), customerName, customerEmail, customerAddress);
        System.out.println("Клієнт: " + customer.getName().getFullName());
        System.out.println("Адреса: " + customer.getAddress().getFormattedAddress() + "\n");

        Dimensions laptopDimensions = new Dimensions(35.5, 25.0, 2.0);
        ProductDetails laptopDetails = new ProductDetails("Ноутбук", "Потужний ігровий ноутбук для програмування", laptopDimensions);
        Money laptopPrice = new Money(new BigDecimal("1500.00"), "USD");
        Stock laptopStock = new Stock(10);

        Product laptop = new Product(UUID.randomUUID(), laptopDetails, laptopPrice, laptopStock);
        System.out.println("Товар: " + laptop.getDetails().getShortDescription());
        System.out.println("Ціна: " + laptop.getPrice().formatted());
        System.out.println("На складі: " + laptop.getStock().quantity() + " шт.\n");

        Order order = new Order(UUID.randomUUID(), customer, customer.getAddress(), "USD");

        int quantityToBuy = 2;
        System.out.println("--- Спроба купити " + quantityToBuy + " шт. ---");

        if (laptop.hasEnoughStock(quantityToBuy)) {
            laptop.decreaseStock(quantityToBuy);

            OrderItemDetails item = new OrderItemDetails(laptop.getId(), quantityToBuy, laptop.getPrice());
            order.addItem(item);

            customer.addOrder(order);

            System.out.println("Успіх! Товар додано у замовлення.");
            System.out.println("Новий залишок на складі: " + laptop.getStock().quantity() + " шт.\n");
        } else {
            System.out.println("Помилка: Недостатньо товару на складі.\n");
        }

        System.out.println("Загальна сума замовлення: " + order.getTotalPrice().formatted());
        System.out.println("Поточний статус замовлення: " + order.getStatus());

        order.changeStatus(OrderStatus.CONFIRMED);
        System.out.println("Статус після підтвердження: " + order.getStatus());

        System.out.println("Чи має клієнт активні замовлення? " + customer.hasActiveOrders());
    }
}