package DTO;

public class PrestamoListaDTO {
   
	//ATRIBUTOS ===========================================================================================================
    private int idPrestamo;
    private String nombreSolicitante;
    private String apellidoSolicitante;
    private String codigoEquipo;
    private String descripcionEquipo;
    private String fechaPrestamo;
    private String fechaDevolucionPrevista;
    private String estado;

    //CONSTRUCTOR VACIO ===========================================================================================================
    public PrestamoListaDTO() {}

    //CONSTRUCTOR =================================================================================================================
    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }

    //GET Y SET =================================================================================================================
    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String n) { this.nombreSolicitante = n; }

    public String getApellidoSolicitante() { return apellidoSolicitante; }
    public void setApellidoSolicitante(String a) { this.apellidoSolicitante = a; }

    public String getCodigoEquipo() { return codigoEquipo; }
    public void setCodigoEquipo(String c) { this.codigoEquipo = c; }

    public String getDescripcionEquipo() { return descripcionEquipo; }
    public void setDescripcionEquipo(String d) { this.descripcionEquipo = d; }

    public String getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(String f) { this.fechaPrestamo = f; }

    public String getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }
    public void setFechaDevolucionPrevista(String f) { this.fechaDevolucionPrevista = f; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
