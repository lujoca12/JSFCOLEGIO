/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsArchivos;
import Dao.InscripcionDao;
import Pojo.Archivos;
import Pojo.SolicitudInscripcion;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Seeting
 */
@Named(value = "matriculaBean")
@ViewScoped
public class MatriculaBean implements Serializable {

    /**
     * Creates a new instance of MatriculaBean
     */
    
    private List<SolicitudInscripcion> lstSInscripcion;
    private List<Archivos> lstArchivos;
    private SolicitudInscripcion SelectedInscripcion;
    private String observacion;

    private StreamedContent file;
    private List<ClsArchivos> archivos = null;
    private List<SolicitudInscripcion> lstSInscripcionFiltrada;
    private InscripcionDao d;

    public List<SolicitudInscripcion> getLstSInscripcion() {
        return lstSInscripcion;
    }

    public void setLstSInscripcion(List<SolicitudInscripcion> lstSInscripcion) {
        this.lstSInscripcion = lstSInscripcion;
    }

    public List<Archivos> getLstArchivos() {
        return lstArchivos;
    }

    public void setLstArchivos(List<Archivos> lstArchivos) {
        this.lstArchivos = lstArchivos;
    }

    public SolicitudInscripcion getSelectedInscripcion() {
        return SelectedInscripcion;
    }

    public void setSelectedInscripcion(SolicitudInscripcion SelectedInscripcion) {
        this.SelectedInscripcion = SelectedInscripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public List<ClsArchivos> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ClsArchivos> archivos) {
        this.archivos = archivos;
    }

    public List<SolicitudInscripcion> getLstSInscripcionFiltrada() {
        return lstSInscripcionFiltrada;
    }

    public void setLstSInscripcionFiltrada(List<SolicitudInscripcion> lstSInscripcionFiltrada) {
        this.lstSInscripcionFiltrada = lstSInscripcionFiltrada;
    }

    public InscripcionDao getD() {
        return d;
    }

    public void setD(InscripcionDao d) {
        this.d = d;
    }
    
    
    public MatriculaBean() {
    }
    
    public void aprobarMatricula(){
        
    }
    public void obtenerRequisitos() {

        try {
            archivos = new ArrayList<>();
            lstArchivos = new ArrayList<>();
            if (SelectedInscripcion != null) {
                d = new InscripcionDao();
                lstArchivos = d.getArchivosInscripciones(String.valueOf(SelectedInscripcion.getId()));

                for (Archivos a : lstArchivos) {
                    InputStream input = new FileInputStream(a.getRuta());
                    String extension = a.getRuta().substring(a.getRuta().lastIndexOf('.'));
                    file = new DefaultStreamedContent(input, a.getRequisitosPromo().getRequisitos().getTipoArchivo(), a.getRequisitosPromo().getRequisitos().getFormato() + extension);
                    archivos.add(new ClsArchivos(a.getRequisitosPromo().getRequisitos().getDescripcion(), file));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarEntrevistaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }

    }
}
