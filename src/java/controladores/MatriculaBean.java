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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "matriculaBean")
@ViewScoped
public class MatriculaBean implements Serializable {

    private List<SolicitudInscripcion> lstSInscripcion;
    private List<Archivos> lstArchivos;
    private SolicitudInscripcion SelectedInscripcion;
    private String observacion;
    private Object[] a = null;
    private Date fechaActual = new Date();
    private StreamedContent file;
    private List<ClsArchivos> archivos = null;
    private List<SolicitudInscripcion> lstSInscripcionFiltrada;
    private InscripcionDao d;
    private boolean desc;
    private int numM;

    public int getNumM() {
        return numM;
    }

    public void setNumM(int numM) {
        this.numM = numM;
    }    

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

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
//        if(this.SelectedInscripcion!=null){
//        MatriculaDao maDao = new MatriculaDao();
//        numM = maDao.obtenerNumeroMatricula(SelectedInscripcion.getEstudiante().getCedPasaporte(), String.valueOf(SelectedInscripcion.getPromocion().getId()));
//        }
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Object[] getA() {
        return a;
    }

    public void setA(Object[] a) {
        this.a = a;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
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

    @PostConstruct
    public void init() {
        MatriculaDao mD = new MatriculaDao();
        lstSInscripcion = mD.obtenerTodasSolicitudes();
    }

    public void guardarMatricula() {
        try {
            MatriculaDao mDao = new MatriculaDao();
            if (SelectedInscripcion != null) {
                if (!mDao.existeMatricula(SelectedInscripcion.getEstudiante().getCedPasaporte(), String.valueOf(SelectedInscripcion.getPromocion().getId()))) {
                    
                    Matricula m = new Matricula();
//                    m.setNMatricula(String.valueOf(numM));
                    m.setEstado('1');
                    Date fechaM = new Date();
                    m.setFechaMatricula(fechaM);
                    m.setSolicitudInscripcion(SelectedInscripcion);
                    SelectedInscripcion.setEstado('A');
                    SelectedInscripcion.setFechaRevision(fechaM);
                    SelectedInscripcion.setObservacion(observacion);
                    mDao.insertar(m, SelectedInscripcion);

                } else if (SelectedInscripcion.getEstado() == 'R') {
                    
                    Matricula m = new Matricula();
//                    m.setNMatricula(String.valueOf(numM));
                    m.setEstado('1');
                    Date fechaM = new Date();
                    m.setFechaMatricula(fechaM);
                    m.setSolicitudInscripcion(SelectedInscripcion);
                    SelectedInscripcion.setEstado('A');
                    SelectedInscripcion.setFechaRevision(fechaM);
                    SelectedInscripcion.setObservacion(observacion);
                    mDao.insertar(m, SelectedInscripcion);    
                } else if (SelectedInscripcion.getEstado() == 'E') {
                    rechazarMatricula();
                }
                lstSInscripcion.clear();
                lstSInscripcion = mDao.obtenerTodasSolicitudes();
                FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage("Error", "Selecciona una solicitud");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Error", "Error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        SelectedInscripcion = null;
        observacion = "";
        archivos = null;
    }

    public void rechazarMatricula() {
        try {
            if (SelectedInscripcion != null) {
                SelectedInscripcion.setEstado('R');
                SelectedInscripcion.setObservacion(observacion);
                Date fechaM = new Date();
                SelectedInscripcion.setFechaRevision(fechaM);
                MatriculaDao mDao = new MatriculaDao();

                if (mDao.existeMatricula(SelectedInscripcion.getEstudiante().getCedPasaporte(), String.valueOf(SelectedInscripcion.getPromocion().getId()))) {
                    Matricula m = mDao.obtenerMatricula(SelectedInscripcion.getEstudiante().getCedPasaporte(), String.valueOf(SelectedInscripcion.getPromocion().getId()));
                    m.setEstado('0');
                    mDao.insertar(m, SelectedInscripcion);
                } else {
                    mDao.rechazar(SelectedInscripcion);
                }
                lstSInscripcion.clear();
                lstSInscripcion = mDao.obtenerTodasSolicitudes();
                FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } else {
                FacesMessage message = new FacesMessage("Error", "Selecciona una solicitud");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        SelectedInscripcion = null;
        observacion = "";
        archivos = null;
    }

    public void obtenerRequisitos() {

        try {
            archivos = new ArrayList<>();
            lstArchivos = new ArrayList<>();
            if (SelectedInscripcion != null) {
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

        }

    }

}
