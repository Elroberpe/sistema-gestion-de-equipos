package Controller;

import Dao.EquipoDAO;
import Modelo.Equipo;
import java.util.ArrayList;

public class EquipoController {

    private EquipoDAO equipoDAO = new EquipoDAO();

    public boolean guardar(Equipo equipo) {
        return equipoDAO.guardar(equipo);
    }

    public boolean actualizar(Equipo equipo) {
        return equipoDAO.actualizar(equipo);
    }

    public boolean eliminar(int idEquipo) {
        return equipoDAO.eliminar(idEquipo);
    }

    public ArrayList<Equipo> listar() {
        return equipoDAO.listar();
    }

    public ArrayList<Equipo> buscarPorNombreOCodigo(String texto) {
        return equipoDAO.buscarPorNombreOCodigo(texto);
    }

    public boolean actualizarEstado(int idEquipo, String estado) {
        return equipoDAO.actualizarEstado(idEquipo, estado);
    }
}