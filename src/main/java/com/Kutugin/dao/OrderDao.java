package com.Kutugin.dao;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;

import java.util.List;

public interface OrderDao {

    void addClientOrder(Client client, Order order);

    void update(long id, Order order);

    void deleteById(long id);

    Order getByID(long id);

    List<Order> getOrdersByClient(long clientId);
    
    boolean isPresent(long id);

    long getNextByMaxID();
}
