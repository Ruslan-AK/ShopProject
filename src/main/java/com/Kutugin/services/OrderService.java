package com.Kutugin.services;

import com.Kutugin.domain.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    BigDecimal summaryPrice(long id);

    long add(Order order);

    void update(long id, Order order);

    List<Order> getOrders();

    void deleteById(long id);

    Order getById(long id);

    boolean containsById(long id);
}
