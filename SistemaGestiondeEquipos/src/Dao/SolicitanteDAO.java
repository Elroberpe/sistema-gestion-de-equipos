package Dao;

import Modelo.Solicitante;
import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class SolicitanteDAO {

    public boolean guardar(Solicitante solicitante) {
        String sql = "INSERT INTO Solicitante (DNI, Nombre, Apellidos, Tipo, SalonCurso, Celular, Correo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, solicitante.getDni());
            ps.setString(2, solicitante.getNombre());
            ps.setString(3, solicitante.getApellidos());
            ps.setString(4, solicitante.getTipo());
            ps.setString(5, solicitante.getSalonCurso());
            ps.setString(6, solicitante.getCelular());
            ps.setString(7, solicitante.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar solicitante: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Solicitante solicitante) {
        String sql = "UPDATE Solicitante SET DNI=?, Nombre=?, Apellidos=?, Tipo=?, SalonCurso=?, Celular=?, Correo=? WHERE IdSolicitante=?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, solicitante.getDni());
            ps.setString(2, solicitante.getNombre());
            ps.setString(3, solicitante.getApellidos());
            ps.setString(4, solicitante.getTipo());
            ps.setString(5, solicitante.getSalonCurso());
            ps.setString(6, solicitante.getCelular());
            ps.setString(7, solicitante.getCorreo());
            ps.setInt(8, solicitante.getIdSolicitante());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar solicitante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idSolicitante) {
        // Primero verificamos si tiene préstamos (Regla de negocio 7)
        String checkSql = "SELECT COUNT(*) FROM Prestamo WHERE IdSolicitante = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement psCheck = con.prepareStatement(checkSql)) {
            psCheck.setInt(1, idSolicitante);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // No se puede eliminar si tiene préstamos
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar préstamos: " + e.getMessage());
            return false;
        }

        String sql = "DELETE FROM Solicitante WHERE IdSolicitante=?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSolicitante);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar solicitante: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Solicitante> listar() {
        ArrayList<Solicitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitante";
        try (Connection con = Conexion.getInstancia().getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Solicitante(
                    rs.getInt("IdSolicitante"),
                    rs.getString("DNI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"),
                    rs.getString("Tipo"),
                    rs.getString("SalonCurso"),
                    rs.getString("Celular"),
                    rs.getString("Correo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar solicitantes: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Solicitante> buscarPorDniNombreApellido(String texto) {
        ArrayList<Solicitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Solicitante WHERE DNI LIKE ? OR Nombre LIKE ? OR Apellidos LIKE ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String t = "%" + texto + "%";
            ps.setString(1, t);
            ps.setString(2, t);
            ps.setString(3, t);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Solicitante(
                    rs.getInt("IdSolicitante"),
                    rs.getString("DNI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellidos"),
                    rs.getString("Tipo"),
                    rs.getString("SalonCurso"),
                    rs.getString("Celular"),
                    rs.getString("Correo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar solicitante: " + e.getMessage());
        }
        return lista;
    }

    public boolean existeDni(String dni) {
        String sql = "SELECT COUNT(*) FROM Solicitante WHERE DNI = ?";
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar DNI: " + e.getMessage());
        }
        return false;
    }
}
