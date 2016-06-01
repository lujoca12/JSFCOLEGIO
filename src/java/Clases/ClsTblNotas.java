/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author server
 */
public class ClsTblNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idModulo;
    private String descripMod;
    private String creditos;
    private int idNotas;
    private Double nota;
    private String observ;
    private String notaTexto;
    private Date fechaInicio;
    private Date fechaFin;

    public ClsTblNotas(int idModulo, String descripMod, String creditos, int idNotas, Double nota, String observ, String notaTexto, Date fechaInicio, Date fechaFin) {
        this.idModulo = idModulo;
        this.descripMod = descripMod;
        this.creditos = creditos;
        this.idNotas = idNotas;
        this.nota = nota;
        this.observ = observ;
        this.notaTexto = notaTexto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    
    public ClsTblNotas(int idModulo, String descripMod, String creditos, int idNotas, Double nota, String observ, String notaTexto) {
        this.idModulo = idModulo;
        this.descripMod = descripMod;
        this.creditos = creditos;
        this.idNotas = idNotas;
        this.nota = nota;
        this.observ = observ;
        this.notaTexto = notaTexto;
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

    
    public int getIdModulo() {
        return idModulo;
    }
    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }
    public String getDescripMod() {
        return descripMod;
    }
    public void setDescripMod(String descripMod) {
        this.descripMod = descripMod;
    }
    public String getCreditos() {
        return creditos;
    }
    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }
    public int getIdNotas() {
        return idNotas;
    }
    public void setIdNotas(int idNotas) {
        this.idNotas = idNotas;
    }
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public String getObserv() {
        return observ;
    }
    public void setObserv(String observ) {
        this.observ = observ;
    }
    public String getNotaTexto() {
        return notaTexto;
    }
    public void setNotaTexto(String notaTexto) {
        this.notaTexto = notaTexto;
    }  
}
