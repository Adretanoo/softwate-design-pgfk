package com.ddd.shop.domain.valueobjects;

public record ProductDetails(String name, String description, Dimensions dimensions) {

    public ProductDetails {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою");
        }
    }
    public String getShortDescription() {
        String safeDesc = (description == null) ? "" : description;
        if (safeDesc.length() > 50) {
            return name + " - " + safeDesc.substring(0, 47) + "...";
        }
        return name + " - " + safeDesc;
    }
}