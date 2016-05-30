/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author server
 */
public class ClsMaestria {
    
    private int id;    
    private String displayName; 
    private String name;
    private int idPromocion;
    private int añoInicio;
    private int añoFin;
    private Date fechaInicioMaestria;
    private Date fechaFinMaestria;
     
    public ClsMaestria() {}
 
    public ClsMaestria(int id, String displayName, String name, int idPromocion, int añoInicio, int añoFin, Date fechaInicioMaestria, Date fechaFinMaestria) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.idPromocion = idPromocion;
        this.añoInicio = añoInicio;
        this.añoFin = añoFin;
        this.fechaInicioMaestria = fechaInicioMaestria;
        this.fechaFinMaestria = fechaFinMaestria;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getDisplayName() {
        return displayName;
    }
 
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getAñoInicio() {
        return añoInicio;
    }

    public void setAñoInicio(int añoInicio) {
        this.añoInicio = añoInicio;
    }

    public int getAñoFin() {
        return añoFin;
    }

    public void setAñoFin(int añoFin) {
        this.añoFin = añoFin;
    }

    public Date getFechaInicioMaestria() {
        return fechaInicioMaestria;
    }

    public void setFechaInicioMaestria(Date fechaInicioMaestria) {
        this.fechaInicioMaestria = fechaInicioMaestria;
    }

    public Date getFechaFinMaestria() {
        return fechaFinMaestria;
    }

    public void setFechaFinMaestria(Date fechaFinMaestria) {
        this.fechaFinMaestria = fechaFinMaestria;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
