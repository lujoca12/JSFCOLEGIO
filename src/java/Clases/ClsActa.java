/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author SERVER
 */
public class ClsActa {
    
    private int idActa;
    private int idMaestria;
    private int idtipoActa;
    private String numeracion;
    private String maestriaD;
    private String ActaD;
    private Character estado;

    public ClsActa(int idActa, int idMaestria, int idtipoActa, String numeracion, String maestriaD, String ActaD, Character estado) {
        this.idActa = idActa;
        this.idMaestria = idMaestria;
        this.idtipoActa = idtipoActa;
        this.numeracion = numeracion;
        this.maestriaD = maestriaD;
        this.ActaD = ActaD;
        this.estado = estado;
    }

    public int getIdActa() {
        return idActa;
    }

    public void setIdActa(int idActa) {
        this.idActa = idActa;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public int getIdtipoActa() {
        return idtipoActa;
    }

    public void setIdtipoActa(int idtipoActa) {
        this.idtipoActa = idtipoActa;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getMaestriaD() {
        return maestriaD;
    }

    public void setMaestriaD(String maestriaD) {
        this.maestriaD = maestriaD;
    }

    public String getActaD() {
        return ActaD;
    }

    public void setActaD(String ActaD) {
        this.ActaD = ActaD;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
