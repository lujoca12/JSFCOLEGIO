/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;
import static org.joda.time.format.ISODateTimeFormat.date;

/**
 *
 * @author SERVER
 */
public class ClsTitulacion {
     private int id;    
    private Double nota; 
    private Date fechaInicio;
    private Date fechaFin;
    private int idtipotitulacion;
    private int idmaestria;
    private int idmatricula;
    private int idestudiante;
    private String maestria;
    private String estudiante;
    private String tipotitulacion;
    private boolean estado;

    public ClsTitulacion(int id, Double nota, Date fechaInicio, Date fechaFin, int idmatricula, boolean estado) {
        this.id = id;
        this.nota = nota;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idmatricula = idmatricula;
        this.estado = estado;
    }

    public ClsTitulacion(int id, Double nota, Date fechaInicio, Date fechaFin, int idtipotitulacion, int idmatricula, String maestria, String estudiante) {
        this.id = id;
        this.nota = nota;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idtipotitulacion = idtipotitulacion;
        this.idmatricula = idmatricula;
        this.maestria = maestria;
        this.estudiante = estudiante;
    }

    public ClsTitulacion(int id, Double nota, Date fechaInicio, Date fechaFin, int idtipotitulacion, int idmaestria, int idmatricula, int idestudiante, String maestria, String estudiante, String tipotitulacion, boolean estado) {
        this.id = id;
        this.nota = nota;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idtipotitulacion = idtipotitulacion;
        this.idmaestria = idmaestria;
        this.idmatricula = idmatricula;
        this.idestudiante = idestudiante;
        this.maestria = maestria;
        this.estudiante = estudiante;
        this.tipotitulacion = tipotitulacion;
        this.estado = estado;
    }
    
    
  

    
    
    public String getTipotitulacion() {
        return tipotitulacion;
    }
    public void setTipotitulacion(String tipotitulacion) {
        this.tipotitulacion = tipotitulacion;
    }   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getIdtipotitulacion() {
        return idtipotitulacion;
    }
    public void setIdtipotitulacion(int idtipotitulacion) {
        this.idtipotitulacion = idtipotitulacion;
    }
    public int getIdmaestria() {
        return idmaestria;
    }
    public void setIdmaestria(int idmaestria) {
        this.idmaestria = idmaestria;
    }
    public int getIdmatricula() {
        return idmatricula;
    }
    public void setIdmatricula(int idmatricula) {
        this.idmatricula = idmatricula;
    }
    public int getIdestudiante() {
        return idestudiante;
    }
    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }
    public String getMaestria() {
        return maestria;
    }
    public void setMaestria(String maestria) {
        this.maestria = maestria;
    }
    public String getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
