package Pojo;
// Generated 30-oct-2016 21:58:04 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Ponderaciones generated by hbm2java
 */
public class Ponderaciones  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character clave;
     private BigDecimal ponderacion;
     private Character estado;
     private Set notases = new HashSet(0);
     private Set asistencias = new HashSet(0);

    public Ponderaciones() {
    }

	
    public Ponderaciones(int id) {
        this.id = id;
    }
    public Ponderaciones(int id, String descripcion, Character clave, BigDecimal ponderacion, Character estado, Set notases, Set asistencias) {
       this.id = id;
       this.descripcion = descripcion;
       this.clave = clave;
       this.ponderacion = ponderacion;
       this.estado = estado;
       this.notases = notases;
       this.asistencias = asistencias;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Character getClave() {
        return this.clave;
    }
    
    public void setClave(Character clave) {
        this.clave = clave;
    }
    public BigDecimal getPonderacion() {
        return this.ponderacion;
    }
    
    public void setPonderacion(BigDecimal ponderacion) {
        this.ponderacion = ponderacion;
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




}


