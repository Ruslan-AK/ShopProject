package com.Kutugin.services.impl;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.dao.impl.OrderDaoImpl;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public BigDecimal summaryPrice(long id) {
        BigDecimal sum = new BigDecimal(0);
        for (Product product:orderDao.getById(id).getProductList()){
            sum.add(product.getPrice());
        }
        return sum;
    }

    @Override
    public long add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public void update(long id,Order order) {
        orderDao.update(id,order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();//
    }

    @Override
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public boolean containsById(long id) {
        return orderDao.containsById(id);
    }
}
