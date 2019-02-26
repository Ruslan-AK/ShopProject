package com.Kutugin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private static List<Long> listId = new ArrayList<Long>();
    private List<Product> productList = new ArrayList<>();
    //date and time of order

    public Order(Product product) {
        productList.add(product);
        generateId();
    }

    public Order() {
        generateId();
    }

    @Override
    public String toString() {
        return "Order №=" + id +
                "\nProductList:\n" + (showProducts().equals("")?"No products yet":showProducts());
    }

    private String showProducts() {
        String t="";
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
            if(!listId.contains(temp)&temp>0) {
                id = temp;
                listId.add(temp);
                break;
            }
        }
    }

    public void update(Order order) {
        this.productList = order.getProductList();
    }
}
