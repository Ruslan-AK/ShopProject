package com.Kutugin.dao.impl.db;

import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private static List<Product> products = new ArrayList<>();

    public ProductDB() {
        fillDB();
    }

    private void fillDB() {
        products.add(new Product("ASUS k53sd", 499.9, ProductType.LAPTOP));
        products.add(new Product("Lenovo I508", 429.9, ProductType.LAPTOP));
        products.add(new Product("Samsung Smart TV x8", 389.9, ProductType.TV));
        products.add(new Product("Kivi Z90", 340, ProductType.TV));
        products.add(new Product("ASUS allInOnePC", 450, ProductType.PC));
        products.add(new Product("Sony W500", 110, ProductType.MP3_PLAYER));
        products.add(new Product("Samsung J5 2016", 180, ProductType.SMARTPHONE));
        products.add(new Product("Xiaomi Redmi Note 5", 210, ProductType.SMARTPHONE));
        products.add(new Product("Toshiba k45 ", 80, ProductType.MP3_PLAYER));
        products.add(new Product("Iphone 7", 460, ProductType.SMARTPHONE));
    }

    public List<Product> getProducts() {
        return products;
    }
}