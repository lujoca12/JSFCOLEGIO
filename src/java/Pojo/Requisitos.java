package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Requisitos generated by hbm2java
 */
public class Requisitos  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Character estado;
     private Character respaldo;
     private String tipoArchivo;
     private String formato;
     private Set requisitosPromos = new HashSet(0);

    public Requisitos() {
    }

	
    public Requisitos(int id) {
        this.id = id;
    }
    public Requisitos(int id, String descripcion, Character estado, Character respaldo, String tipoArchivo, String formato, Set requisitosPromos) {
       this.id = id;
       this.descripcion = descripcion;
       this.estado = estado;
       this.respaldo = respaldo;
       this.tipoArchivo = tipoArchivo;
       this.formato = formato;
       this.requisitosPromos = requisitosPromos;
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
    public Character getRespaldo() {
        return this.respaldo;
    }
    
    public void setRespaldo(Character respaldo) {
        this.respaldo = respaldo;
    }
    public String getTipoArchivo() {
        return this.tipoArchivo;
    }
    
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    public String getFormato() {
        return this.formato;
    }
    
    public void setFormato(String formato) {
        this.formato = formato;
    }
    public Set getRequisitosPromos() {
        return this.requisitosPromos;
    }
    
    public void setRequisitosPromos(Set requisitosPromos) {
        this.requisitosPromos = requisitosPromos;
    }




}


