package com.Kutugin.services.impl;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public BigDecimal summaryPrice(Order order) {
        BigDecimal sum = new BigDecimal(0);
        for (Product product : order.getProductList()) {
            sum = sum.add(BigDecimal.valueOf(product.getPrice()));
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

    @Override
    public boolean isPresent(long id) {
        return orderDao.isPresent(id);
    }

    @Override
    public long getNextByMaxID() {
        return orderDao.getNextByMaxID();
    }
}
