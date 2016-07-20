/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsEstudiante;
import Clases.ClsMaestria;
import Dao.DaoRepActaEmision;
import Dao.DaoTEstudiante;
import Dao.DaoTMaestrias;
import Dao.DaoTPromocion;
import Pojo.Estudiante;
import Pojo.Maestria;
import Pojo.Promocion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.StreamedContent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author server
 */
@Named(value = "mbVEstudiante")
@SessionScoped
public class MbVEstudiante implements Serializable {

    private StreamedContent media;
    private ClsEstudiante themeEstudiante; 
    private List<ClsEstudiante> lstThemeEstudiante;
    
    private int idMaestria;  
    private List<SelectItem> maestria;
    
    private int idPromocion;  
    private List<SelectItem> promocion;
    
    private ClsMaestria themeMaestria;
    private List<ClsMaestria> lstThemeMaestria;
    
    private ClsMaestria themePromociones;
    private List<ClsMaestria> lstThemePromociones;
    private boolean msg = false;
    
     @PostConstruct
    public void load() {
        media = null;
        this.maestria = new ArrayList<SelectItem>();
        this.promocion = new ArrayList<SelectItem>();
        llenarCboEstudiante();
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public ClsEstudiante getThemeEstudiante() {
        return themeEstudiante;
    }

    public void setThemeEstudiante(ClsEstudiante themeEstudiante) {
        this.themeEstudiante = themeEstudiante;
    }

    public List<ClsEstudiante> getLstThemeEstudiante() {
        return lstThemeEstudiante;
    }

    public ClsMaestria getThemeMaestria() {
        return themeMaestria;
    }

    public void setThemeMaestria(ClsMaestria themeMaestria) {
        this.themeMaestria = themeMaestria;
    }

    public List<ClsMaestria> getLstThemeMaestria() {
        return lstThemeMaestria;
    }

    public ClsMaestria getThemePromociones() {
        return themePromociones;
    }

    public void setThemePromociones(ClsMaestria themePromociones) {
        this.themePromociones = themePromociones;
    }

    public List<ClsMaestria> getLstThemePromociones() {
        return lstThemePromociones;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public List<SelectItem> getMaestria() {
        return maestria;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public List<SelectItem> getPromocion() {
        return promocion;
    }

    public void llenarCboEstudiante(){
        this.lstThemeEstudiante = new ArrayList<ClsEstudiante>();
         try {
            DaoTEstudiante daoTEstudiante = new DaoTEstudiante();
            
            //Calendar calendar = Calendar.getInstance();
        

            List<Estudiante> lstEstudiante = daoTEstudiante.getEstudiantes();
            this.lstThemeEstudiante.add(new ClsEstudiante(-1, -1, "Ninguno","Ninguno","Ninguno",null,null,null,null,true,true));
            
            for (Estudiante estudiante : lstEstudiante) {
                
                this.lstThemeEstudiante.add(new ClsEstudiante(estudiante.getId(),
                        0,
                        estudiante.getCedPasaporte() == null ? null:estudiante.getCedPasaporte(),
                        "Ingeniero(a) "+estudiante.getApellidos()+" "+estudiante.getNombres() + " (" +estudiante.getCedPasaporte() + ")",
                        null,
                        null,
                        null,
                        null,
                        null,
                        true,
                        true));
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onEstudianteChange() {
        
        maestria = new ArrayList<SelectItem>();
        
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            List<Maestria> lstMaestria = null;
            
            this.maestria.clear();
            this.promocion = new ArrayList<SelectItem>();

            if(themeEstudiante != null)
                lstMaestria = daoTmaestria.getMaestriaEstudiante(themeEstudiante.getId());
            else
                lstMaestria = daoTmaestria.getMaestriaEstudiante(0);
            
            
            for(Maestria maestria: lstMaestria){
                this.maestria.add(new SelectItem(maestria.getId(), maestria.getDescripcion()));
            }
            this.maestria.get(0);
        } catch (Exception ex) {
            
        }
    }
    
    public void onMaestriaChange() throws Exception {
            
            this.promocion = new ArrayList<SelectItem>();
        try {
            
            DaoTPromocion daoTpromocion = new DaoTPromocion();
            List<Promocion> lstPromocion = null;
            
            if(idMaestria > 0)
                lstPromocion = daoTpromocion.getPromocionesMaestriasEstudiantes(idMaestria,themeEstudiante.getId());
            else
                lstPromocion = daoTpromocion.getPromocionesMaestrias(0);
            
            
            for (Promocion promocion : lstPromocion) {
                
                this.promocion.add(new SelectItem(promocion.getId(), "Promoción "+promocion.getDescripcion()));
            }
        } catch (Exception ex) {

        }

    }
    
    public void cargarReporte(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeEstudiante != null)
           media = daoReport.reporteMaestriaProceso(this.themeEstudiante.getCedula(), idMaestria, idPromocion);//Reporte Maestria en proceso por estudiante
    }
    //Reporte maestria culminada por estudiante
    public void cargarReporteMaestria(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeEstudiante != null  && idMaestria > 0 && idPromocion > 0)
           media = daoReport.reporteMaestriaEstudiante(this.themeEstudiante.getCedula(), idMaestria, idPromocion);
    }
            
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
}
