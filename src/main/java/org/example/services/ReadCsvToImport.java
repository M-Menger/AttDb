package org.example.services;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadCsvToImport {
    private static final String DB_URL = "jdbc:sqlite:atendimentos.db";

    public static void importCsvToDb(String csvFile) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFile), "ISO-8859-1"));
             Connection conn = DriverManager.getConnection(DB_URL)) {
            String line;
            boolean isFirstLine = true;
            String sql = "INSERT INTO ATENDIMENTOS (Atendimento, Número da OS, Placa, Modelo, Motivo da OS, Status da OS) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) { // Pular a primeira linha (cabeçalho)
                        isFirstLine = false;
                        continue;
                    }

                    String[] values = line.split(";");
                    pstmt.setString(1, values[0]);
                    pstmt.setString(2, values[1]);
                    pstmt.setString(3, values[2]);
                    pstmt.setString(4, values[3]);
                    pstmt.setString(5, values[4]);
                    pstmt.setString(6, values[5]);
                    pstmt.executeUpdate();
                }
            }
        }
    }
}