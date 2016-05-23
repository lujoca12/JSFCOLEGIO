/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.text.Normalizer;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
public class ClsArchivos implements Serializable{
    
    private String descripcion;
    private StreamedContent file;
   
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public ClsArchivos() {
    }

    public ClsArchivos(String descripcion, StreamedContent file) {
        this.descripcion = descripcion;
        this.file = file;        
    }    
}
