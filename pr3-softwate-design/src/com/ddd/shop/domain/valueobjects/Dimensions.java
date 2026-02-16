package com.ddd.shop.domain.valueobjects;

public record Dimensions(double length, double width, double height) {

    public Dimensions {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Габарити повинні бути більшими за 0");
        }
    }

    public double getVolume() {
        return length * width * height;
    }

    public boolean isWithinMaxDimensions(double maxLength, double maxWidth, double maxHeight) {
        return this.length <= maxLength && this.width <= maxWidth && this.height <= maxHeight;
    }
}