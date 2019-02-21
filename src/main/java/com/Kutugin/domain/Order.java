package com.Kutugin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private static List<Long> listId = new ArrayList<Long>();
    private List<Product> productList = new ArrayList<>();

    public Order(Product product) {
        productList.add(product);
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "№=" + id +
                ", productList:\n" + showProducts() +
                '}';
    }

    private String showProducts() {
        String t = null;
        for(Product p:productList){
           t+=p.toString();
           t+="\n";
        }
        return t;

    }

    public long getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    private void generateId(){
        Random random = new Random();
        while (true){
            long temp = random.nextLong();
            if(!listId.contains(temp)) {
                id = temp;
                listId.add(temp);
                break;
            }
        }
    }
}
