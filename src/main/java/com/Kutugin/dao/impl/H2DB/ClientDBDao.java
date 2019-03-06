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

    public ClientDBDao() {
        getAllClients();
    }

    @Override
    public void saveClient(Client client) {
        clientList.add(client);
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME,AGE, PHONE,EMAIL) VALUES("
                     + "'" + client.getName() + "',"
                     + "'" + client.getSurmame() + "',"
                     + "" + client.getAge() + ","
                     + "'" + client.getPhoneNumber() + "',"
                     + "'" + client.getEmail() + "');")) {
            statement.execute();
        } catch (SQLException ignored) {
            System.out.println("Error saving client to DB");
        }
    }

    @Override
    public void updateClient(String phoneNumber, int paramNumber, String param) {
        Client client = getByPhoneNumber(phoneNumber);
        String query = null;
        switch (paramNumber) {
            case 1: {
                client.setName(param);
                query = "UPDATE CLIENT SET NAME = '"+param+"' WHERE PHONE ='"+phoneNumber+"';";
                break;
            }
            case 2: {
                client.setSurmame(param);
                query = "UPDATE CLIENT SET SURNAME = '"+param+"' WHERE PHONE ='"+phoneNumber+"';";
                break;
            }
            case 3: {
                client.setAge(Integer.valueOf(param));
                query = "UPDATE CLIENT SET AGE = "+Integer.valueOf(param)+" WHERE PHONE ='"+phoneNumber+"';";
                break;
            }
            case 4: {
                client.setEmail(param);
                query = "UPDATE CLIENT SET EMAIL = '"+param+"' WHERE PHONE ='"+phoneNumber+"';";
                break;
            }
            case 5: {
                client.setPhoneNumber(param);
                query = "UPDATE CLIENT SET PHONE = '"+param+"' WHERE PHONE ='"+phoneNumber+"';";
                break;
            }
            default: {
                System.out.println("Eror update client!");
            }
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Eror update client DB!");
        }
    }

    @Override
    public Client getByPhoneNumber(String phoneNumber) {
        for (Client c:clientList){
            if (c.getPhoneNumber().equals(phoneNumber)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        if (clientList == null) {
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
                    Long id = resultSet.getLong("ID");
                    clientList.add(new Client(id,name, surname, age, email, phoneNumber));
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
    public void deleteClient(String phoneNumber) {
        clientList.remove(getByPhoneNumber(phoneNumber));
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM CLIENT WHERE PHONE ='" + phoneNumber + "';");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
    }

    @Override
    public boolean contains(String phoneNumber) {
        for (Client c:clientList){
            if (c.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
}
