package org.example.records;

import java.math.BigDecimal;

public record Product(String productId, String type, String manufacturer, String model, BigDecimal price, int quantity) {

    public Product withQuantity(int quantity) {
        return new Product(productId, type, manufacturer, model, price, quantity);
    }
}
