package com.github.gkttk.testApp.connectUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnector {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    /*private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/testwebapp?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";*/

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mySQL");
        String url = resourceBundle.getString("url");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        Connection connection;
        connection = DriverManager.getConnection(url, user, password);
        return connection;


    }

}
