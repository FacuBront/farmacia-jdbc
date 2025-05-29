package main;

import util.DBConnection;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Conexión exitosa a la base de datos H2");
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
    }
}
