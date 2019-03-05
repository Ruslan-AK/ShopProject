package com.Kutugin.services;

import com.Kutugin.domain.Product;

import java.util.List;

public interface ProductServise {

    boolean saveProduct(Product product);

    List<Product> getProducts();

    void deleteById(long id);//for Client

    boolean contains(long id);

    void updateProduct(long id, int paramNumber, String item);

    Product getById(long id);
}
