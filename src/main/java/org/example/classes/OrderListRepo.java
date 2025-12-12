package org.example.classes;


import org.example.records.Order;

import java.util.ArrayList;
import java.util.Objects;
import org.example.interfaces.OrderRepo;

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

public class OrderListRepo implements OrderRepo {

    ArrayList<Order> orders;

    public OrderListRepo() {
        orders = new ArrayList<>();
    }

    @Override
    public void addSingle(Order order) {

        if (order == null){
            return;
        }

        if (orders.contains(order)){
            return;
        }

        orders.add(order);
    }
    @Override
    public void removeSingleById(int orderId) {

        if (orders.isEmpty()){
            return;
        }

        for (Order order : orders){
           if(order.orderId().equals(orderId)){
               orders.remove(order);
               break;
           }
        }

        //orders.removeIf(order -> order.orderId().equals(orderId));

        // TODO: ??
        //orders.removeIf(order -> order.orderId() == orderId);
    }

    @Override
    public Order getSingleById(int id) {

        if (orders.isEmpty()) {
            return null;
        }

        for (Order order : orders) {
            if (order.orderId() == id) {
                return order;
            }
        }

        return null;
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
    public void removeMulti(int[] orderIds) {

        if(orders.isEmpty()){
            return;
        }

        for (int orderId : orderIds) {
            removeSingleById(orderId);
        }
    }

    @Override
    public ArrayList<Order> getAll() {
        return orders;
    }


    @Override
    public void printSingleById(int id) {

        if (orders.isEmpty()){
            return;
        }

        for (Order order : orders){
            if (order.orderId() == id){
                System.out.println(order);
            }
        }

        System.out.println("Order with ID: " + id + " not found.");
    }

    @Override
    public void  printAll() {

        if (orders.isEmpty()){
            return;
        }

        for (Order order: orders) {
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
