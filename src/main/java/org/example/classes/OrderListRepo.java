package org.example.classes;


import org.example.records.Order;

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

public class OrderListRepo {

    Map<Integer, Order> orders = new HashMap<>();

    void addSingle(Order order) {
        if (orders.containsKey(order.orderId())){
            return;
        }

        orders.put(order.orderId(), order);
    }

    void removeSingle(Order order) {
        if (orders.isEmpty()){
            return;
        }

        orders.remove(order.orderId());
    }

    Order getSingle(int id) {
        return orders.get(id);
    }

    Map<Integer, Order> getAll() {
        return orders;
    }

    void printSingle(int id) {
        if (orders.isEmpty()){
            return;
        }
        if (!orders.containsKey(id)){
            return;
        }
        System.out.println("Order: " + orders.get(id));
    }

    void  printAll() {
        if (orders.isEmpty()){
            return;
        }
        for (Order order: orders.values()) {
            System.out.println(order);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orders=" + orders +
                '}';
    }
}
