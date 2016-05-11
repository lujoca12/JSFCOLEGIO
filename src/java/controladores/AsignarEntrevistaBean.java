/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Pojo.Estudiante;
import Pojo.SolicitudInscripcion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import Dao.InscripcionDao;
import Dao.MatriculaDao;
import Pojo.Archivos;
import Pojo.Matricula;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author chiti
 */
@Named(value = "asignarEntrevistaBean")
@ViewScoped
public class AsignarEntrevistaBean implements Serializable {

    /**
     * Creates a new instance of AsignarEntrevistaBean
     */
    private List<Estudiante> estudiantes;
    private List<SolicitudInscripcion> lstSInscripcion;
    private List<Archivos> lstArchivos;
    private SolicitudInscripcion SelectedInscripcion;
    private String observacion;

    public List<Archivos> getLstArchivos() {
        return lstArchivos;
    }

    public void setLstArchivos(List<Archivos> lstArchivos) {
        this.lstArchivos = lstArchivos;
    }
    
    

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public SolicitudInscripcion getSelectedInscripcion() {
        return SelectedInscripcion;
    }

    public void setSelectedInscripcion(SolicitudInscripcion SelectedInscripcion) {
        this.SelectedInscripcion = SelectedInscripcion;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<SolicitudInscripcion> getLstSInscripcion() {
        return lstSInscripcion;
    }

    public void setLstSInscripcion(List<SolicitudInscripcion> lstSInscripcion) {
        this.lstSInscripcion = lstSInscripcion;
    }

    public AsignarEntrevistaBean() {

    }

    @PostConstruct
    public void init() {
        try {
            InscripcionDao d = new InscripcionDao();
            lstSInscripcion = d.getInscripcionesEstudiantes();
        } catch (Exception ex) {
        }
    }

    public void guardarMatricula() {
        try {
            Matricula m = new Matricula();
            m.setEstado('1');
            Date fecha = new Date();
            m.setFechaMatricula(fecha);
            m.setSolicitudInscripcion(SelectedInscripcion);
            SelectedInscripcion.setEstado('A');
            SelectedInscripcion.setFechaRevision(fecha);
            SelectedInscripcion.setObservacion(observacion);
            MatriculaDao mDao = new MatriculaDao();
            mDao.insertar(m, SelectedInscripcion);
            lstSInscripcion.remove(SelectedInscripcion);
            FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardarlos datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void rechazarMatricula() {
        try {

            SelectedInscripcion.setEstado('R');
            Date fecha = new Date();
            SelectedInscripcion.setFechaRevision(fecha);
            MatriculaDao mDao = new MatriculaDao();
            mDao.rechazar(SelectedInscripcion);
            lstSInscripcion.remove(SelectedInscripcion);
        } catch (Exception ex) {

        }
    }

}
