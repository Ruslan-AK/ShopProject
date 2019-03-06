package com.Kutugin.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String model;
    private String firm;
    private BigDecimal price;
    private long id;
    private static long currentId = 0;
    private ProductType type;
    private static List<Long> listId = new ArrayList<>();

    public Double getPrice() {
        return price.doubleValue();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public void setId(long id) {
        this.id = id;
    }

    public static List<Long> getListId() {
        return listId;
    }

    public static void setListId(List<Long> listId) {
        Product.listId = listId;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String type) {
        this.type = ProductType.valueOf(type);
    }

    public Product(String firm, String model, double price, String type) {
        this.firm = firm;
        this.model = model;
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        this.type = ProductType.valueOf(type);
        id = currentId++;
    }

    public Product(String firm, String model, double price, String type, long id) {
        this.firm = firm;
        this.model = model;
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        this.type = ProductType.valueOf(type);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "firm='" + firm + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    public long getId() {
        return id;
    }
}
