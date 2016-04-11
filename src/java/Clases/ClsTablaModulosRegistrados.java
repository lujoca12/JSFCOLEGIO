/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;

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
    private int creditos;
    private int idModulo;
    

    public ClsTablaModulosRegistrados(int idMaestria, String maestria, int idPromocion, String modulo, int idUsuario, String usuario, int creditos, int idModulo) {
        this.idMaestria = idMaestria;
        this.maestria = maestria;
        this.idPromocion = idPromocion;
        this.modulo = modulo;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.creditos = creditos;
        this.idModulo = idModulo;
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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    
}
