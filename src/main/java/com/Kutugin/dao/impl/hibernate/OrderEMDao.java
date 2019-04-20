package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderEMDao implements OrderDao {
    @PersistenceContext(unitName = "factory")
    private EntityManager entityManager;

    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void addClientOrder(Client client, Order order) {
        order.setClientId(client.getId());
        addOrder(order);
    }

    @Override
    public void update(long id, Order order) {
        Order order1 = entityManager.find(Order.class, id);
        if (order.getDate() != null) order1.setDate(order.getDate());
        if (order.getClientId() != -1) order1.setClientId(order.getClientId());
        if (order.getProductsList() != null) order1.setProducts(order.getProductsList());
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(entityManager.find(Order.class, id));
    }

    @Override
    public Order getByID(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.clientId=:clientId");
        query.setParameter("clientId", clientId);
        List<Order> list = null;
        try {
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error getOrdersByClient");
        }
        return list;
    }

    @Override
    public boolean isPresent(long id) {
        return entityManager.contains(entityManager.find(Order.class, id));
    }
}
