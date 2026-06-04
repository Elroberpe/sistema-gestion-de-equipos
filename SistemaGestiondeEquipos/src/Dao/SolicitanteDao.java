package Dao;

import Conexion.Conexion;
import Modelo.Solicitante;
import java.sql.*;
import java.util.ArrayList;

public class SolicitanteDao {

    // BUSCAR POR DNI =====================================================
    public Solicitante buscarPorDni(String dni) {
        String sql = "SELECT * FROM Solicitante WHERE DNI = ?";
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("IdSolicitante"));
                s.setDni(rs.getString("DNI"));
                s.setNombre(rs.getString("Nombre"));
                s.setApellidos(rs.getString("Apellidos"));
                s.setTipo(rs.getString("Tipo"));
                s.setSalonCurso(rs.getString("SalonCurso"));
                s.setCelular(rs.getString("Celular"));
                s.setCorreo(rs.getString("Correo"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    // LISTAR TODOS =======================================================
    public ArrayList<Solicitante> listar() {
        ArrayList<Solicitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitante";
        try {
            Connection con = Conexion.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("IdSolicitante"));
                s.setDni(rs.getString("DNI"));
                s.setNombre(rs.getString("Nombre"));
                s.setApellidos(rs.getString("Apellidos"));
                s.setTipo(rs.getString("Tipo"));
                s.setSalonCurso(rs.getString("SalonCurso"));
                s.setCelular(rs.getString("Celular"));
                s.setCorreo(rs.getString("Correo"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}