package com.Kutugin.services.impl;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public BigDecimal summaryPrice(long id) {
        BigDecimal sum = new BigDecimal(0);
        for (Product product : orderDao.getByID(id).getProductList()) {
            sum = sum.add(BigDecimal.valueOf(product.getPrice()));//because BigDecimal is immutable
        }
        return sum;
    }

    @Override
    public void addClientOrder(Client client, Order order) {
        orderDao.addClientOrder(client, order);
    }

    @Override
    public void update(long id, Order order) {
        orderDao.update(id, order);
    }

    @Override
    public void deleteById(long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order getByID(long id) {
        return orderDao.getByID(id);
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        return orderDao.getOrdersByClient(clientId);
    }

//    @Override
//    public void addProduct(long id, Product product) {
//        orderDao.addProduct(id, product);
//    }

    @Override
    public boolean isInDBById(long id) {
        return orderDao.isInDBById(id);
    }

    @Override
    public long getNextByMaxID() {
        return orderDao.getNextByMaxID();
    }
}
