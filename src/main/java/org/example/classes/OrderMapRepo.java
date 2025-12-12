package org.example.classes;


import org.example.interfaces.OrderRepo;
import org.example.records.Order;

import java.util.Map;

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

    @Override
    public void addSingle(Order order) {

    }

    @Override
    public void removeSingleById(int orderId) {

    }

    @Override
    public Order getSingleById(int id) {
        return null;
    }

    @Override
    public void addMulti(Order[] order) {

    }

    @Override
    public void removeMulti(int[] orderIds) {

    }

    @Override
    public Map<Integer, Order> getAll() {
        return Map.of();
    }

    @Override
    public void printSingleById(int id) {

    }

    @Override
    public void printAll() {

    }
}
