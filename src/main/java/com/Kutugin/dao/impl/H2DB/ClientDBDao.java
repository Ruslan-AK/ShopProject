package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "admin";
    private static final String PASS = "";
    private List<Client> clientList;
    private boolean modified = false;//flag modification, to reload clientList when we change DB information

    @Override
    public void saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME,AGE, PHONE,EMAIL) VALUES("
                     + "'" + client.getName() + "',"
                     + "'" + client.getSurmame() + "',"
                     + "" + client.getAge() + ","
                     + "'" + client.getPhoneNumber() + "',"
                     + "'" + client.getEmail() + "');")) {
            statement.execute();
            modified = true;
        } catch (SQLException ignored) {
        }
    }

    @Override
    public Client getById(String id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT WHERE PHONE='" + id + "';");
            resultSet.next();//move pointer to first row of table
            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            String age = resultSet.getString("AGE");
            String phoneNumber = resultSet.getString("PHONE");
            String email = resultSet.getString("EMAIL");
            return new Client(name, surname, age, email, phoneNumber);
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        if (clientList == null||modified) {
            clientList = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT;");
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String surname = resultSet.getString("SURNAME");
                    String age = resultSet.getString("AGE");
                    String phoneNumber = resultSet.getString("PHONE");
                    String email = resultSet.getString("EMAIL");
                    clientList.add(new Client(name, surname, age, email, phoneNumber));
                }
                return clientList;
            } catch (SQLException e) {
                System.out.println("Client not found");
            }
        } else {
            return clientList;
        }
        return null;
    }

    @Override
    public void deleteClient(String id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM CLIENT WHERE PHONE ='" + id + "';");
            modified = true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    @Override
    public boolean contains(String id) {
        boolean find = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT WHERE PHONE = '" + id + "';");
            while (!find&&resultSet.next()) {
                String name = resultSet.getString("PHONE");
                if (name != null && name.length() > 0) {
                    find = true;
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return find;
    }
}
