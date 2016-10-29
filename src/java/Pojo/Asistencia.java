package Pojo;
// Generated 28-oct-2016 20:56:56 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Asistencia generated by hbm2java
 */
public class Asistencia  implements java.io.Serializable {


     private int id;
     private Asistencia asistencia;
     private Matricula matricula;
     private Modulo modulo;
     private Ponderaciones ponderaciones;
     private Character estado;
     private String observacion;
     private Date fecha;
     private String usuario;
     private BigDecimal horas_asistidas;
     private Character asistencia_evaluacion;

    public Asistencia() {
    }

	
    public Asistencia(int id, Asistencia asistencia, Matricula matricula, Modulo modulo, Ponderaciones ponderaciones) {
        this.id = id;
        this.asistencia = asistencia;
        this.matricula = matricula;
        this.modulo = modulo;
        this.ponderaciones = ponderaciones;
    }
    public Asistencia(int id, Asistencia asistencia, Matricula matricula, Modulo modulo,Ponderaciones ponderaciones, Character estado, String observacion, Date fecha, String usuario, BigDecimal horas_asistidas, Character asistencia_evaluacion) {
       this.id = id;
       this.asistencia = asistencia;
       this.matricula = matricula;
       this.modulo = modulo;
       this.ponderaciones = ponderaciones;
       this.estado = estado;
       this.observacion = observacion;
       this.fecha = fecha;
       this.usuario = usuario;
       this.horas_asistidas = horas_asistidas;
       this.asistencia_evaluacion = asistencia_evaluacion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Asistencia getAsistencia() {
        return this.asistencia;
    }
    
    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
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

    public BigDecimal getHoras_asistidas() {
        return horas_asistidas;
    }

    public void setHoras_asistidas(BigDecimal horas_asistidas) {
        this.horas_asistidas = horas_asistidas;
    }

    public Character getAsistencia_evaluacion() {
        return asistencia_evaluacion;
    }

    public void setAsistencia_evaluacion(Character asistencia_evaluacion) {
        this.asistencia_evaluacion = asistencia_evaluacion;
    }

    public Ponderaciones getPonderaciones() {
        return ponderaciones;
    }

    public void setPonderaciones(Ponderaciones ponderaciones) {
        this.ponderaciones = ponderaciones;
    }
    
}


