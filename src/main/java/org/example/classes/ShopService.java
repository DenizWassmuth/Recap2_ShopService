package org.example.classes;


import org.example.interfaces.OrderRepo;
import org.example.records.Order;
import org.example.records.Product;
import org.example.utils.UtilityLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Author: Deniz Wassmuth
 * <p>
 * KEEP IN MIND:
 * - check and/or change user and email through GitBash
 * - add /target, *.iml and .idea to gitignore
 * - exclude previously added files from git changes through GitBash (git rm -r) and readd the files needed (git add .)
 * -> maven mostly handles this automatically after changing gitignore
 * <p>
 * add dependency: junit-jupiter
 * <p>
 * add plugin: maven-surefire
 * <p>
 * in GITHUB add Action: ... with maven
 *
 */

public class ShopService {

    ProductRepo productRepo;
    OrderRepo orderRepo;
    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {

        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void makeOrderBy(List<String> productIds, int quantity) {

        if (orderRepo == null) {
            System.out.println("OrderRepo is not initialized");
            return;
        }

        if(productRepo == null) {
            System.out.println("ProductRepo is not initialized");
            return;
        }

        if (productIds.isEmpty()) {
            System.out.println("OrderIds are invalid");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Order quantity needs to be > 0");
            return;
        }

        List<Product> orderedProducts = new ArrayList<>();

        for (Product product : productRepo.products.values()) {
            for (String productId : productIds) {

                if (!product.productId().equals(productId)) {
                    continue;
                }

                if (product.quantity() <= 0) {
                    System.out.println("Product " + product + " is out of Stock.");
                    return;
                }

                if (product.quantity() < quantity) {
                    System.out.println(product + " only " + product.quantity() + " left. Try ordering less");
                } else {
                    orderedProducts.add(product);
                }

                break;
            }
        }

        if (orderedProducts.isEmpty()) {
            System.out.println("No products ordered");
            return;
        }

        Order newOrder = new Order(UtilityLibrary.getRandomString(), orderedProducts);
        orderRepo.addSingle(newOrder);

        System.out.println("Product with Id: " + productIds + " does not exist.");
    }
}
