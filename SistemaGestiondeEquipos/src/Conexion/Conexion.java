package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static Conexion instancia;
	private Connection conexion;

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
          + "databaseName=BDGestionEquipos;"
          + "encrypt=true;"
          + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "123456";
    
    
    private Conexion() {
    	try {
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conexion = DriverManager.getConnection(URL,USER,PASSWORD);
    	}catch(ClassNotFoundException |SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static Conexion getInstancia() {
        if(instancia == null) {
        	instancia = new Conexion();
        }
        return instancia;
    }
    
    public Connection getConexion() {
    	return conexion;
    }
}