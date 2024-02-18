package com.example.cp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    public static Connection dbConnection;
    public static Connection getConnection() {
        Connection dbLink;
        String user = "root";
        String db_name = "warehouse";
        String password = "DelendikMySQL1234";
        String url = "jdbc:mysql://127.0.0.1/"+ db_name;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return  dbLink;
    }
    public static synchronized DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
