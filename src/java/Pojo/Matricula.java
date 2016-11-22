package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Matricula generated by hbm2java
 */
public class Matricula  implements java.io.Serializable {


     private int id;
     private SolicitudInscripcion solicitudInscripcion;
     private Date fechaMatricula;
     private Integer dscColegiatura;
     private Character estado;
     private String NMatricula;
     private Date fechaGraduacion;
     private Character estadoCurso;
     private Set asistencias = new HashSet(0);
     private Set titulacions = new HashSet(0);
     private Set pagos = new HashSet(0);
     private Set notases = new HashSet(0);

    public Matricula() {
    }

	
    public Matricula(int id, SolicitudInscripcion solicitudInscripcion) {
        this.id = id;
        this.solicitudInscripcion = solicitudInscripcion;
    }
    public Matricula(int id, SolicitudInscripcion solicitudInscripcion, Date fechaMatricula, Integer dscColegiatura, Character estado, String NMatricula, Date fechaGraduacion, Character estadoCurso, Set asistencias, Set titulacions, Set pagos, Set notases) {
       this.id = id;
       this.solicitudInscripcion = solicitudInscripcion;
       this.fechaMatricula = fechaMatricula;
       this.dscColegiatura = dscColegiatura;
       this.estado = estado;
       this.NMatricula = NMatricula;
       this.fechaGraduacion = fechaGraduacion;
       this.estadoCurso = estadoCurso;
       this.asistencias = asistencias;
       this.titulacions = titulacions;
       this.pagos = pagos;
       this.notases = notases;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public SolicitudInscripcion getSolicitudInscripcion() {
        return this.solicitudInscripcion;
    }
    
    public void setSolicitudInscripcion(SolicitudInscripcion solicitudInscripcion) {
        this.solicitudInscripcion = solicitudInscripcion;
    }
    public Date getFechaMatricula() {
        return this.fechaMatricula;
    }
    
    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }
    public Integer getDscColegiatura() {
        return this.dscColegiatura;
    }
    
    public void setDscColegiatura(Integer dscColegiatura) {
        this.dscColegiatura = dscColegiatura;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public String getNMatricula() {
        return this.NMatricula;
    }
    
    public void setNMatricula(String NMatricula) {
        this.NMatricula = NMatricula;
    }
    public Date getFechaGraduacion() {
        return this.fechaGraduacion;
    }
    
    public void setFechaGraduacion(Date fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }
    public Character getEstadoCurso() {
        return this.estadoCurso;
    }
    
    public void setEstadoCurso(Character estadoCurso) {
        this.estadoCurso = estadoCurso;
    }
    public Set getAsistencias() {
        return this.asistencias;
    }
    
    public void setAsistencias(Set asistencias) {
        this.asistencias = asistencias;
    }
    public Set getTitulacions() {
        return this.titulacions;
    }
    
    public void setTitulacions(Set titulacions) {
        this.titulacions = titulacions;
    }
    public Set getPagos() {
        return this.pagos;
    }
    
    public void setPagos(Set pagos) {
        this.pagos = pagos;
    }
    public Set getNotases() {
        return this.notases;
    }
    
    public void setNotases(Set notases) {
        this.notases = notases;
    }




}


