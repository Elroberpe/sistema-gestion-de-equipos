package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import DTO.PrestamoActivoDTO;
import Modelo.Devolucion;

public class DevolucionDao {

		public ArrayList<PrestamoActivoDTO> listarPrestamosActivos(){
			ArrayList<PrestamoActivoDTO> lista = new ArrayList<>();
			
			String consultaSql = """
						SELECT 
							P.IdPrestamo,
							E.IdEquipo,
							E.Codigo,
							E.Nombre AS Equipo,
							S.Nombre+ ' '+ S.Apellidos AS Solicitante,
							S.DNI,
							P.FechaPrestamo,
                            P.FechaDevolucionPrevista,
                            P.Estado
                        FROM Prestamo P
					    INNER JOIN Equipo E ON P.IdEquipo = E.IdEquipo
                        INNER JOIN Solicitante S ON P.IdSolicitante = S.IdSolicitante
                        WHERE P.Estado IN ('Activo', 'Vencido')
                        ORDER BY P.IdPrestamo DESC 
					""";
			try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(consultaSql);
				ResultSet rs = ps.executeQuery();
				){
				
				while(rs.next()) {
					PrestamoActivoDTO prestamoActivo= new PrestamoActivoDTO();
					
					prestamoActivo.setIdPrestamo(rs.getInt("idPrestamo"));
					prestamoActivo.setIdEquipo(rs.getInt("IdEquipo"));
					prestamoActivo.setCodigoEquipo(rs.getString("Codigo"));
					prestamoActivo.setNombreEquipo(rs.getString("Equipo"));
					prestamoActivo.setSolicitante(rs.getString("Solicitante"));
					prestamoActivo.setDNI(rs.getString("DNI"));
					
					Date fechaPrestamo = rs.getDate("fechaPrestamo");
					Date fechaPrevista = rs.getDate("FechaDevolucionPrevista");
					
					if(fechaPrestamo != null) {
						prestamoActivo.setFechaPrestamo(fechaPrestamo.toLocalDate());
					}
					
					if (fechaPrevista != null) {
	                    prestamoActivo.setFechaDevolucionPrevista(fechaPrevista.toLocalDate());
	                }
					
					prestamoActivo.setEstadoPrestamo(rs.getString("Estado"));
					lista.add(prestamoActivo);
					
				}
				
				
			} catch(SQLException e) {

				JOptionPane.showMessageDialog(null, "Error al listar prestamos activos: " + e.getMessage()) ;
			}
			
			return lista;
			
			
			
			
		}
		
		public ArrayList<PrestamoActivoDTO> buscarPrestamoActivo(String idPrestamo, String dni , String codigoEquipo){
			ArrayList<PrestamoActivoDTO> lista = new ArrayList<>();
			StringBuilder consulta= new StringBuilder("""
			        SELECT 
		            P.IdPrestamo,
		            E.IdEquipo,
		            E.Codigo,
		            E.Nombre AS Equipo,
		            S.Nombre + ' ' + S.Apellidos AS Solicitante,
		            S.DNI,
		            P.FechaPrestamo,
		            P.FechaDevolucionPrevista,
		            P.Estado
		        FROM Prestamo P
		        INNER JOIN Equipo E ON P.IdEquipo = E.IdEquipo
		        INNER JOIN Solicitante S ON P.IdSolicitante = S.IdSolicitante
		        WHERE P.Estado IN ('Activo', 'Vencido')
		    """);
			ArrayList<Object> parametros = new ArrayList<>();
			
			if(idPrestamo !=null && !idPrestamo.trim().isEmpty()) {
				consulta.append("AND P.IdPrestamo = ? ");
				parametros.add(Integer.parseInt(idPrestamo.trim()));
			}
			
			if(dni != null && !dni.trim().isEmpty()) {
				consulta.append("AND S.DNI = ? ");
				parametros.add(dni.trim());
			}
			
			if (codigoEquipo != null && !codigoEquipo.trim().isEmpty()) {
		        consulta.append(" AND E.Codigo = ? ");
		        parametros.add(codigoEquipo.trim());
		    }

		    consulta.append(" ORDER BY P.IdPrestamo DESC ");
			

			
			try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(consulta.toString())
				){
				
				for (int i= 0; i< parametros.size();i++) {
					ps.setObject(i+1, parametros.get(i));
				}
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					PrestamoActivoDTO prestamoActivo = new PrestamoActivoDTO();
					
					prestamoActivo.setIdPrestamo(rs.getInt("idPrestamo"));
					prestamoActivo.setIdEquipo(rs.getInt("idEquipo"));
					prestamoActivo.setCodigoEquipo(rs.getString("Codigo"));
					prestamoActivo.setNombreEquipo(rs.getString("Equipo"));
					prestamoActivo.setSolicitante(rs.getString("Solicitante"));
					prestamoActivo.setDNI(rs.getString("DNI").trim());
					
					Date fechaPrestamo = rs.getDate("FechaPrestamo");
		            Date fechaPrevista = rs.getDate("FechaDevolucionPrevista");

		            if (fechaPrestamo != null) {
		                prestamoActivo.setFechaPrestamo(fechaPrestamo.toLocalDate());
		            }

		            if (fechaPrevista != null) {
		                prestamoActivo.setFechaDevolucionPrevista(fechaPrevista.toLocalDate());
		            }
		            
		            prestamoActivo.setEstadoPrestamo(rs.getString("Estado"));

		            lista.add(prestamoActivo);
					
				}
			} catch(SQLException e) {
				System.out.println("Error al buscar prestamo: " + e.getMessage());
				System.out.println("Error al buscar prestamo: " + e.getMessage());
			}
			return lista;
			
		}

	
		public void actualizarEstadoPrestamoVencidos() {
			
			String consulta = """
					UPDATE Prestamo 
					SET Estado = 'Vencido'
					WHERE Estado = 'Activo' AND FechaDevolucionPrevista < CAST(GETDATE () as DATE)
					""";
			try (
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(consulta)
					){		
				ps.executeUpdate();		
			}
			
			catch (SQLException e) {
				System.out.println("Error al actualizar prestamos vencidos: " + e.getMessage());
			}
			
					
		}
		
		public boolean registrarDevolucion(PrestamoActivoDTO prestamo , Devolucion devolucion) {
			
			String consultaInsertarDevolucion = """
					INSERT INTO Devolucion 
					(idPrestamo, FechaDevolucion, HoraDevolucion,Observacion)
					VALUES
					(?,CAST(GETDATE() AS DATE), CAST(GETDATE() AS TIME (0)), ?)
					""";
			String consultaCambiarEstadoPrestamo = """
					UPDATE Prestamo
					SET Estado = 'Devuelto'
					WHERE IdPrestamo = ?
					""";
			String consultaCambiarEstadoEquio = """
					UPDATE Equipo
					SET estado = 'Disponible'
					WHERE idEquipo = ?;
					""";
			
			Connection con = null;
			
			try{
				con = Conexion.getConexion();
				
				con.setAutoCommit(false);
				
				PreparedStatement psDevolucion = con.prepareStatement(consultaInsertarDevolucion);
				psDevolucion.setInt(1, devolucion.getIdPrestamo());
				psDevolucion.setString(2, devolucion.getObservacion());
				psDevolucion.executeUpdate();
				
				PreparedStatement psPrestamo = con.prepareStatement(consultaCambiarEstadoPrestamo);
				psPrestamo.setInt(1, prestamo.getIdPrestamo());
				psPrestamo.executeUpdate();
				
				PreparedStatement psEquipo = con.prepareStatement(consultaCambiarEstadoEquio);
				psEquipo.setInt(1, prestamo.getIdEquipo());
				psPrestamo.executeUpdate();
				
				con.commit();
				return true;
				
			} catch (SQLException e) {
		        try {
		            if (con != null) {
		                con.rollback();
		            }
		        } catch (SQLException ex) {
		            System.out.println("Error al hacer rollback: " + ex.getMessage());
		        }

		        System.out.println("Error al registrar devolución: " + e.getMessage());
		        return false;

		    } finally {
		        try {
		            if (con != null) {
		                con.setAutoCommit(true);
		                con.close();
		            }
		        } catch (SQLException e) {
		            System.out.println("Error al cerrar conexión: " + e.getMessage());
		        }
		    }
		}
	
	
}
