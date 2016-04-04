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
public class ClsTablaPermisos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String descripcion;
    private String contenedor;
    private String ruta;
    private int orden;
    private String type;
    private int padre;

    public ClsTablaPermisos(int id, String descripcion, String contenedor, String ruta, int orden, String type, int padre) {
        this.id = id;
        this.descripcion = descripcion;
        this.contenedor = contenedor;
        this.ruta = ruta;
        this.orden = orden;
        this.type = type;
        this.padre = padre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }
    
    
    @Override
    public String toString() {
        return descripcion;
    }
    
}
