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
    private List<Order> orderListByClient;
    List<Long> cartList = new ArrayList<>();
    List<Product> productCartList;

    public OrderDBDao() {
        //getOrders();
    }

    @Override
    public long add(Client client, Order order) {
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
        orderListByClient.add(order);
        return 0;
    }

    @Override
    public void addProduct(long id, Product product) {
        getByID(id).addProduct(product);
        String queryCart = "INSERT INTO CART_ID(ORDER_ID,PRODUCT_ID) VALUES(" + id + ", " + product.getId() + ");";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(queryCart)) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error adding product");
        }
        update(id, getByID(id));
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
//        List<Long> cartList = new ArrayList<>();
//        List<Product> productCartList = new ArrayList<>();
//        if (orderList == null) {
//            orderList = new ArrayList<>();
//            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
//                 Statement statement = connection.createStatement()) {
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM \"ORDER\";");
//                    while (resultSet.next()) {
//                            String date = resultSet.getString("DATE");
//                            long id = resultSet.getLong("ID");
//                            try(Statement statement1 = connection.createStatement()) {
//                                ResultSet resultSetCart = statement1.executeQuery("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =" + id + ";");
//                                while (resultSetCart.next()) {
//                                    cartList.add(resultSetCart.getLong("PRODUCT_ID"));
//                                }
//                            } catch (SQLException e){
//
//                            }
//
//                        try(Statement statement2 = connection.createStatement()) {
//                            ResultSet productResultSetCart = statement2.executeQuery("SELECT * FROM \"PRODUCT\");");
//                            while (productResultSetCart.next()) {
//                                long idP = resultSet.getLong("ID");
//                                String firm = resultSet.getString("FIRM");
//                                String model = resultSet.getString("MODEL");
//                                String type = resultSet.getString("TYPE");
//                                double price = resultSet.getDouble("PRICE");
//                                productCartList.add(new Product(firm, model, price, type, idP));
//                            }
//                        } catch (SQLException e){
//
//                        }
//                    orderList.add(new Order(id, productCartList, date));
//                }
//                return orderList;
//            } catch (SQLException e) {
//                System.out.println("Order not found");
//                System.out.println(e.getErrorCode());
//            }
//        }
//        return orderList;
        return null;
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        if (orderListByClient == null) {
            orderListByClient = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM \"ORDER\" WHERE CLIENT_ID =" + clientId + ";");
                while (resultSet.next()) {
                    String date = resultSet.getString("DATE");
                    long id = resultSet.getLong("ID");
                    {
                        try (Statement statement1 = connection.createStatement()) {
                            ResultSet resultSetCart = statement1.executeQuery("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =" + id + ";");
                            while (resultSetCart.next()) {
                                cartList.add(resultSetCart.getLong("PRODUCT_ID"));
                            }
                            try (Statement statement2 = connection.createStatement()) {
                                ResultSet productResultSetCart = statement2.executeQuery("SELECT * FROM \"PRODUCT\" WHERE ID IN (" + listToString(cartList) + ");");
                                cartList.clear();
                                productCartList = new ArrayList<>();
                                while (productResultSetCart.next()) {
                                    long idP = productResultSetCart.getLong("ID");
                                    String firm = productResultSetCart.getString("FIRM");
                                    String model = productResultSetCart.getString("MODEL");
                                    String type = productResultSetCart.getString("TYPE");
                                    double price = productResultSetCart.getDouble("PRICE");
                                    productCartList.add(new Product(firm, model, price, type, idP));
                                }
                            } catch (SQLException e) {
                            }
                        } catch (SQLException e) {
                        }
                    }
                    orderListByClient.add(new Order(id, productCartList, date));
                    productCartList = null;
                }
            } catch (SQLException e) {
                System.out.println("Order by client not found");
            }
        }
        return orderListByClient;
    }

    private String listToString(List<Long> input) {
        String s = "";
        int count = 0;
        for (Long l : input) {
            s += l;
            if (!(++count == input.size())) {
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
        for (Order order : orderListByClient) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public boolean containsById(long id) {
        for (Order order : orderListByClient) {
            if (order.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public long getMaxID() {
        long maxId = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement2 = connection.createStatement()) {
            ResultSet resultSet = statement2.executeQuery("SELECT MAX(ID) FROM \"ORDER\";");
            resultSet.next();
            maxId = resultSet.getLong(1);
        } catch (SQLException e) {

        }
        return  maxId;
    }
}