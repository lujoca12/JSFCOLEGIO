/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author server
 */
public class ClsMaestria {
    
    private int id;    
    private String displayName; 
    private String name;
    private int idPromocion;
     
    public ClsMaestria() {}
 
    public ClsMaestria(int id, String displayName, String name, int idPromocion) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.idPromocion = idPromocion;
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
    
     
    @Override
    public String toString() {
        return name;
    }
    
}
