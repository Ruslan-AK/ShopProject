package com.Kutugin.dao;

import com.Kutugin.domain.Product;

import java.util.List;

public interface ProductDao {
    boolean saveProduct(Product product);

    List<Product> getProducts();

    void deleteById(long id);

    Product getById(long id);

    boolean contains(long id);

    void updateProduct(long id, int paramNumber,String item);
}
