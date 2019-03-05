//package com.Kutugin.dao.impl.H2DB;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Init {
//    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
//    private static final String LOGIN = "admin";
//    private static final String PASS = "";
//    public void initOrderDB(){
//        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT(NAME, SURNAME,AGE, PHONE,EMAIL) VALUES("
//                     + "'" + client.getName() + "',"
//                     + "'" + client.getSurmame() + "',"
//                     + "" + client.getAge() + ","
//                     + "'" + client.getPhoneNumber() + "',"
//                     + "'" + client.getEmail() + "');")) {
//            statement.execute();
//            modified = true;
//        } catch (SQLException ignored) {
//        }
//    }
//}
