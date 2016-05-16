package Pojo;
// Generated 16-may-2016 9:34:07 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Asistencia generated by hbm2java
 */
public class Asistencia  implements java.io.Serializable {


     private int id;
     private Matricula matricula;
     private Modulo modulo;
     private Character estado;
     private String observacion;
     private Date fecha;
     private String usuario;

    public Asistencia() {
    }

	
    public Asistencia(int id, Matricula matricula, Modulo modulo) {
        this.id = id;
        this.matricula = matricula;
        this.modulo = modulo;
    }
    public Asistencia(int id, Matricula matricula, Modulo modulo, Character estado, String observacion, Date fecha, String usuario) {
       this.id = id;
       this.matricula = matricula;
       this.modulo = modulo;
       this.estado = estado;
       this.observacion = observacion;
       this.fecha = fecha;
       this.usuario = usuario;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Matricula getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    public Modulo getModulo() {
        return this.modulo;
    }
    
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }




}


