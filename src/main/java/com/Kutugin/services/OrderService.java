package com.Kutugin.services;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    BigDecimal summaryPrice(long id);

    void addClientOrder(Client client, Order order);

    void update(long id, Order order);

    List<Order> getOrdersByClient(long clientId);

    void deleteById(long id);

    Order getByID(long id);

    //void addProduct(long id, Product product);

    boolean isInDBById(long id);

    long getNextByMaxID();
}
