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

        if(productRepo == null) {
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

            // TODO: make productId lenght private and final and add getter
            boolean bContinue = productId.length() < 5 || !productRepo.isValidEntry(productId);
            if (bContinue) {
                System.out.println();
                System.out.println("Invalid entry");
                continue;
            }

            Product product = productRepo.getSingle(productId);
            orderedProducts.add(product);
            System.out.println(product + " added to List.");
            bCancel = true;
            break;
        }

        for (String productId : productIds) {

            if (productRepo.productIsOutOfStock(productId)) {
                continue;
            }

            Product product = productRepo.getSingle(productId);

            //TODO: add input to choose from quantity -> use while loop
            int orderedQuantity = 0;

            while (true) {

                System.out.println("Please enter the quantity you would like to order for " + product.model());

                String quantityInput = sc.next().trim().toLowerCase();

                if (!Character.isDigit(quantityInput.charAt(0))) {
                    System.out.println("Wrong input! Quantity must be a digit. Please try again.");
                    continue;
                }

                orderedQuantity = Integer.parseInt(quantityInput);

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

            Product orderedProduct = new Product(product.productId(), product.type(), product.manufacturer(), product.model(), product.price(), orderedQuantity);
            orderedProducts.add(orderedProduct);
            totalPrice = totalPrice.add(orderedProduct.price().multiply(BigDecimal.valueOf(orderedQuantity)));

            int newRepoQuantity = product.quantity() - orderedQuantity;
            productRepo.updateSingle(product.withQuantity(newRepoQuantity));
        }

        if (orderedProducts.isEmpty()) {
            System.out.println("No products ordered");
            return;
        }

        Order newOrder = new Order(UtilityLibrary.getRandomString(), orderedProducts, totalPrice);
        orderRepo.addSingle(newOrder);
    }
}
