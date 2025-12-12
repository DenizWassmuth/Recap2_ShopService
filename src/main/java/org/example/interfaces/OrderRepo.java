package org.example.interfaces;

import org.example.records.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public interface OrderRepo {

    public void addSingle(Order order);

    public void removeSingleById(int orderId);

    public Order getSingleById(int id);

    public void addMulti(Order[] order);

    public void removeMulti(int[] orderIds);

    public ArrayList<Order> getAll();

    public void printSingleById(int id);

    public void  printAll();
}
