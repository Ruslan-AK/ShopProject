package com.Kutugin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private static long currentId = 0;
    private List<Product> productList = new ArrayList<>();
    private String date;


    public Order(long id) {
        currentId = id+1;
        this.id = currentId++;
        date = new Date().toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Order(long id, List<Product> productCartList, String date) {
        this.id = id;
        productList = productCartList;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order №=" + id + "\nCreated: " + date +
                "\nProductList:\n" + (showProducts().equals("") ? "No products yet" : showProducts());
    }

    private String showProducts() {
        String t = "";
        for (Product p : productList) {
            t += p.toString();
            if (!(productList.indexOf(p) + 1 == productList.size()))
                t += "\n";
        }
        return t;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void update(Order order) {
        this.productList = order.getProductList();
        date = new Date().toString();
    }
}
