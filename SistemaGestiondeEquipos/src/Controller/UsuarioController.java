package Controller;

import java.util.List;

import Dao.UsuarioDAO;
import Modelo.Usuario;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario procesarLogin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.out.println("Validación fallida: Credenciales vacías.");
            return null; 
        }
        
        return usuarioDAO.login(username, password);
    }

    public boolean procesarRegistro(Usuario nuevoUsuario, String passwordPlana) {

        nuevoUsuario.setUsername(nuevoUsuario.getUsername().toLowerCase());
        
        if (passwordPlana.length() < 6) {
            System.out.println("Validación fallida: La contraseña debe tener al menos 6 caracteres.");
            return false;
        }

        return usuarioDAO.registrarUsuario(nuevoUsuario, passwordPlana);
    }

    public boolean procesarActualizacion(Usuario usuarioModificado) {

        if (usuarioModificado.getIdUsuario() <= 0) {
            System.out.println("Error: ID de usuario no válido para actualizar.");
            return false;
        }
        
        return usuarioDAO.actualizarUsuario(usuarioModificado);
    }
    
    public boolean procesarCambioPassword(int idUsuario, String nuevaPassword) {
        if (nuevaPassword.length() < 6) {
            System.out.println("La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        return usuarioDAO.cambiarPassword(idUsuario, nuevaPassword);
    }

    public boolean procesarEliminacion(int idUsuario) {
        if (idUsuario <= 0) {
            System.out.println("Error: ID de usuario no válido para eliminar.");
            return false;
        }
        
        return usuarioDAO.eliminarUsuario(idUsuario);
    }
    
    public List<Usuario> procesarBusqueda(String termino) {
    
    	if (termino == null || termino.trim().isEmpty()) {
            return usuarioDAO.listarUsuarios();
        }
        return usuarioDAO.buscarUsuarios(termino.trim());
    }
    
    public List<Usuario> procesarListado() {
        return usuarioDAO.listarUsuarios();
    }
}