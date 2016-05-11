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
import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author chiti
 */
@Named(value = "prueba")
@ViewScoped
public class prueba implements Serializable {

    private String nombre;
    private Part archivo;
    private Collection<Part> archivos;
    private UploadedFile file;
    private List<UploadedFile> files = new ArrayList<>();

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        this.files.add(file);
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }
    

   
    

    public void save() {
        try {
            for(UploadedFile f : files){
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date dateobj = new Date();
//                    String nombreFecha = ("chiting" + "-" + df.format(dateobj).replaceAll(":", "-")).trim();
//                    File directorio = new File("d:/Postgrado/inscripciones/requisitos/" + nombreFecha);
//                    if (!directorio.exists()) {
//                        directorio.mkdir();
//                    }
//
                    String filename = f.getFileName();
                     String extension = FilenameUtils.getExtension(filename);
//                    Path ruta = Paths.get(directorio + filename);
//
//                    try (InputStream input = f.getInputstream()) {
//                        Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
//                    }
                FacesMessage message = new FacesMessage("Succesful", filename + " is uploaded.-- "+extension);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Error", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public Collection<Part> getArchivos() {
        return archivos;
    }

    public void setArchivos(Collection<Part> archivos) {
        this.archivos = archivos;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivos.add(archivo);
    }

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
        Path path = Paths.get("D:\\Postgrado\\inscripciones\\requisitos\\" + nombreFecha.trim());
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
        Path ruta = Paths.get(path + "\\" + filename);

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
        try {

            for (Part pa : archivos) {
                Path destino = Paths.get("d:/temp/" + archivo.getSubmittedFileName());
                InputStream bytes = null;
                if (archivo != null) {
                    bytes = archivo.getInputStream();
                    Files.copy(bytes, destino);
                    FacesMessage message = new FacesMessage("Succesful", " is uploaded.");
                    FacesContext.getCurrentInstance().addMessage(null, message);

                }
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Succesful", ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void pf(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", " is PF." + event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void asd() {
        FacesMessage message = new FacesMessage("Succesful", " is PF.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
