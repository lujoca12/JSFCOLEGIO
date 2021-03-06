package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Modalidad generated by hbm2java
 */
public class Modalidad  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character estado;
     private Set seccions = new HashSet(0);

    public Modalidad() {
    }

	
    public Modalidad(int id) {
        this.id = id;
    }
    public Modalidad(int id, String descripcion, Character estado, Set seccions) {
       this.id = id;
       this.descripcion = descripcion;
       this.estado = estado;
       this.seccions = seccions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public Set getSeccions() {
        return this.seccions;
    }
    
    public void setSeccions(Set seccions) {
        this.seccions = seccions;
    }




}


