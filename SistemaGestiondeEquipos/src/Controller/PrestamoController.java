package Controller;

import Dao.PrestamoDao;
import Modelo.Prestamo;
import java.util.ArrayList;
 
public class PrestamoController {
    
    private PrestamoDao dao = new PrestamoDao();
    
    public boolean registrar(Prestamo prestamo) {
        return dao.guardar(prestamo);}
    
    public ArrayList<Prestamo> listar() {
        return dao.listar();}
}