package Pojo;
// Generated 21-nov-2016 21:32:12 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Pais generated by hbm2java
 */
public class Pais  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private String codigoLetra;
     private Integer codigo;
     private Set universidads = new HashSet(0);
     private Set provincias = new HashSet(0);

    public Pais() {
    }

	
    public Pais(int id) {
        this.id = id;
    }
    public Pais(int id, String descripcion, String codigoLetra, Integer codigo, Set universidads, Set provincias) {
       this.id = id;
       this.descripcion = descripcion;
       this.codigoLetra = codigoLetra;
       this.codigo = codigo;
       this.universidads = universidads;
       this.provincias = provincias;
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
    public String getCodigoLetra() {
        return this.codigoLetra;
    }
    
    public void setCodigoLetra(String codigoLetra) {
        this.codigoLetra = codigoLetra;
    }
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public Set getUniversidads() {
        return this.universidads;
    }
    
    public void setUniversidads(Set universidads) {
        this.universidads = universidads;
    }
    public Set getProvincias() {
        return this.provincias;
    }
    
    public void setProvincias(Set provincias) {
        this.provincias = provincias;
    }




}


