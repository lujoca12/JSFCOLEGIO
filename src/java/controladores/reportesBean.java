/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.reportesDao;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
    private int month;
    private int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        
           media = daoReport.reporteIngresoMensuales(month,year);
    }
    public void cargarReporteAnual(){
        media = null;
        reportesDao daoReport = new reportesDao();
        
           media = daoReport.reporteIngresoAnuales(year);
    }
}
