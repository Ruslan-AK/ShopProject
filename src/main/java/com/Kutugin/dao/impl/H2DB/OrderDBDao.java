package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.OrderDao;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static com.Kutugin.dao.impl.H2DB.InitDB.*;

public class OrderDBDao implements OrderDao {

    @Override
    public void addClientOrder(Client client, Order order) {
        String queryCart = "INSERT INTO \"ORDER\"(CLIENT_ID,DATE) VALUES(?,?);";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(queryCart)) {
            statement.setLong(1, client.getId());
            statement.setString(2, order.getDate());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error adding order");
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public void update(long id, Order order) {
        //update date of creation order
        order.setDate(new Date().toString());
        String query = "UPDATE \"ORDER\" SET DATE = ? WHERE ID = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, order.getDate());
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error updating order");
            System.out.println(e.getErrorCode());
        }
//erase cart
        query = "DELETE FROM CART_ID WHERE ORDER_ID = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating order");
            System.out.println(e.getErrorCode());
        }
//fill cart from order
        for (Product p : order.getProductMap().keySet()) {
            String queryCart = "INSERT INTO CART_ID(ORDER_ID,PRODUCT_ID,PRODUCT_AMOUNT) VALUES(?,?,?);";
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 PreparedStatement statement = connection.prepareStatement(queryCart)) {
                statement.setLong(1, id);
                statement.setLong(2, p.getId());
                statement.setLong(3, order.getProductMap().get(p));
                statement.execute();
            } catch (SQLException e) {
                System.out.println("Error updating order");
                System.out.println(e.getErrorCode());
            }
        }
    }

    @Override
    public List<Order> getOrdersByClient(long clientId) {
        List<Long> cartList = new ArrayList<>();
        List<Product> productCartList = new ArrayList<>();
        List<Order> orderListByClient = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"ORDER\" WHERE CLIENT_ID = ?")) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String date = resultSet.getString("DATE");
                long id = resultSet.getLong("ID");
                //get products
                try (PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =?;")) {
                    statement1.setLong(1, id);
                    ResultSet resultSetCart = statement1.executeQuery();
                    productCartList = new ArrayList<>();
                    while (resultSetCart.next()) {
                        long productID = resultSetCart.getLong("PRODUCT_ID");
                        long productAmount = resultSetCart.getLong("PRODUCT_AMOUNT");
                        for (int i = 0; i < productAmount; i++) {
                            try (PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE ID =?")) {
                                statement2.setLong(1, productID);
                                ResultSet productResultSetCart = statement2.executeQuery();
                                while (productResultSetCart.next()) {
                                    long idP = productResultSetCart.getLong("ID");
                                    String firm = productResultSetCart.getString("FIRM");
                                    String model = productResultSetCart.getString("MODEL");
                                    String type = productResultSetCart.getString("TYPE");
                                    double price = productResultSetCart.getDouble("PRICE");
                                    productCartList.add(new Product(firm, model, price, type, idP));
                                }
                            } catch (SQLException e) {
                                System.out.println("Error getOrdersByClient");
                                System.out.println(e.getErrorCode());
                            }
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error getOrdersByClient");
                    System.out.println(e.getErrorCode());
                }
                orderListByClient.add(new Order(id, productCartList, date));
            }
        } catch (SQLException e) {
            System.out.println("Error getOrdersByClient");
            System.out.println(e.getErrorCode());
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
        String query = "DELETE FROM \"ORDER\" WHERE ID = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error deleting order");
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public Order getByID(long id) {
        String query = "SELECT * FROM \"ORDER\" WHERE ID = ?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String date = resultSet.getString("DATE");
            //get products
            List<Product> productCartList = new ArrayList<>();
            //get products
            try (PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM \"CART_ID\" WHERE ORDER_ID =?;")) {
                statement1.setLong(1, id);
                ResultSet resultSetCart = statement1.executeQuery();
                while (resultSetCart.next()) {
                    long productID = resultSetCart.getLong("PRODUCT_ID");
                    long productAmount = resultSetCart.getLong("PRODUCT_AMOUNT");
                    for (int i = 0; i < productAmount; i++) {
                        try (PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM \"PRODUCT\" WHERE ID =?")) {
                            statement2.setLong(1, productID);
                            ResultSet productResultSetCart = statement2.executeQuery();
                            while (productResultSetCart.next()) {
                                long idP = productResultSetCart.getLong("ID");
                                String firm = productResultSetCart.getString("FIRM");
                                String model = productResultSetCart.getString("MODEL");
                                String type = productResultSetCart.getString("TYPE");
                                double price = productResultSetCart.getDouble("PRICE");
                                productCartList.add(new Product(firm, model, price, type, idP));
                            }
                        } catch (SQLException e) {
                            System.out.println("Error getOrdersByClient");
                            System.out.println(e.getErrorCode());
                        }
                    }
                }
                return new Order(id, productCartList, date);
            } catch (SQLException e) {
                System.out.println("Error getOrdersByClient");
                System.out.println(e.getErrorCode());
            }
        } catch (SQLException e) {
            System.out.println("Error getOrdersByClient");
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    @Override
    public boolean isPresent(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"ORDER\" WHERE ID =?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            System.out.println("Error isPresent");
            System.out.println(e.getSQLState());
        }
        return false;
    }

    @Override
    public long getNextByMaxID() {
        long maxId = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement2 = connection.createStatement()) {
            ResultSet resultSet = statement2.executeQuery("SELECT MAX(ID) FROM \"ORDER\";");
            if (resultSet.next()){
                maxId = resultSet.getLong(1);
            }
            else maxId = 0;
        } catch (SQLException e) {
            System.out.println("Error getNextByMaxID");
            System.out.println(e.getSQLState());
        }
        return maxId + 1;
    }
}