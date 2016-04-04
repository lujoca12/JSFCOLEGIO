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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
@SessionScoped
public class subirArchivo implements Serializable {

    /**
     * Creates a new instance of subirArchivo
     */
    private StreamedContent listImage = null;

    public StreamedContent getListImage() {
        if (listImage == null) {
            try {
                listImage = new DefaultStreamedContent(new FileInputStream("D:/a.jpg"), "image/png"); // load a dummy image
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return listImage;
    }

    public void handleFileUpload(FileUploadEvent event) {
        final UploadedFile uploadedFile = event.getFile();

        listImage = new DefaultStreamedContent(new ByteArrayInputStream(uploadedFile.getContents()), "image/png");
    }
   

    
}
