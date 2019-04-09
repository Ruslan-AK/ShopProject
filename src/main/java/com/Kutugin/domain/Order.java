package com.Kutugin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "CLIENT_ID")
    long clientId;

    @JoinTable(
            name = "ORDER_PRODUCT",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = {@JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )}
    )
    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Product> products;

    public Order() {
        date = new Date().toString();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order ID=" + id + "\nCreated: " + date +
                "\nProductList:\n" + (showProducts().equals("") ? "No products yet" : showProducts());
    }

    private String showProducts() {
        String t = "";
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        for (Product p : products) {
            if (productIntegerMap.get(p) == null) {
                productIntegerMap.put(p, 1);
            } else {
                productIntegerMap.put(p, productIntegerMap.get(p) + 1);
            }
        }
        for (Product p : productIntegerMap.keySet()) {
            t += p.toString();
            t += " Amount: " + productIntegerMap.get(p);
            t += "\n";
        }
        return t;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProductsList() {
        return products;
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        setDate(new Date().toString());
    }
}
