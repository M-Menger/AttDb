package org.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:sqlite:atendimentos.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}


