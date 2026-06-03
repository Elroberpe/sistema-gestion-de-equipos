package DAO;

import Modelo.Equipo;
import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class EquipoDAO {

    public boolean guardar(Equipo equipo) {
        String sql = "INSERT INTO Equipo (Codigo, Nombre, Tipo, Marca, Modelo, NumeroSerie, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, equipo.getCodigo());
            ps.setString(2, equipo.getNombre());
            ps.setString(3, equipo.getTipo());
            ps.setString(4, equipo.getMarca());
            ps.setString(5, equipo.getModelo());
            ps.setString(6, equipo.getNumeroSerie());
            ps.setString(7, equipo.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Equipo equipo) {
        String sql = "UPDATE Equipo SET Codigo=?, Nombre=?, Tipo=?, Marca=?, Modelo=?, NumeroSerie=?, Estado=? WHERE IdEquipo=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, equipo.getCodigo());
            ps.setString(2, equipo.getNombre());
            ps.setString(3, equipo.getTipo());
            ps.setString(4, equipo.getMarca());
            ps.setString(5, equipo.getModelo());
            ps.setString(6, equipo.getNumeroSerie());
            ps.setString(7, equipo.getEstado());
            ps.setInt(8, equipo.getIdEquipo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEquipo) {
        String sql = "DELETE FROM Equipo WHERE IdEquipo=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEquipo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Equipo> listar() {
        ArrayList<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Equipo";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Equipo e = new Equipo(
                    rs.getInt("IdEquipo"),
                    rs.getString("Codigo"),
                    rs.getString("Nombre"),
                    rs.getString("Tipo"),
                    rs.getString("Marca"),
                    rs.getString("Modelo"),
                    rs.getString("NumeroSerie"),
                    rs.getString("Estado")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Equipo> buscarPorNombreOCodigo(String texto) {
        ArrayList<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Equipo WHERE Nombre LIKE ? OR Codigo LIKE ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipo e = new Equipo(
                    rs.getInt("IdEquipo"),
                    rs.getString("Codigo"),
                    rs.getString("Nombre"),
                    rs.getString("Tipo"),
                    rs.getString("Marca"),
                    rs.getString("Modelo"),
                    rs.getString("NumeroSerie"),
                    rs.getString("Estado")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarEstado(int idEquipo, String estado) {
        String sql = "UPDATE Equipo SET Estado=? WHERE IdEquipo=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, idEquipo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
}