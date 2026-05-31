package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
          + "databaseName=BDGestionEquipos;"
          + "encrypt=true;"
          + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "2233";

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con SQL Server: " + e.getMessage());
            return null;
        }
    }
}