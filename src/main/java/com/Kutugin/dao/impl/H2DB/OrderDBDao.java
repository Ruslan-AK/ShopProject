package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDao implements OrderDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASS = "";
    private List<Order> orderList;

    public OrderDBDao() {
        getOrders();
    }

    @Override
    public long add(Client client,Order order) {
        for (Product p : order.getProductList()) {
            String queryCart = "INSERT INTO CART_ID(ORDER_ID,PRODUCT_ID) VALUES(" + order.getId() + ", " + p.getId() + ");";
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 PreparedStatement statement = connection.prepareStatement(queryCart)) {
                statement.execute();
            } catch (SQLException ignored) {
                System.out.println("Error adding orders product");
            }
        }
        String queryCart = "INSERT INTO \"ORDER\"(CLIENT_ID,DATE) VALUES(" + client.getId() + ", '" + order.getDate() + "');";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(queryCart)) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error adding order");
        }
        orderList.add(order);
        return 0;
    }

    @Override
    public void addProduct(long id,Product product){
        getByID(id).addProduct(product);
        String queryCart = "INSERT INTO CART_ID(ORDER_ID,PRODUCT_ID) VALUES(" + id + ", " + product.getId() + ");";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(queryCart)) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error adding product");
        }
        update(id,getByID(id));
    }


    @Override
    public void update(long id, Order order) {
        String query = "UPDATE \"ORDER\" SET DATE = '" + order.getDate() + "' WHERE ID = " + id + ";";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error updating order");
        }
        getByID(id).setDate(order.getDate());
    }

    @Override
    public List<Order> getOrders() {
        List<Long> cartList = new ArrayList<>();
        List<Product> productCartList = new ArrayList<>();
        if (orderList == null) {
            orderList = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM \"ORDER\";");
                while (resultSet.next()) {
                    String date = resultSet.getString("DATE");
                    long id = resultSet.getLong("ID");
                    {
                        ResultSet resultSetCart = statement.executeQuery("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =" + id + ";");
                        while (resultSetCart.next()) {
                            cartList.add(resultSetCart.getLong("PRODUCT_ID"));
                        }
                        ResultSet productResultSetCart = statement.executeQuery("SELECT * FROM \"PRODUCT\" WHERE ID IN (" + listToString(cartList) + ");");
                        while (productResultSetCart.next()) {
                            long idP = resultSet.getLong("ID");
                            String firm = resultSet.getString("FIRM");
                            String model = resultSet.getString("MODEL");
                            String type = resultSet.getString("TYPE");
                            double price = resultSet.getDouble("PRICE");
                            productCartList.add(new Product(firm, model, price, type, idP));
                        }
                    }
                    orderList.add(new Order(id, productCartList, date));
                }
                return orderList;
            } catch (SQLException e) {
                System.out.println("Order not found");
            }
        }
        return orderList;
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        List<Long> cartList = new ArrayList<>();
        List<Product> productCartList = new ArrayList<>();
        if (orderList == null) {
            orderList = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM \"ORDER\" WHERE CLIENT_ID ="+clientId+";");
                while (resultSet.next()) {
                    String date = resultSet.getString("DATE");
                    long id = resultSet.getLong("ID");
                    {
                        ResultSet resultSetCart = statement.executeQuery("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =" + id + ";");
                        while (resultSetCart.next()) {
                            cartList.add(resultSetCart.getLong("PRODUCT_ID"));
                        }
                        ResultSet productResultSetCart = statement.executeQuery("SELECT * FROM \"PRODUCT\" WHERE ID IN (" + listToString(cartList) + ");");
                        while (productResultSetCart.next()) {
                            long idP = resultSet.getLong("ID");
                            String firm = resultSet.getString("FIRM");
                            String model = resultSet.getString("MODEL");
                            String type = resultSet.getString("TYPE");
                            double price = resultSet.getDouble("PRICE");
                            productCartList.add(new Product(firm, model, price, type, idP));
                        }
                    }
                    orderList.add(new Order(id, productCartList, date));
                }
                return orderList;
            } catch (SQLException e) {
                System.out.println("Order not found");
            }
        }
        return orderList;
    }

    private String listToString(List<Long> input) {
        String s = "";
        int count = 0;
        for (Long l : input) {
            s += l;
            if (!(count++ == input.size())) {
                s += ",";
            }
        }
        return s;
    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM \"ORDER\" WHERE ID = " + id + ";";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error updating order");
        }
        orderList.remove(getByID(id));
    }

    @Override
    public Order getByID(long id) {
        for (Order order : orderList) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public boolean containsById(long id) {
        for (Order order : orderList) {
            if (order.getId() == id)
                return true;
        }
        return false;
    }
}
