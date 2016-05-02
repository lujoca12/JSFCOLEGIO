/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "subirArchivo")
@ViewScoped
public class subirArchivo implements Serializable {

    /**
     * Creates a new instance of subirArchivo
     */
    private List<UploadedFile> files;   
    private String nombre;
    private UploadedFile file;
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file=file;
        this.files.add(file);
    }
        

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

    

    public subirArchivo() {
    }

    public void guardarPrime() {
        try {
            //guardar los datos
            //???????????
            //guardando los requisitos
           for(UploadedFile f : files)
           {
                if (f != null) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateobj = new Date();
                   
                    String nombreFecha = ("chiting"+"-"+ df.format(dateobj).replaceAll(":", "-")).trim();
                    File directorio = new File("d:/Postgrado/inscripciones/requisitos/" + nombreFecha);
                    if (!directorio.exists()) {
                        directorio.mkdir();
                    }                    
                    
                    String filename = f.getFileName();
                    // String extension = f.getContentType();
                    Path ruta = Paths.get(directorio+filename);
                   
                try (InputStream input = f.getInputstream()) {
                    Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
                }
                    FacesMessage message = new FacesMessage("Succesful", filename + " is uploaded.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
           }
           
        } catch (Exception ex) {
            Logger.getLogger(subirArchivo.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Succesful", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

}
