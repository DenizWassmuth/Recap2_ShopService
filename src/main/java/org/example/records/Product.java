package org.example.records;

import java.math.BigDecimal;

public record Product(String productId, String type, String manufacturer, String model, BigDecimal price, int quantity) {
}
