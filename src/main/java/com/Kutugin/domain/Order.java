package com.Kutugin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private static List<Long> listId = new ArrayList<Long>();
    private List<Product> productList = new ArrayList<>();
    private Date date;


    public Order() {
        generateId();
        date = new Date();
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

    private void generateId() {
        Random random = new Random();
        while (true) {
            long temp = random.nextLong();
            if (!listId.contains(temp) & temp > 0) {
                id = temp;
                listId.add(temp);
                break;
            }
        }
    }

    public void update(Order order) {
        this.productList = order.getProductList();
        date = new Date();
    }
}
