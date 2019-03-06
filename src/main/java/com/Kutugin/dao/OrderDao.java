package com.Kutugin.dao;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;

import java.util.List;

public interface OrderDao {

    long add(Client client,Order order);

    void update(long id, Order order);

    List<Order> getOrders();

    void deleteById(long id);

    Order getByID(long id);

    public List<Order> getOrdersByClient(long clientId);

    public void addProduct(long id, Product product);

    boolean containsById(long id);

    long getMaxID();
}
