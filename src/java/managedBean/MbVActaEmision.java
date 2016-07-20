/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsMaestria;
import Dao.DaoRepActaEmision;
import Dao.DaoTMaestrias;
import Dao.DaoTPromocion;
import Pojo.Maestria;
import Pojo.Promocion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author server
 */
@Named(value = "mbVActaEmision")
@SessionScoped
public class MbVActaEmision implements Serializable {

    private ClsMaestria themeMaestria; 
    private List<ClsMaestria> lstThemeMaestria;
    
    private boolean msg = false;
    private int estado = 0;
    
    private StreamedContent media;
    
    private ClsMaestria themePromociones;
    private List<ClsMaestria> lstThemePromociones;
    
    
    @PostConstruct
    public void load() {
        media = null;
        llenarCboMaestria();
        this.lstThemePromociones = new ArrayList<ClsMaestria>();
        this.lstThemePromociones.add(new ClsMaestria(-1, "Seleccione..", "Seleccione..", 0, 0, 0, null, null));
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

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public StreamedContent getMedia() {
//        DaoRepActaEmision daoReport = new DaoRepActaEmision();
//        
//           media = daoReport.reporte(1);
        
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
        
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestriasD("",false);
            
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Seleccione....","Seleccione....",0,0,0,null, null));
//            
//            if(lstThemePromociones == null){
//                
//            }
//            
//            this.lstThemePromociones = new ArrayList<ClsMaestria>();
//             
//             this.lstThemePromociones.add(new ClsMaestria(-1, "Seleccione..", "Seleccione..", 0, 0, 0, null, null));
            
            for(Maestria maestria: lstMaestria){
                this.lstThemeMaestria.add(new ClsMaestria(maestria.getId(),
                        maestria.getDescripcion(),
                        maestria.getDescripcion(),
                        maestria.getId(),
                        0,
                        0, 
                        null,
                        null));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void onMaestriaChange() throws Exception {
            
            this.lstThemePromociones = new ArrayList<ClsMaestria>();
        try {
            
            DaoTPromocion daoTpromocion = new DaoTPromocion();

            List<Promocion> lstPromocion = daoTpromocion.getPromocionesMaestrias(themeMaestria.getId());
            
            Calendar anioInicioC = Calendar.getInstance();
            Calendar anioFinC = Calendar.getInstance();
            int anioInicio = 0;
            int anioFin = 0;
            
            this.lstThemePromociones.add(new ClsMaestria(-1,"Seleccione..","Seleccione..",0,0,0,null, null));
            for (Promocion promocion : lstPromocion) {
                
                anioInicioC.setTime(promocion.getFechaInicio());
                anioInicio = anioInicioC.get(Calendar.YEAR);

                anioFinC.setTime(promocion.getFechaFin());
                anioFin = anioFinC.get(Calendar.YEAR);
                
                this.lstThemePromociones.add(new ClsMaestria(promocion.getId(),
                        "Promoción "+promocion.getDescripcion() + " año (" + String.valueOf(anioInicio) +" - "+ String.valueOf(anioFin)+")" ,
                        promocion.getUsuario(), 
                        promocion.getId(),
                        0, 0,
                        promocion.getFechaInicio(),
                        promocion.getFechaFin()));
            }
        } catch (Exception ex) {

        }

    }
    
    public void cargarReporte(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themePromociones != null && themeMaestria != null)
           media = daoReport.reporte(this.themePromociones.getId(), themeMaestria.getId());
    }
    public void cargarReporteNominaGraduados(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeMaestria != null)
           media = daoReport.reporteNominaGraduados(this.themeMaestria.getId());
    }
    public void cargarReporteRecordAcademico(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeMaestria != null)
           media = daoReport.reporteNominaGraduados(this.themeMaestria.getId());
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
