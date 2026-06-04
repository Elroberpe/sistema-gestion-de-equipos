package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Devolucion {
	
	private int idDevolucion;
	private int idPrestamo;
	private LocalDate fechaDevolucion;
	private LocalTime horaDevolucion;
	private String Observacion;
	
	
	
	public Devolucion() {
		
	}
	
	
	public Devolucion(int idPrestamo,
			String observacion) {
		
		this.idPrestamo = idPrestamo;
		this.Observacion = observacion;
	}
	public int getIdDevolucion() {
		return idDevolucion;
	}
	public void setIdDevolucion(int idDevolucion) {
		this.idDevolucion = idDevolucion;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public LocalTime getHoraDevolucion() {
		return horaDevolucion;
	}
	public void setHoraDevolucion(LocalTime horaDevolucion) {
		this.horaDevolucion = horaDevolucion;
	}
	public String getObservacion() {
		return Observacion;
	}
	public void setObservacion(String observacion) {
		Observacion = observacion;
	}
	
	
	
	
}
