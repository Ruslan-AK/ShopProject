package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductEMDao implements ProductDao {
    @PersistenceContext(unitName = "factory")
    private EntityManager entityManager;

    @Override
    public long saveProduct(Product product) {
        entityManager.persist(product);
        return product.getId();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> resultList = entityManager.createQuery("Select p from Product p").getResultList();//hql
        return resultList;
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(getByID(id));
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
        Product product1 = entityManager.find(Product.class, id);
        if (product.getType() != null) product1.setType(product.getType());
        if (product.getModel() != null) product1.setModel(product.getModel());
        if (product.getPrice() != null) product1.setPrice(product.getPrice());
        if (product.getFirm() != null) product1.setFirm(product.getFirm());
    }
}