package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDao implements ProductDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASS = "";
    private List<Product> productList;

    public ProductDBDao() {
        init();
    }

    private void init() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't load driver");
        }
        productList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT;");
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String firm = resultSet.getString("FIRM");
                String model = resultSet.getString("MODEL");
                String type = resultSet.getString("TYPE");
                double price = resultSet.getDouble("PRICE");
                productList.add(new Product(firm, model, price, type, id));
            }
        } catch (SQLException e) {
            System.out.println("Error loading products");
        }
    }

    @Override
    public boolean saveProduct(Product product) {
        productList.add(product);
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT(ID,FIRM,MODEL,TYPE,PRICE) VALUES(?,?,?,?,?)")) {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getFirm());
            statement.setString(3, product.getModel());
            statement.setString(4, product.getType());
            statement.setDouble(5, product.getPrice());
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error saving product");
        }
        return false;
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM PRODUCT WHERE ID = '" + id + "';");
            productList.remove(getByID(id));
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    @Override
    public Product getByID(long id) {
        for (Product p : productList) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public boolean contains(long id) {
        for (Product p : productList) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void updateProduct(long id, int paramNumber, String value) {
        for (Product p : productList) {
            if (p.getId() == id) {
                switch (paramNumber) {
                    case 1:
                        p.setFirm(value);
                        break;
                    case 2:
                        p.setModel(value);
                        break;
                    case 3:
                        p.setType(value);
                        break;
                    case 4:
                        p.setPrice(Double.valueOf(value));
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
                try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                     PreparedStatement statement = connection.prepareStatement("update product set " +
                             "firm='" + p.getFirm() + "'," +
                             "model='" + p.getModel() + "'," +
                             "type='" + p.getType() + "'," +
                             "price='" + p.getPrice() + "' " +
                             "where id = " + id + ";")) {
                    statement.execute();
                } catch (SQLException ignored) {
                    System.out.println("Error updating product");
                }
            }
        }
    }
}
