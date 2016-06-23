/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsArchivos;
import Dao.InscripcionDao;
import Dao.MatriculaDao;
import Pojo.Archivos;
import Pojo.Matricula;
import Pojo.SolicitudInscripcion;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Matricula> lstMatriculas;
    private List<Archivos> lstArchivos;
    private Matricula SelectedMatricula;
    private String observacion;

    private StreamedContent file;
    private List<ClsArchivos> archivos = null;
    private List<SolicitudInscripcion> lstSInscripcionFiltrada;
    private InscripcionDao d;

    public List<Matricula> getLstMatriculas() {
        return lstMatriculas;
    }

    public void setLstMatriculas(List<Matricula> lstMatriculas) {
        this.lstMatriculas = lstMatriculas;
    }

    public List<Archivos> getLstArchivos() {
        return lstArchivos;
    }

    public void setLstArchivos(List<Archivos> lstArchivos) {
        this.lstArchivos = lstArchivos;
    }

    public Matricula getSelectedMatricula() {
        return SelectedMatricula;
    }

    public void setSelectedMatricula(Matricula SelectedMatricula) {
        this.SelectedMatricula = SelectedMatricula;
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

    @PostConstruct
    public void init() {
        MatriculaDao mD = new MatriculaDao();
        lstMatriculas = mD.obtenerTodasMatriculas();
    }

    public void aprobarMatricula() {
        if (SelectedMatricula.getEstado() == '0') {
            try {
                MatriculaDao mD = new MatriculaDao();
                SelectedMatricula.setEstado('1');
                SelectedMatricula.getSolicitudInscripcion().setEstado('A');
                
                if (mD.insertar(SelectedMatricula)) {
                    FacesMessage message = new FacesMessage("Succesful", "Datos actualizados");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    lstMatriculas.clear();
                    lstMatriculas = mD.obtenerTodasMatriculas();
                } else {
                    FacesMessage message = new FacesMessage("Error", "No se pudo actualizar los datos");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } catch (Exception ex) {
                Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
                FacesMessage message = new FacesMessage("Error", "No se pudo actualizar los datos");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage("Error", "Ya está aprobada esta maestría");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void rechazarMatricula() {
        if (SelectedMatricula.getEstado() == '1') {
            try {
                MatriculaDao mD = new MatriculaDao();
                SelectedMatricula.setEstado('0');
                SelectedMatricula.getSolicitudInscripcion().setEstado('R');
                
                if (mD.insertar(SelectedMatricula)) {
                    FacesMessage message = new FacesMessage("Succesful", "Datos actualizados");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    lstMatriculas.clear();
                    lstMatriculas = mD.obtenerTodasMatriculas();
                } else {
                    FacesMessage message = new FacesMessage("Error", "No se pudo actualizar los datos");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } catch (Exception ex) {
                Logger.getLogger(MatriculaBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
                FacesMessage message = new FacesMessage("Error", "No se pudo actualizar los datos");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage("Error", "Ya está rechazada esta maestría");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void obtenerRequisitos() {

        try {
            archivos = new ArrayList<>();
            lstArchivos = new ArrayList<>();
            if (SelectedMatricula != null) {
                d = new InscripcionDao();
                lstArchivos = d.getArchivosInscripciones(String.valueOf(SelectedMatricula.getSolicitudInscripcion().getId()));

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
            FacesMessage message = new FacesMessage("Error", "No se ha podido cargar los requisitos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
