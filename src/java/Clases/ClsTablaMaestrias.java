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
public class ClsTablaMaestrias implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int idMaestria;
    private String descripcionM;
    private char estado;
    private int idPromocion;
    private int descripcionP;
    private Date fechaResolucion;
    private Date fechaInicio;
    private Date fechaFin;
    private int cupo;
    private int cuotas;
    private int idUsuario;
    private String nombresUsuarios;
    private String n_resolucion;

    public ClsTablaMaestrias(int idMaestria, String descripcionM, char estado, int idPromocion, int descripcionP, Date fechaResolucion, Date fechaInicio, Date fechaFin, int cupo, int cuotas, int idUsuario, String nombresUsuarios, String n_resolucion) {
        this.idMaestria = idMaestria;
        this.descripcionM = descripcionM;
        this.estado = estado;
        this.idPromocion = idPromocion;
        this.descripcionP = descripcionP;
        this.fechaResolucion = fechaResolucion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cupo = cupo;
        this.cuotas = cuotas;
        this.idUsuario = idUsuario;
        this.nombresUsuarios = nombresUsuarios;
        this.n_resolucion = n_resolucion;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public String getDescripcionM() {
        return descripcionM;
    }

    public void setDescripcionM(String descripcionM) {
        this.descripcionM = descripcionM;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
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

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios(String nombresUsuarios) {
        this.nombresUsuarios = nombresUsuarios;
    }

    public int getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(int descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getN_resolucion() {
        return n_resolucion;
    }

    public void setN_resolucion(String n_resolucion) {
        this.n_resolucion = n_resolucion;
    }
    
}
