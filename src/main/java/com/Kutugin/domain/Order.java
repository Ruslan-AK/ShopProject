package com.Kutugin.domain;

import java.util.*;

public class Order {
    private long id;
    private String date;

    public Map<Product, Long> getProductMap() {
        return productMap;
    }

    private Map<Product, Long> productMap = new HashMap<>();

    public Order(long id) {
        this.id = id;
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
        for (Product p:productCartList) {
            Long pos = productMap.get(p);
            productMap.put(p, pos == null ? 1 : pos + 1);
        }
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order №=" + id + "\nCreated: " + date +
                "\nProductList:\n" + (showProducts().equals("") ? "No products yet" : showProducts());
    }

    private String showProducts() {
        String t = "";
        for (Product p : productMap.keySet()) {
            t += p.toString();
            t += "\nAmount: ";
            t += productMap.get(p);
            t += "\n";
        }
        return t;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        for(Product p:productMap.keySet()){
            for (int i=0;i<productMap.get(p);i++){
                productList.add(p);
            }
        }
        return productList;
    }

    public void addProduct(Product product) {
        Long pos = productMap.get(product);
        productMap.put(product, pos == null ? 1 : pos + 1);
    }

    public void update(Order order) {
        for (Product p:order.getProductList()){
            addProduct(p);
        }
        date = new Date().toString();
    }
}
