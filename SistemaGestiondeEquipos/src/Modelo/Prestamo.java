package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Prestamo {
	
	//ATRIBUTOS ==============================================================================
	private int idPrestamo;
	private int idEquipo;
	private int idSolicitante;
	private LocalDate fechaPrestamo;
	private LocalTime horaPrestamo;
	private LocalDate fechaDevolucionPrevista;
	private String observaciones;
	private String estado;

	//CONSTRUCTORES ==========================================================================
	public Prestamo() {}	
	
	public Prestamo(int idPrestamo, int idEquipo, int idSolicitante, LocalDate fechaPrestamo, LocalTime horaPrestamo,
					LocalDate fechaDevolucionPrevista, String observaciones, String estado)
    {
		   this.idPrestamo = idPrestamo;
	       this.idEquipo = idEquipo;
		   this.idSolicitante = idSolicitante;
		   this.fechaPrestamo = fechaPrestamo;
		   this.horaPrestamo = horaPrestamo;
		   this.fechaDevolucionPrevista = fechaDevolucionPrevista;
		   this.observaciones = observaciones;
		   this.estado = estado;
	}

	//GETTERS Y SETTERS ==========================================================================
	public int getIdPrestamo() {return idPrestamo;}
	public void setIdPrestamo(int idPrestamo) {this.idPrestamo=idPrestamo;}

	public int getIdEquipo() {return idEquipo;}
	public void setIdEquipo(int idEquipo) {this.idEquipo=idEquipo;}
	
	public int getIdSolicitante() {return idSolicitante;}
	public void setIdSolicitante(int idSolicitante) {this.idSolicitante=idSolicitante;}
	
	public LocalDate getFechaPrestamo() {return fechaPrestamo;}
	public void setFechaPrestamo(LocalDate fechaPrestamo) {this.fechaPrestamo=fechaPrestamo;}
	
	public LocalTime getHoraPrestamo() {return horaPrestamo;}
	public void setHoraPrestamo(LocalTime horaPrestamo) {this.horaPrestamo=horaPrestamo;}
	
	public LocalDate getFechaDevolucionPrevista() {return fechaDevolucionPrevista;}
	public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {this.fechaDevolucionPrevista=fechaDevolucionPrevista;}
	
	public String getObservaciones() {return observaciones;}
	public void setObservaciones(String observaciones) {this.observaciones=observaciones;}
	
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado=estado;}
}
