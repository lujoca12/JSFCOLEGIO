package Pojo;
// Generated 02-nov-2016 10:39:37 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Permiso generated by hbm2java
 */
public class Permiso  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Integer padre;
     private String form;
     private Integer orden;
     private Character estado;
     private Set detallePermisos = new HashSet(0);

    public Permiso() {
    }

	
    public Permiso(int id) {
        this.id = id;
    }
    public Permiso(int id, String descripcion, Integer padre, String form, Integer orden, Character estado, Set detallePermisos) {
       this.id = id;
       this.descripcion = descripcion;
       this.padre = padre;
       this.form = form;
       this.orden = orden;
       this.estado = estado;
       this.detallePermisos = detallePermisos;
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
    public Integer getPadre() {
        return this.padre;
    }
    
    public void setPadre(Integer padre) {
        this.padre = padre;
    }
    public String getForm() {
        return this.form;
    }
    
    public void setForm(String form) {
        this.form = form;
    }
    public Integer getOrden() {
        return this.orden;
    }
    
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public Set getDetallePermisos() {
        return this.detallePermisos;
    }
    
    public void setDetallePermisos(Set detallePermisos) {
        this.detallePermisos = detallePermisos;
    }




}


