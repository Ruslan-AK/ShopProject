package com.Kutugin.dao;

import com.Kutugin.domain.Product;

import java.util.List;

public interface ProductDao {
    boolean saveProduct(Product product);

    List<Product> getProducts();

    void deleteById(long id);

    Product getByID(long id);

    boolean isPresent(long id);

    void updateProduct(long id, Product product);

    long getNextByMaxID();
}
