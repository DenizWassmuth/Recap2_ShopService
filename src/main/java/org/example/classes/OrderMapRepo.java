package org.example.classes;


import org.example.interfaces.OrderRepo;
import org.example.records.Order;

import java.util.ArrayList;
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

public class OrderMapRepo implements OrderRepo {


    Map<String, Order> orders = new HashMap<>();

    @Override
    public void addSingle(Order order) {

        if (order == null){
            return;
        }

        if (orders.containsKey(order.orderId())){
            return;
        }

        orders.put(order.orderId(), order);
    }
    @Override
    public void removeSingleById(String orderId) {

        if (orders.isEmpty()){
            return;
        }

        orders.remove(orderId);
    }

    @Override
    public Order getSingleById(String id) {

        if (orders.isEmpty()){
            return null;
        }

        return orders.get(id);
    }

    @Override
    public void addMulti(Order[] orders) {

        if (orders == null){
            return;
        }

        for (Order order : orders) {
            addSingle(order);
        }

    }

    @Override
    public void removeMulti(String[] orderIds) {

        if(orders.isEmpty()){
            return;
        }

        for (String orderId : orderIds) {
            removeSingleById(orderId);
        }
    }

    @Override
    public ArrayList<Order> getAll() {
        return null;
    }


    @Override
    public void printSingleById(String id) {

        if (orders.isEmpty()){
            return;
        }

        if (!orders.containsKey(id)){
            return;
        }

        System.out.println(orders.get(id));
    }

    @Override
    public void  printAll() {

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
