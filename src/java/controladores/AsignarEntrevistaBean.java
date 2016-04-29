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
            InscripcionDao d=new InscripcionDao();
            lstSInscripcion=d.getInscripcionesEstudiantes();
        } catch (Exception ex) {
        }
    }

}
