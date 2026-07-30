package Dao;

import Conexion.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String username, String passwordInsegura) {

        String sql = "SELECT IdUsuario, Username, PasswordHash, NombresCompletos, Rol, Estado " +
                     "FROM Usuario WHERE Username = ? AND Estado = 'Activo'";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("PasswordHash");
                    
                    if (BCrypt.checkpw(passwordInsegura, hashGuardado)) {
                        
                        Usuario usuarioLogueado = new Usuario();
                        usuarioLogueado.setIdUsuario(rs.getInt("IdUsuario"));
                        usuarioLogueado.setUsername(rs.getString("Username"));
                        usuarioLogueado.setNombresCompletos(rs.getString("NombresCompletos"));
                        usuarioLogueado.setRol(rs.getString("Rol"));
                        usuarioLogueado.setEstado(rs.getString("Estado"));
                        
                        return usuarioLogueado;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error crítico en la autenticación: " + e.getMessage());
        }
        
        return null;
    }
    
    public boolean registrarUsuario(Usuario usuario, String passwordPlana) {

        String sql = "INSERT INTO Usuario (Username, PasswordHash, NombresCompletos, Rol, Estado) VALUES (?, ?, ?, ?, ?)";
        String hashGenerado = BCrypt.hashpw(passwordPlana, BCrypt.gensalt());

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, hashGenerado);
            pstmt.setString(3, usuario.getNombresCompletos());
            pstmt.setString(4, usuario.getRol());
            pstmt.setString(5, "Activo"); 
            
            int filasAfectadas = pstmt.executeUpdate();
            
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarUsuario(Usuario usuario) {

    	String sql = "UPDATE Usuario SET Username = ?, NombresCompletos = ?, Rol = ?, Estado = ? WHERE IdUsuario = ?";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());          
            pstmt.setString(2, usuario.getNombresCompletos());   
            pstmt.setString(3, usuario.getRol());               
            pstmt.setString(4, usuario.getEstado());             
            pstmt.setInt(5, usuario.getIdUsuario());             
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean cambiarPassword(int idUsuario, String nuevaPasswordPlana) {
        String sql = "UPDATE Usuario SET PasswordHash = ? WHERE IdUsuario = ?";
        String hashGenerado = BCrypt.hashpw(nuevaPasswordPlana, BCrypt.gensalt());

        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, hashGenerado);
            pstmt.setInt(2, idUsuario);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al cambiar contraseña: " + e.getMessage());
            return false;
        }
    }
    
    public List<Usuario> buscarUsuarios(String termino) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        String sql = "SELECT IdUsuario, Username, NombresCompletos, Rol, Estado FROM Usuario " +
                     "WHERE Username LIKE ? OR NombresCompletos LIKE ?";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            String parametroBusqueda = "%" + termino + "%";
            
            pstmt.setString(1, parametroBusqueda);
            pstmt.setString(2, parametroBusqueda);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("IdUsuario"));
                    u.setUsername(rs.getString("Username"));
                    u.setNombresCompletos(rs.getString("NombresCompletos"));
                    u.setRol(rs.getString("Rol"));
                    u.setEstado(rs.getString("Estado"));
                    
                    listaUsuarios.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuarios: " + e.getMessage());
        }
        
        return listaUsuarios;
    }
    
    public boolean eliminarUsuario(int idUsuario) {
 
    	String sql = "DELETE FROM Usuario WHERE IdUsuario = ?";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuario);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error crítico al eliminar usuario físicamente: " + e.getMessage());
            return false;
        }
    }
    
    public List<Usuario> listarUsuarios() {

        List<Usuario> listaUsuarios = new ArrayList<>();
        
        String sql = "SELECT IdUsuario, Username, NombresCompletos, Rol, Estado FROM Usuario";
        
        try (Connection con = Conexion.getInstancia().getConexion();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                
                u.setIdUsuario(rs.getInt("IdUsuario"));
                u.setUsername(rs.getString("Username"));
                u.setNombresCompletos(rs.getString("NombresCompletos"));
                u.setRol(rs.getString("Rol"));
                u.setEstado(rs.getString("Estado"));
                
                listaUsuarios.add(u);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios: " + e.getMessage());
        }
        
        return listaUsuarios;
    }
    
    
}