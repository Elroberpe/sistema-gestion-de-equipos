package Controller;

import Dao.SolicitanteDAO;
import Modelo.Solicitante;
import java.util.ArrayList;

public class SolicitanteController {

    private SolicitanteDAO solicitanteDAO = new SolicitanteDAO();

    public boolean guardar(Solicitante solicitante) {
        return solicitanteDAO.guardar(solicitante);
    }

    public boolean actualizar(Solicitante solicitante) {
        return solicitanteDAO.actualizar(solicitante);
    }

    public boolean eliminar(int idSolicitante) {
        return solicitanteDAO.eliminar(idSolicitante);
    }

    public ArrayList<Solicitante> listar() {
        return solicitanteDAO.listar();
    }

    public ArrayList<Solicitante> buscarPorDniNombreApellido(String texto) {
        return solicitanteDAO.buscarPorDniNombreApellido(texto);
    }

    public boolean existeDni(String dni) {
        return solicitanteDAO.existeDni(dni);
    }
}