package Modelo;

public class Solicitante {
    
    private int idSolicitante;
    private String dni;
    private String nombre;
    private String apellidos;
    private String tipo;
    private String salonCurso;
    private String celular;
    private String correo;

    public Solicitante() {}

    public int getIdSolicitante() { return idSolicitante; }
    public void setIdSolicitante(int idSolicitante) { this.idSolicitante = idSolicitante; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSalonCurso() { return salonCurso; }
    public void setSalonCurso(String salonCurso) { this.salonCurso = salonCurso; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}