package com.Kutugin.services;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    BigDecimal summaryPrice(long id);

    long add(Client client, Order order);

    void update(long id, Order order);

    List<Order> getOrders();

    public List<Order> getOrdersByClient(long clientId);

    void deleteById(long id);

    Order getByID(long id);

    public void addProduct(long id, Product product);

    boolean containsById(long id);

    long getMaxID();
}
