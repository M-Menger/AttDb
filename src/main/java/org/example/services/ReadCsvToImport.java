package org.example.services;

import java.io.*;
import java.sql.*;

public class ReadCsvToImport {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Balcão\\Desktop\\Developing\\3.AreaDeTestes\\Java\\AttDB\\src\\main\\resources\\atendimentos.db";

    public static void importCsvToDb(String csvFile) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFile), "ISO-8859-1"));
             Connection conn = DriverManager.getConnection(DB_URL)) {
            String line;
            boolean isFirstLine = true;
            String sql = "INSERT INTO ATENDIMENTOS (Atendimento, Número_da_OS, Placa, Modelo, Motivo_da_OS, Status_da_OS) VALUES (?, ?, ?, ?, ?, ?)";

                while ((line = br.readLine()) != null) {
                    if (isFirstLine) { // Pular a primeira linha (cabeçalho)
                        isFirstLine = false;
                        continue;
                }
                String[] values = line.split(";");
                String atendimento = values[0];
                String numeroOs = values[1];
                String placa = values[2];
                String modelo = values[3];
                String motivoOs = values[4];
                String statusOs = values[5];

                if (registroExiste(atendimento)) {
                    atualizarRegistro(atendimento,statusOs);
                } else {
                    inserirRegistro(atendimento, numeroOs, placa, modelo, motivoOs, statusOs);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean registroExiste(String atendimento) {
        String sql = "SELECT 1 FROM ATENDIMENTOS WHERE Atendimento = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atendimento);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se o registro existir
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void inserirRegistro(String atendimento, String numeroOs, String placa, String modelo, String motivoOs, String statusOs) {
        String sql = "INSERT INTO ATENDIMENTOS (Atendimento, Número_da_OS, Placa, Modelo, Motivo_da_OS, Status_da_OS) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atendimento);
            stmt.setString(2, numeroOs);
            stmt.setString(3, placa);
            stmt.setString(4, modelo);
            stmt.setString(5, motivoOs);
            stmt.setString(6, statusOs);
            stmt.executeUpdate();
            System.out.println("Registro inserido: " + atendimento);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void atualizarRegistro(String atendimento, String statusOs) {
        String sql = "UPDATE ATENDIMENTOS SET Status_da_OS = ? WHERE Atendimento = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statusOs);
            stmt.setString(2, atendimento);
            stmt.executeUpdate();
            System.out.println("Registro atualizado: " + atendimento);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}