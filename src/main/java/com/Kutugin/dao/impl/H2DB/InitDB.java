package com.Kutugin.dao.impl.H2DB;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitDB {
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    public static final String LOGIN = "admin";
    public static final String PASS = "";
    private List<String> tables;
    private List<String> tablesInDB;

    public InitDB() {
        tables = new ArrayList<>(Arrays.asList("CART_ID", "CLIENT", "ORDER", "PRODUCT"));
        tablesInDB = new ArrayList<>();
    }

    public void fillExistTables() {
        loadDBNameToList();
        createAndFillDBifInList();
    }

    private void loadDBNameToList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
             PreparedStatement statement = connection.prepareStatement("SHOW TABLES")) {
            ResultSet tables = statement.executeQuery();
            while (tables.next()) {
                String target = tables.getString(1);
                tablesInDB.add(target);
            }
        } catch (SQLException ignored) {
        }
    }

    private void createAndFillDBifInList() {
        for (String s : tables) {
            if (!tablesInDB.contains(s)) {
                createDBbyName(s);
                fillDBbyName(s);
            }
        }
    }

    private void fillDBbyName(String s) {
        String query = null;
        switch (s) {
            case "PRODUCT": {
                query = "INSERT INTO PRODUCT(FIRM,MODEL,TYPE,PRICE) VALUES('Asus','K53SD','LAPTOP',450.9), ('Apple','IPHONE 7','SMARTPHONE',400), ('Lenovo', 'I508', 'LAPTOP', 429.9), ('Samsung', 'Smart TV x8', 'TV', 389.9), ('Kivi', 'Z90', 'TV', 340), ('ASUS', 'allInOnePC', 'PC', 450), ('Sony', 'W500', 'MP3_PLAYER', 110), ('Samsung', 'J5 2016', 'SMARTPHONE', 180), ('Xiaomi', 'Redmi Note 5', 'SMARTPHONE', 210), ('Toshiba', 'k45','MP3_PLAYER', 80), ('Apple', 'Ipod 64GB', 'MP3_PLAYER', 460)";
                break;
            }
            case "ORDER": {
                query = "INSERT INTO \"ORDER\"(CLIENT_ID,DATE) VALUES(2,'Wed Mar 06 073:05:10 EET 2019'),(1,'Wed Mar 06 07:05:10 EET 2019'),(1,'Wed Mar 06 08:05:10 EET 2019')";
                break;
            }
            case "CLIENT": {
                query = "INSERT INTO CLIENT(NAME,SURNAME,AGE,PHONE,EMAIL)  VALUES('Ruslan','Kutugin',26,'0676387370','kututginr@gmail.com'), ('Andrew','Sapojnikov',24,'0987798955','sapojnikov@gmail.com');";
                break;
            }
            case "CART_ID": {
                query = "INSERT INTO CART_ID(ORDER_ID, PRODUCT_ID,PRODUCT_AMOUNT) VALUES(1,1,2),(1,6,2),(2,4,2),(3,2,3)";
                break;
            }
            default:
                System.out.println("No such table!");
                break;
        }
        if (query != null) {
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            } catch (SQLException ignored) {
                System.out.println("Error fill DB " + s);
            }
        }
    }

    private void createDBbyName(String s) {
        String query = null;
        switch (s) {
            case "PRODUCT": {
                query = "create table \"PRODUCT\"(ID BIGINT PRIMARY KEY AUTO_INCREMENT, FIRM VARCHAR(255), MODEL VARCHAR(255), TYPE VARCHAR(255),PRICE DOUBLE);";
                break;
            }
            case "ORDER": {
                query = "create table \"ORDER\"(ID BIGINT PRIMARY KEY AUTO_INCREMENT, CLIENT_ID BIGINT, DATE VARCHAR(255));";
                break;
            }
            case "CLIENT": {
                query = "CREATE TABLE \"CLIENT\"(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), SURNAME VARCHAR(20),AGE INT, PHONE VARCHAR(20),EMAIL VARCHAR(50));";
                break;
            }
            case "CART_ID": {
                query = "create table \"CART_ID\"(ORDER_ID BIGINT, PRODUCT_ID BIGINT,PRODUCT_AMOUNT BIGINT);";
                break;
            }
            default:
                System.out.println("No such table!");
                break;
        }
        if (query != null) {
            try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            } catch (SQLException ignored) {
                System.out.println("Error creating DB " + s);
            }
        }
    }
}
