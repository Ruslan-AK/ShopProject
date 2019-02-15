package com.Kutugin.dao.impl;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static List<Product> products = new ArrayList<>();

    @Override
    public boolean saveProduct(Product product) {
        if(!products.contains(product)){
            products.add(product);
            System.out.println(product+" saved");
            return true;
        }
        System.out.println(product+" already saved");
        return false;
    }

    public static List<Product> getProducts() {
        return products;
    }
}
