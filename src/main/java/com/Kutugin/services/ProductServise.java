package com.Kutugin.services;

import com.Kutugin.domain.Product;

import java.util.List;

public interface ProductServise {

    boolean saveProduct(Product product);

    List<Product> getProducts();

    void deleteById(long id);//

    boolean isInDB(long id);

    void updateProduct(long id, Product product);

    Product getByID(long id);
}
