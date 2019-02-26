package com.Kutugin.dao;

import com.Kutugin.domain.Order;

import java.util.List;

public interface OrderDao {

    long add(Order order);

    void update(long id, Order order);

    List<Order> getOrders();

    void deleteById(long id);

    Order getById(long id);

    boolean containsById(long id);
}
