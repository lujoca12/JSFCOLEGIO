package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PonderacionFecha generated by hbm2java
 */
public class PonderacionFecha  implements java.io.Serializable {


     private int id;
     private Ponderaciones ponderaciones;
     private Date fechaInicio;
     private Date fechaFin;
     private Character estado;
     private Set notases = new HashSet(0);
     private Set asistencias = new HashSet(0);
     private Promocion promocion;

    public PonderacionFecha() {
    }

	
    public PonderacionFecha(int id, Promocion promocion) {
        this.id = id;
        this.promocion = promocion;
    }
    public PonderacionFecha(int id, Ponderaciones ponderaciones, Date fechaInicio, Date fechaFin, Character estado, Set notases, Set asistencias, Promocion promocion) {
       this.id = id;
       this.ponderaciones = ponderaciones;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.estado = estado;
       this.notases = notases;
       this.asistencias = asistencias;
       this.promocion = promocion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Ponderaciones getPonderaciones() {
        return this.ponderaciones;
    }
    
    public void setPonderaciones(Ponderaciones ponderaciones) {
        this.ponderaciones = ponderaciones;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public Set getNotases() {
        return this.notases;
    }
    
    public void setNotases(Set notases) {
        this.notases = notases;
    }
    public Set getAsistencias() {
        return this.asistencias;
    }
    
    public void setAsistencias(Set asistencias) {
        this.asistencias = asistencias;
    }

    public Promocion getPromocion() {
        return this.promocion;
    }
    
    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }


}


