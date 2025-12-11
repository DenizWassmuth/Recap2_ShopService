package org.example.records;

import java.math.BigDecimal;

public record Product(int productId, String type, String manufacturer, String model, BigDecimal price, int quantity) {
}
