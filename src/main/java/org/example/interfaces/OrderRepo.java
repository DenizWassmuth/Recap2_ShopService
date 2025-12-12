package org.example.interfaces;

import org.example.classes.ProductRepo;
import org.example.records.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public interface OrderRepo {

    public void addSingle(Order order);

    public void removeSingleById(String id);

    public Order getSingleById(String id);

    public void addMulti(Order[] order);

    public void removeMulti(String[] orderIds);

    public ArrayList<Order> getAll();


    public void printSingleById(String id);

    public void  printAll();
}
