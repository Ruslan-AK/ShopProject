package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class OrderEMDao implements OrderDao {
    private EntityManager entityManager;

    @Autowired
    public OrderEMDao(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    public void addOrder(Order order) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void addClientOrder(Client client, Order order) {
        order.setClientId(client.getId());
        addOrder(order);
    }

    @Override
    public void update(long id, Order order) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Order order1 = entityManager.find(Order.class, id);
        if (order.getDate() != null) order1.setDate(order.getDate());
        if (order.getClientId() != -1) order1.setClientId(order.getClientId());
        if (order.getProductsList() != null) order1.setProducts(order.getProductsList());
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(long id) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(entityManager.find(Order.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public Order getByID(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
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
