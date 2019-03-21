package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.Kutugin.dao.impl.H2DB.InitDB.*;

public class ProductDBDao implements ProductDao {

    @Override
    public boolean saveProduct(Product product) {
        long id = getNextByMaxID();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT(ID,FIRM,MODEL,TYPE,PRICE) VALUES(?,?,?,?,?)")) {
            statement.setLong(1, id);

            statement.setString(2, product.getFirm());
            statement.setString(3, product.getModel());
            statement.setString(4, product.getType());
            statement.setDouble(5, product.getPrice());
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error saving product");
            System.out.println(e.getErrorCode());
            return false;
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
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
            System.out.println("Error deleteById");
            System.out.println(e.getErrorCode());
        }
        return productList;
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM PRODUCT WHERE ID = '" + id + "';");
        } catch (SQLException e) {
            System.out.println("Error deleteById");
            System.out.println(e.getSQLState());
        }
    }

    @Override
    public Product getByID(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firm = resultSet.getString("FIRM");
                String model = resultSet.getString("MODEL");
                double price = resultSet.getDouble("PRICE");
                String type = resultSet.getString("TYPE");
                return new Product(firm, model, price, type, id);
            } else System.out.println("Product not found in DB");
        } catch (SQLException e) {
            System.out.println("Error getID");
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    @Override
    public boolean isPresent(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error isPresent");
            System.out.println(e.getErrorCode());
        }
        return false;
    }

    @Override
    public void updateProduct(long id, Product product) {
        Product p = getByID(id);
        if (p == null){
            saveProduct(product);
            return;
        }
        if (product.getFirm() != null) {
            p.setFirm(product.getFirm());
        }
        if (product.getModel() != null) {
            p.setModel(product.getModel());
        }
        if (product.getPrice() > 0) {
            p.setPrice(product.getPrice());
        }
        if (product.getType() != null) {
            p.setType(product.getType());
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection
                     .prepareStatement("update product set firm=?, model=?, type=?, price=? where id = ?")) {
            statement.setString(1, p.getFirm());
            statement.setString(2, p.getModel());
            statement.setString(3, p.getType());
            statement.setDouble(4, p.getPrice());
            statement.setLong(5, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error updating product");
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public long getNextByMaxID() {
        long maxId = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement2 = connection.createStatement()) {
            ResultSet resultSet = statement2.executeQuery("SELECT MAX(ID) FROM PRODUCT;");
            resultSet.next();
            maxId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("Error getNextByMaxID");
            System.out.println(e.getErrorCode());
        }
        return maxId + 1;
    }
}
