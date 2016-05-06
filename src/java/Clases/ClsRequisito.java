/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author chiti
 */
public class ClsRequisito {
    private int id;
    private String descripcion;
    private String tipoArchivo;

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
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

    public ClsRequisito() {
    }

    public ClsRequisito(int id, String descripcion,String tipoArchivo) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipoArchivo=tipoArchivo;
    }

    public ClsRequisito(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    

    public ClsRequisito(int id) {
        this.id = id;
    }
    
    
    
}
