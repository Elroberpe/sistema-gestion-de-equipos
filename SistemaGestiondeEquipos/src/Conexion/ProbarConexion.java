package Conexion;

import java.sql.Connection;

public class ProbarConexion {

    public static void main(String[] args) {
        Connection con = Conexion.getConexion();

        if (con != null) {
            System.out.println("Conexión exitosa");
        } else {
            System.out.println("No se pudo conectar");
        }
    }
}