package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import com.Kutugin.exceptions.BusinessException;
import org.h2.tools.Server;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.Kutugin.dao.impl.H2DB.InitDB.*;

@Repository
public class ClientDBDao implements ClientDao {

    public ClientDBDao() {
        try {
            Server.createTcpServer().start();
            Server.openBrowser("youtube.com");
        } catch (SQLException ignored) {
            System.out.println("Error init DBbb");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getAllClients();
    }

    @Override
    public boolean saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME,AGE, PHONE,EMAIL,ID) VALUES(?,?,?,?,?,?);")) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhoneNumber());
            statement.setString(5, client.getEmail());
            statement.setLong(6, client.getId());
            statement.execute();
            return true;
        } catch (SQLException ignored) {
            System.out.println("Error saving client to DB");
            return false;
        }
    }

    @Override
    public void  updateClient(long currentClientID, Client clientFrom) {
        Client c = getClientByID(currentClientID);
        if (clientFrom.getName() != null) {
            c.setName(clientFrom.getName());
        }
        if (clientFrom.getSurname() != null) {
            c.setSurname(clientFrom.getSurname());
        }
        if (clientFrom.getAge() != 0) {
            c.setAge(clientFrom.getAge());
        }
        if (clientFrom.getPhoneNumber() != null) {
            c.setPhoneNumber(clientFrom.getPhoneNumber());
        }
        if (clientFrom.getEmail() != null) {
            c.setEmail(clientFrom.getEmail());
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("UPDATE CLIENT SET NAME = ?, SURNAME = ?,AGE = ?, PHONE = ?,EMAIL = ? WHERE ID = ?;")) {
            statement.setString(1, c.getName());
            statement.setString(2, c.getSurname());
            statement.setInt(3, c.getAge());
            statement.setString(4, c.getPhoneNumber());
            statement.setString(5, c.getEmail());
            statement.setLong(6, currentClientID);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Eror update client DB!");
        }
    }


    @Override
    public long getIDByPhoneNumber(String phoneNumber) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT WHERE PHONE = ?;")) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("ID");
            } else throw new BusinessException("No such ID");
        } catch (SQLException | BusinessException e) {
            System.out.println("Error isPresent");
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();
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
                clientList.add(new Client(id, name, surname, age, email, phoneNumber));
            }
            return clientList;
        } catch (SQLException e) {
            System.out.println("Client not found");
        }
        return null;
    }

    @Override
    public void deleteClient(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CLIENT WHERE ID = ?;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleteClient");
            System.out.println(e.getSQLState());
        }
    }

    @Override
    public boolean isPresent(String phone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT WHERE PHONE =?;")) {
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
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
            ResultSet resultSet = statement2.executeQuery("SELECT MAX(ID) FROM CLIENT;");
            resultSet.next();
            maxId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("Error getNextByMaxID");
            System.out.println(e.getErrorCode());
        }
        return maxId + 1;
    }

    @Override
    public Client getClientByID(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                String age = resultSet.getString("AGE");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                return new Client(id, name, surname, age, email, phone);
            } else System.out.println("Client not found in DB");
        } catch (SQLException e) {
            System.out.println("Error getByPhoneNumber");
            System.out.println(e.getErrorCode());
        }
        return null;
    }
}
