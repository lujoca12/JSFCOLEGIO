/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsEstudiante;
import Dao.DaoRepActaEmision;
import Dao.DaoTEstudiante;
import Pojo.Estudiante;
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

/**
 *
 * @author server
 */
@Named(value = "mbVEstudiante")
@SessionScoped
public class MbVEstudiante implements Serializable {

    private StreamedContent media;
    private ClsEstudiante themeMaestria; 
    private List<ClsEstudiante> lstThemeMaestria;
    private boolean msg = false;
    
     @PostConstruct
    public void load() {
        media = null;
        llenarCboEstudiante();
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public ClsEstudiante getThemeMaestria() {
        return themeMaestria;
    }

    public void setThemeMaestria(ClsEstudiante themeMaestria) {
        this.themeMaestria = themeMaestria;
    }

    public List<ClsEstudiante> getLstThemeMaestria() {
        return lstThemeMaestria;
    }

    public void llenarCboEstudiante(){
        this.lstThemeMaestria = new ArrayList<ClsEstudiante>();
         try {
            DaoTEstudiante daoTEstudiante = new DaoTEstudiante();
            
            //Calendar calendar = Calendar.getInstance();
        

            List<Estudiante> lstEstudiante = daoTEstudiante.getEstudiantes();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsEstudiante(-1, -1, "Ninguno","Ninguno","Ninguno",null,null,null,null,true,true));

            for (Estudiante estudiante : lstEstudiante) {
                
                this.lstThemeMaestria.add(new ClsEstudiante(estudiante.getId(),
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
    
    public void cargarReporte(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeMaestria != null)
           media = daoReport.reporteMaestriaProceso(this.themeMaestria.getCedula());//Reporte Maestria en proceso por estudiante
    }
    //Reporte maestria culminada por estudiante
    public void cargarReporteMaestria(){
        media = null;
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeMaestria != null)
           media = daoReport.reporteMaestriaEstudiante(this.themeMaestria.getCedula());
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
