package com.Kutugin.services.impl;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.ProductDaoImpl;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ProductServise;

import java.util.List;

public class ProductServiceImpl implements ProductServise {

    private ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public boolean saveProduct(Product product) {
        return productDao.saveProduct(product);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public void deleteById(long id) {
        productDao.deleteById(id);
    }

    @Override
    public boolean contains(long id) {
        return productDao.contains(id);
    }

    @Override
    public void updateProduct(long id, Product product) {
        productDao.updateProduct(id, product);
    }

    @Override
    public Product getById(long id) {
        return productDao.getById(id);
    }
}