package com.Kutugin.dao;

import com.Kutugin.domain.Product;
import com.Kutugin.domain.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    boolean saveProduct(Product product);
    List<Product> getProducts();
    void deleteById(long id);
    Product getById(long id);
    boolean contains(long id);
    void updateProduct(long id, Product product);
}
