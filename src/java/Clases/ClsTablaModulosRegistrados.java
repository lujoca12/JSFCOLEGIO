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
public class ClsTablaModulosRegistrados implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int idMaestria;
    private String maestria;
    private int idPromocion;
    private String modulo;
    private int idUsuario;
    private String usuario;
    private String creditos;
    private int idModulo;
    private String n_modulo;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioExamen;
    private Date fechaFinExamen;
    private String totalHorasModulo;
    

    public ClsTablaModulosRegistrados(int idMaestria, String maestria, int idPromocion, String modulo, int idUsuario, String usuario, String creditos, int idModulo, String n_modulo, Date fechaInicio, Date fechaFin, Date fechaInicioExamen, Date fechaFinExamen, String totalHorasModulo) {
        this.idMaestria = idMaestria;
        this.maestria = maestria;
        this.idPromocion = idPromocion;
        this.modulo = modulo;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.creditos = creditos;
        this.idModulo = idModulo;
        this.n_modulo = n_modulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaInicioExamen = fechaInicioExamen;
        this.fechaFinExamen = fechaFinExamen;
        this.totalHorasModulo = totalHorasModulo;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public String getMaestria() {
        return maestria;
    }

    public void setMaestria(String maestria) {
        this.maestria = maestria;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getN_modulo() {
        return n_modulo;
    }

    public void setN_modulo(String n_modulo) {
        this.n_modulo = n_modulo;
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

    public Date getFechaInicioExamen() {
        return fechaInicioExamen;
    }

    public void setFechaInicioExamen(Date fechaInicioExamen) {
        this.fechaInicioExamen = fechaInicioExamen;
    }

    public Date getFechaFinExamen() {
        return fechaFinExamen;
    }

    public void setFechaFinExamen(Date fechaFinExamen) {
        this.fechaFinExamen = fechaFinExamen;
    }

    public String getTotalHorasModulo() {
        return totalHorasModulo;
    }

    public void setTotalHorasModulo(String totalHorasModulo) {
        this.totalHorasModulo = totalHorasModulo;
    }
    
    
}
