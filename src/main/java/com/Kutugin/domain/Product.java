package com.Kutugin.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Product {
    private String name;
    private BigDecimal price;
    private long id;
    private static List<Long> listId = new ArrayList<>();
    private ProductType type;

    public Product(String name, BigDecimal price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Product(String name, double price, ProductType type) {
        this.name = name;
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    public long getId() {
        return id;
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
