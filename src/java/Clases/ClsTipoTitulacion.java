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
public class ClsTipoTitulacion {    
    private int id;    
    private String displayName; 
    private String name;
    private boolean estado;

    public ClsTipoTitulacion() {
    }

    public ClsTipoTitulacion(int id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }

    public ClsTipoTitulacion(int id, String displayName, String name, boolean estado) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
