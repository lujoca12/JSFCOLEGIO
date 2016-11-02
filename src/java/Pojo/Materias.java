package Pojo;
// Generated 02-nov-2016 10:39:37 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Materias generated by hbm2java
 */
public class Materias  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private BigDecimal creditos;
     private Character estado;
     private Set modulos = new HashSet(0);

    public Materias() {
    }

	
    public Materias(int id) {
        this.id = id;
    }
    public Materias(int id, String descripcion, BigDecimal creditos, Character estado, Set modulos) {
       this.id = id;
       this.descripcion = descripcion;
       this.creditos = creditos;
       this.estado = estado;
       this.modulos = modulos;
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
    public BigDecimal getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
    }
    public Set getModulos() {
        return this.modulos;
    }
    
    public void setModulos(Set modulos) {
        this.modulos = modulos;
    }




}


