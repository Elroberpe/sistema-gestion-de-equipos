package Dao;

import Conexion.Conexion;
import Modelo.Prestamo;
import java.sql.*;
import java.util.ArrayList;

public class PrestamoDao {
	
	//GUARDAR PRESTAMO =================================================================================================================================================================
	public boolean guardar(Prestamo prestamo) 
		 { String sql = "INSERT INTO Prestamo (IdEquipo, IdSolicitante,FechaPrestamo, HoraPrestamo, FechaDevolucionPrevista, Observaciones, Estado)"
				     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	       try {Connection con = Conexion.getConexion();
		        PreparedStatement ps = con.prepareStatement(sql);
							      ps.setInt(1, prestamo.getIdEquipo());
							      ps.setInt(2, prestamo.getIdSolicitante());
							      ps.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
							      ps.setTime(4, Time.valueOf(prestamo.getHoraPrestamo()));
							      ps.setDate(5, Date.valueOf(prestamo.getFechaDevolucionPrevista()));
							      ps.setString(6, prestamo.getObservaciones());
							      ps.setString(7, prestamo.getEstado());
							      ps.executeUpdate();
	       return true;}	
	       
	       catch (SQLException e) {System.out.println("Error al guardar: " + e.getMessage());
	       return false;}
		 }

	
	// LISTAR PRÉSTAMOS =================================================================================================================================================================
	public ArrayList<Prestamo> listar()
		 { ArrayList<Prestamo> lista = new ArrayList<>();
		   String sql = "SELECT * FROM Prestamo";
		   try {Connection con = Conexion.getConexion();
		       	Statement st = con.createStatement();
		       	ResultSet rs = st.executeQuery(sql);
		       	while (rs.next()) {
			           Prestamo p = new Prestamo();
			           p.setIdPrestamo(rs.getInt("IdPrestamo"));
			           p.setIdEquipo(rs.getInt("IdEquipo"));
			           p.setIdSolicitante(rs.getInt("IdSolicitante"));
			           p.setFechaPrestamo(rs.getDate("FechaPrestamo").toLocalDate());
			           p.setHoraPrestamo(rs.getTime("HoraPrestamo").toLocalTime());
			           p.setFechaDevolucionPrevista(rs.getDate("FechaDevolucionPrevista").toLocalDate());
			           p.setObservaciones(rs.getString("Observaciones"));
			           p.setEstado(rs.getString("Estado"));
			           lista.add(p);}}
		   
		   catch (SQLException e) {System.out.println("Error al listar: " + e.getMessage());}
		   return lista;
		 }

	
	// ACTUALIZAR PRÉSTAMO ==============================================================================================================================================================
	public boolean actualizar(Prestamo prestamo)
		 { String sql = "UPDATE Prestamo SET IdEquipo=?, IdSolicitante=?, FechaPrestamo=?, HoraPrestamo=?, FechaDevolucionPrevista=?, Observaciones=?, "
				 	 + "Estado=? WHERE IdPrestamo=?";
		   try {Connection con = Conexion.getConexion();
		        PreparedStatement ps = con.prepareStatement(sql);
							      ps.setInt(1, prestamo.getIdEquipo());
							      ps.setInt(2, prestamo.getIdSolicitante());
							      ps.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
							      ps.setTime(4, Time.valueOf(prestamo.getHoraPrestamo()));
							      ps.setDate(5, Date.valueOf(prestamo.getFechaDevolucionPrevista()));
							      ps.setString(6, prestamo.getObservaciones());
							      ps.setString(7, prestamo.getEstado());
							      ps.setInt(8, prestamo.getIdPrestamo());
							      ps.executeUpdate();
		   return true;}
		   
		   catch (SQLException e) {System.out.println("Error al actualizar: " + e.getMessage());
		   return false;}
		 }

	
	// ELIMINAR PRÉSTAMO ================================================================================================================================================================
	public boolean eliminar(int idPrestamo)
		 { String sql = "DELETE FROM Prestamo WHERE IdPrestamo=?";
		   try {Connection con = Conexion.getConexion();
		       PreparedStatement ps = con.prepareStatement(sql);
						         ps.setInt(1, idPrestamo);
						         ps.executeUpdate();
		       return true;}
		   
		   catch (SQLException e) {System.out.println("Error al eliminar: " + e.getMessage());
		   return false;}
		 }


	// BUSCAR PRÉSTAMO ==================================================================================================================================================================
	public ArrayList<Prestamo> buscar(String texto)
		 { ArrayList<Prestamo> lista = new ArrayList<>();
		   String sql = "SELECT * FROM Prestamo WHERE " +
		                "CAST(IdPrestamo AS VARCHAR) LIKE ? OR " +
		                "CAST(IdSolicitante AS VARCHAR) LIKE ? OR " +
		                "CAST(IdEquipo AS VARCHAR) LIKE ? OR " +
		                "Estado LIKE ? OR " +
		                "CAST(FechaPrestamo AS VARCHAR) LIKE ?";
		   try {Connection con = Conexion.getConexion();
		        PreparedStatement ps = con.prepareStatement(sql);
		        String busqueda = "%" + texto + "%";
							      ps.setString(1, busqueda);
							      ps.setString(2, busqueda);
							      ps.setString(3, busqueda);
							      ps.setString(4, busqueda);
							      ps.setString(5, busqueda);
			    ResultSet rs = ps.executeQuery();
			    while (rs.next()) {
			    Prestamo p = new Prestamo();
				         p.setIdPrestamo(rs.getInt("IdPrestamo"));
				         p.setIdEquipo(rs.getInt("IdEquipo"));
				         p.setIdSolicitante(rs.getInt("IdSolicitante"));
				         p.setFechaPrestamo(rs.getDate("FechaPrestamo").toLocalDate());
				         p.setHoraPrestamo(rs.getTime("HoraPrestamo").toLocalTime());
				         p.setFechaDevolucionPrevista(rs.getDate("FechaDevolucionPrevista").toLocalDate());
				         p.setObservaciones(rs.getString("Observaciones"));
				         p.setEstado(rs.getString("Estado"));
				lista.add(p);}}
			   
		   catch (SQLException e) {System.out.println("Error al buscar: " + e.getMessage());}
		   return lista;
	     }


	// VERIFICAR SI EQUIPO TIENE PRÉSTAMO ACTIVO ========================================================================================================================================
	public boolean tienePrestamoActivo(int idEquipo)
		 {String sql = "SELECT COUNT(*) FROM Prestamo WHERE " +
		                "IdEquipo=? AND Estado='Activo'";
		  try {Connection con = Conexion.getConexion();
		       PreparedStatement ps = con.prepareStatement(sql);
		       					 ps.setInt(1, idEquipo);
		       ResultSet rs = ps.executeQuery();
		       if(rs.next()){return rs.getInt(1) > 0;}}
		 
		  catch (SQLException e) {System.out.println("Error: " + e.getMessage());}
		  return false;
		 }


	// ANULAR PRÉSTAMO ==================================================================================================================================================================
	public boolean anularPrestamo(int idPrestamo)
		 { String sql = "UPDATE Prestamo SET Estado='Anulado' " +
		                "WHERE IdPrestamo=?";
		   try {Connection con = Conexion.getConexion();
		        PreparedStatement ps = con.prepareStatement(sql);
							     ps.setInt(1, idPrestamo);
							     ps.executeUpdate();
		   return true;}
		   
		   catch (SQLException e) {System.out.println("Error al anular: " + e.getMessage());
		   return false;}
		 }
	
	
	
}
