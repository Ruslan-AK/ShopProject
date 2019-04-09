package com.Kutugin.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "FIRM")
    private String firm;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "TYPE")
    private ProductType type;

    public Product() {
    }

    public Product(String firm, String model, double price, String type) {
        this.firm = firm;
        this.model = model;
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        this.type = ProductType.valueOf(type);
    }

    public Product(String firm, String model, double price, String type, long id) {
        this.firm = firm;
        this.model = model;
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        this.type = ProductType.valueOf(type);
        this.id = id;
    }

    public Double getPrice() {
        if (price != null)
            return price.doubleValue();
        return null;
    }

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        if (type != null)
            return type.toString();
        return null;
    }

    public void setType(String type) {
        this.type = ProductType.valueOf(type);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", firm='" + firm + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return model.equals(product.model) &&
                firm.equals(product.firm) &&
                price.equals(product.price) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, firm, price, type);
    }
}
