package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Titulacion generated by hbm2java
 */
public class Titulacion  implements java.io.Serializable {


     private int id;
     private Matricula matricula;
     private TipoTitulacion tipoTitulacion;
     private BigDecimal nota;
     private Date fechaInicio;
     private Date fechaFin;
     private Character estado;
     private Set proyectos = new HashSet(0);

    public Titulacion() {
    }

	
    public Titulacion(int id) {
        this.id = id;
    }
    public Titulacion(int id, Matricula matricula, TipoTitulacion tipoTitulacion, BigDecimal nota, Date fechaInicio, Date fechaFin, Character estado, Set proyectos) {
       this.id = id;
       this.matricula = matricula;
       this.tipoTitulacion = tipoTitulacion;
       this.nota = nota;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.estado = estado;
       this.proyectos = proyectos;
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
    public TipoTitulacion getTipoTitulacion() {
        return this.tipoTitulacion;
    }
    
    public void setTipoTitulacion(TipoTitulacion tipoTitulacion) {
        this.tipoTitulacion = tipoTitulacion;
    }
    public BigDecimal getNota() {
        return this.nota;
    }
    
    public void setNota(BigDecimal nota) {
        this.nota = nota;
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
    public Set getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Set proyectos) {
        this.proyectos = proyectos;
    }




}


