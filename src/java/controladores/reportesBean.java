/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.reportesDao;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "reportesBean")
@SessionScoped
public class reportesBean implements Serializable {
    private StreamedContent media;
    private int mes;
    private int anio;

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }        

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }
    
    
    @PostConstruct
    public void load() {
        media = null;
        
    }
    public void cargarReporte(){
        media = null;
        reportesDao daoReport = new reportesDao();
        
           media = daoReport.reporteIngresoMensuales(mes,anio);
    }
}
