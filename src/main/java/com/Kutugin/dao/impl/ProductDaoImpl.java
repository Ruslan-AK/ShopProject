package com.Kutugin.dao.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.db.ProductDB;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Product;
import com.Kutugin.domain.Products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private Map<Long, Product> map = new HashMap<>();
    private static long generator = 0;
    private static ProductDao productDao = new ProductDaoImpl();
    private ProductDB db = new ProductDB();
    private List<Product> products;

    private ProductDaoImpl() {
        products = db.getProducts();
    }

    public static ProductDao getInstance(){return   productDao;}

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

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void deleteById(long id) {
        map.remove(id);
    }

    @Override
    public Product getById(long id) {
        return map.get(id);
    }

    @Override
    public boolean contains(long id) {
        return map.containsKey(id);
    }

    @Override
    public void updateProduct(long id, Product product) {
        map.remove(id);
        map.put(id,product);
    }

}
