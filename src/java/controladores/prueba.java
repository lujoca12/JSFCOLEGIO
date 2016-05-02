/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author chiti
 */
@Named(value = "prueba")
@ViewScoped
public class prueba implements Serializable {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Creates a new instance of prueba
     */
    public prueba() {
    }

    public void handleFileUpload(FileUploadEvent event) {

        UploadedFile file = event.getFile();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateobj = new Date();
        String nombreFecha = (df.format(dateobj).replaceAll(":", "-"));       
         Path path = Paths.get("D:\\Postgrado\\inscripciones\\requisitos\\"+nombreFecha.trim());
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        String filename = file.getFileName();
//         String extension = f.getContentType();
        Path ruta = Paths.get(path +"\\"+ filename);

        try (InputStream input = file.getInputstream()) {
            Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Succesful", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void guardarPrime() {
//        try {
//            //guardar los datos
//            //???????????
//            //guardando los requisitos
//            for (UploadedFile f : files) {
//                if (f != null) {
//                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date dateobj = new Date();
//
//                    String nombreFecha = ("chiting" + "-" + df.format(dateobj).replaceAll(":", "-")).trim();
//                    File directorio = new File("d:/Postgrado/inscripciones/requisitos/" + nombreFecha);
//                    if (!directorio.exists()) {
//                        directorio.mkdir();
//                    }
//
//                    String filename = f.getFileName();
//                    // String extension = f.getContentType();
//                    Path ruta = Paths.get(directorio + filename);
//
//                    try (InputStream input = f.getInputstream()) {
//                        Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
//                    }
//                    FacesMessage message = new FacesMessage("Succesful", filename + " is uploaded.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(subirArchivo.class.getName()).log(Level.SEVERE, null, ex);
//            FacesMessage message = new FacesMessage("Succesful", ex.toString());
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }

    }

    public void guardar() {
//        try {
//            for (Part pa : archivos) {
//                Path destino = Paths.get("d:/temp/" + pa.getSubmittedFileName());
//                InputStream bytes = null;
//                if (pa != null) {
//                    bytes = pa.getInputStream();
//                    Files.copy(bytes, destino);
//                    FacesMessage message = new FacesMessage("Succesful", " is uploaded.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//
//                }
//            }
//        } catch (Exception ex) {
//            FacesMessage message = new FacesMessage("Succesful", ex.toString());
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
    }
}
