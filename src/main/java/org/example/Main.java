package org.example;

import org.example.classes.OrderListRepo;
import org.example.classes.ProductRepo;
import org.example.classes.ShopService;
import org.example.interfaces.OrderRepo;
import org.example.records.Product;
import org.example.utils.UtilityLibrary;

import java.math.BigDecimal;

public class Main {
    static void main() {

        Product mouse1 = new Product(UtilityLibrary.getRandomString(), "mouse", "corsair", "cx-2", new BigDecimal("44.99"), 10);
        Product mouse2 = new Product(UtilityLibrary.getRandomString(), "mouse", "logitech", "lx-8", new BigDecimal("34.99"), 10);
        Product mouse3 = new Product(UtilityLibrary.getRandomString(), "mouse", "razer", "rx-52", new BigDecimal("55.99"), 10);
        Product mouse4 = new Product(UtilityLibrary.getRandomString(), "mouse", "apple", "ax-91", new BigDecimal("99.99"), 10);

        ProductRepo productRepo = new ProductRepo();
        productRepo.addSingle(mouse1);
        productRepo.addSingle(mouse2);
        productRepo.addSingle(mouse3);
        productRepo.addSingle(mouse4);

        OrderListRepo orderListRepo = new OrderListRepo();

        ShopService shopservice = new ShopService(productRepo, orderListRepo);

        productRepo.printAll();

    }
}
