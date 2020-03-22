package com.paolamonteiro.core.agenda.dao;

import com.paolamonteiro.core.agenda.exception.AgendaException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/Agenda";
    private final String USER = "root";
    private final String PASSWORD = "password";
    private static Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new AgendaException("Connection error: " + e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new AgendaException("Connection error: " + e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new AgendaException("Connection error: " + e);
            }
        }
    }
}
