package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Order;

import java.util.List;

public class OrderDBDao implements OrderDao {
    @Override
    public long add(Order order) {
        return 0;
    }

    @Override
    public void update(long id, Order order) {

    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Order getById(long id) {
        return null;
    }

    @Override
    public boolean containsById(long id) {
        return false;
    }
}
