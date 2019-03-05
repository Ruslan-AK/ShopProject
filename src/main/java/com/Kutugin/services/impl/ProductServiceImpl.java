package com.Kutugin.services.impl;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ProductServise;

import java.util.List;

public class ProductServiceImpl implements ProductServise {

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

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
    public void updateProduct(long id, int paramNumber, String item) {
        productDao.updateProduct(id, paramNumber, item);
    }

    @Override
    public Product getById(long id) {
        return productDao.getById(id);
    }
}
