package org.example.classes;


import org.example.interfaces.OrderRepo;
import org.example.records.Order;
import org.example.records.Product;
import org.example.utils.UtilityLibrary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {

        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void printAllProductsInProductRepo() {
        if (productRepo == null) {
            System.out.println("Product Repo is null");
            return;
        }

        productRepo.printAll();
    }

    public void printAllOrdersInOrderRepo() {
        if (orderRepo == null) {
            System.out.println("Order Repo is null");
            return;
        }

        orderRepo.printAll();
    }

    public void makeOrderById(List<String> productIds) {

        if (orderRepo == null) {
            System.out.println("OrderRepo is not initialized");
            return;
        }

        if (productRepo == null) {
            System.out.println("ProductRepo is not initialized");
            return;
        }

        if (productIds.isEmpty()) {
            System.out.println("OrderIds are invalid");
            return;
        }

        Scanner sc = new Scanner(System.in);

        List<Product> orderedProducts = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal("0");

        boolean bCancel = false;
        while (!bCancel) {
            System.out.println();
            System.out.println("Pls enter the product ID of the product you want to add to your order");

            String productId = sc.nextLine().trim().toLowerCase();

            // TODO: make productId length private and final and add getter
            boolean bContinue = productId.length() < 5 || !productRepo.isValidEntry(productId);
            if (bContinue) {
                System.out.println();
                System.out.println("Invalid entry");
                continue;
            }

            if (productRepo.productIsOutOfStock(productId)) {
                continue;
            }

            Product product = productRepo.getSingle(productId);

            // TODO:: trycatch ??
            int orderedQuantity = determineOrderQuantity(product, sc);
            if (orderedQuantity <= 0) {
                continue;
            }

            Product orderedProduct = new Product(product.productId(), product.type(), product.manufacturer(), product.model(), product.price(), orderedQuantity);
            orderedProducts.add(orderedProduct);
            totalPrice = totalPrice.add(orderedProduct.price().multiply(BigDecimal.valueOf(orderedQuantity)));

            Order newOrder = new Order(UtilityLibrary.getRandomString(), orderedProducts, totalPrice);
            orderRepo.addSingle(newOrder);

            int newRepoQuantity = product.quantity() - orderedQuantity;
            productRepo.updateSingle(product.withQuantity(newRepoQuantity));

            System.out.println(orderedProduct + " added to List.");

            // TODO: add logic to cancel, finish or add to order
            bCancel = true;
            break;
        }
    }

    int determineOrderQuantity(Product product, Scanner sc) {

        if(product == null || sc == null) {
            return 0;
        }

        int orderedQuantity = 0;

        while (true) {

            System.out.println("Please enter the quantity you would like to order for " + product.model());

            if(sc.hasNextInt()) {
                orderedQuantity = sc.nextInt();
            }
            else {
                System.out.println("Wrong input! Quantity must be a digit. Please try again.");
                sc.next();
                continue;
            }

            if (orderedQuantity <= 0) {
                System.out.println("Order quantity needs to be > 0");
                continue;
            }

            if (product.quantity() < orderedQuantity) {
                System.out.println(product + " only " + product.quantity() + " left. Try ordering less");
                continue;
            }

            break;
        }

        return orderedQuantity;
    }
}
