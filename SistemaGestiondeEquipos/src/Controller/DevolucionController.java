package Controller;

import java.util.ArrayList;

import DTO.PrestamoActivoDTO;
import Dao.DevolucionDao;

public class DevolucionController {
	private DevolucionDao devolucionDao;
	
	public DevolucionController() {
		
		devolucionDao = new DevolucionDao();
		
	}
	
	public ArrayList<PrestamoActivoDTO> listarPrestamosActivos(){
		return devolucionDao.listarPrestamosActivos();
	}
	
	public ArrayList<PrestamoActivoDTO> buscarPrestamoActivos(String idPrestamo, String dni, String codigoEquipoi){
		
		return devolucionDao.buscarPrestamoActivo(idPrestamo, dni, codigoEquipoi);
	}
	
	
}
