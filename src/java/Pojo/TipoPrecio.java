package Pojo;
// Generated 28-oct-2016 20:56:56 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TipoPrecio generated by hbm2java
 */
public class TipoPrecio  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character estado;
     private Set precios = new HashSet(0);

    public TipoPrecio() {
    }

	
    public TipoPrecio(int id) {
        this.id = id;
    }
    public TipoPrecio(int id, String descripcion, Character estado, Set precios) {
       this.id = id;
       this.descripcion = descripcion;
       this.estado = estado;
       this.precios = precios;
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
    public Set getPrecios() {
        return this.precios;
    }
    
    public void setPrecios(Set precios) {
        this.precios = precios;
    }




}


