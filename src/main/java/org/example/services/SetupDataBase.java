package org.example.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDataBase {
    public static void setupDataBase() throws SQLException{

        final String DB_URL = "jdbc:sqlite:C:\\Users\\Balcão\\Desktop\\Developing\\3.AreaDeTestes\\Java\\AttDB\\src\\main\\resources\\atendimentos.db";

        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS ATENDIMENTOS (
                    ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    Atendimento TEXT,
                    Número da OS TEXT,
                    Placa TEXT,
                    Modelo TEXT,
                    Motivo da OS TEXT,
                    Status da OS TEXT
                    );
                    """;
            stmt.execute(sql);
        }
    }
}
