package org.example;

import static org.example.services.ReadCsvToImport.importCsvToDb;
import static org.example.services.SetupDataBase.setupDataBase;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Balcão\\Desktop\\Developing\\3.AreaDeTestes\\Java\\AttDB\\src\\main\\resources\\Teste.csv";
        String outputFile = "C:\\Users\\Balcão\\Desktop\\Resultado.csv";

        try{
            System.out.println("Iniciando app...");
            System.out.println("Setando banco de dados...");
            setupDataBase();

            System.out.println("Lendo arquivo CSV...");
            importCsvToDb(inputFile);

            System.out.println("Proccess Finished!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}