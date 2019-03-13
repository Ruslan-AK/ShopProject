package com.Kutugin.dao.impl.noDB;

import com.Kutugin.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private static List<Product> products = new ArrayList<>();

    public ProductDB() {
        fillDB();
    }

    private void fillDB() {
        products.add(new Product("ASUS", "k53sd", 499.9, "LAPTOP"));
        products.add(new Product("Lenovo", "I508", 429.9, "LAPTOP"));
        products.add(new Product("Samsung", "Smart TV x8", 389.9, "TV"));
        products.add(new Product("Kivi", "Z90", 340, "TV"));
        products.add(new Product("ASUS", "allInOnePC", 450, "PC"));
        products.add(new Product("Sony", "W500", 110, "MP3_PLAYER"));
        products.add(new Product("Samsung", "J5 2016", 180, "SMARTPHONE"));
        products.add(new Product("Xiaomi", "Redmi Note 5", 210, "SMARTPHONE"));
        products.add(new Product("Toshiba", "k45 ", 80, "MP3_PLAYER"));
        products.add(new Product("Apple", "Iphone 7", 460, "SMARTPHONE"));
    }

    public List<Product> getProducts() {
        return products;
    }
}
