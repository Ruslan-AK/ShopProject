package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class ProductEMDao implements ProductDao {

    private EntityManager entityManager;

    @Autowired
    public ProductEMDao(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long saveProduct(Product product) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.persist(product);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return product.getId();
    }

    @Override
    public List<Product> getProducts() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        List<Product> resultList = entityManager.createQuery("Select p from Product p").getResultList();//hql
        return resultList;
    }

    @Override
    public void deleteById(long id) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(getByID(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public Product getByID(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public boolean isPresent(long id) {
        Query query = entityManager.createQuery("SELECT 1 FROM Product p WHERE p.id=:id");
        query.setParameter("id", id);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public void updateProduct(long id, Product product) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Product product1 = entityManager.find(Product.class, id);
        if (product.getType() != null) product1.setType(product.getType());
        if (product.getModel() != null) product1.setModel(product.getModel());
        if (product.getPrice() != null) product1.setPrice(product.getPrice());
        if (product.getFirm() != null) product1.setFirm(product.getFirm());
        entityManager.getTransaction().commit();
    }
}