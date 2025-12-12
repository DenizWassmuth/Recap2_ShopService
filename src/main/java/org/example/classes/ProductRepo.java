package org.example.classes;


import org.example.records.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

public class ProductRepo {

    Map<String, Product> products = new HashMap<>();

    public void addSingle(Product product) {

        if (product == null) {
            return;
        }

        if (products.containsKey(product.productId())){
            return;
        }

        products.put(product.productId(), product);
    }

    public void removeSingle(Product product) {
        if (products.isEmpty()){
            return;
        }

        products.remove(product.productId());
    }

    void updateSingle(Product product) {

        if (product == null) {
            return;
        }

        if (products.containsKey(product.productId())) {
            products.put(product.productId(), product);
            return;
        }

        System.out.println("Product to update not found!");
    }

    public Product getSingle(int id) {
        return products.get(id);
    }

    public Map<String, Product> getAll() {
        return products;
    }

    public void printSingle(int id) {
        if (products.isEmpty()){
            return;
        }
        if (!products.containsKey(id)){
            return;
        }
        System.out.println("Product: " + products.get(id));
    }

    public void  printAll() {
        if (products.isEmpty()){
            return;
        }
        for (Product product: products.values()) {
            System.out.println(product);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "ProductRepo{" +
                "products=" + products +
                '}';
    }
}
