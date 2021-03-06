package com.Kutugin.services.impl;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public long saveProduct(Product product) {
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
    public boolean isPresent(long id) {
        return productDao.isPresent(id);
    }

    @Override
    public void updateProduct(long id, Product product) {
        productDao.updateProduct(id, product);
    }

    @Override
    public Product getByID(long id) {
        return productDao.getByID(id);
    }
}
