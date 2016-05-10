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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author server
 */
@Named(value = "mbVActaEmision")
@ViewScoped
public class MbVActaEmision implements Serializable {

    private ClsMaestria themeMaestria; 
    private List<ClsMaestria> lstThemeMaestria;
    
    private boolean msg = false;
    
    private StreamedContent streamedContent = null;
    
    
    
    public MbVActaEmision() {
        llenarCboMaestria();
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

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTPromocion daoTpromocion = new DaoTPromocion();
            
            Calendar calendar = Calendar.getInstance();
        

            List<Promocion> lstPromocion = daoTpromocion.getPromocionesMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1, "Ninguno", "Ninguno", 0, 0, 0));

            for (Promocion promocion : lstPromocion) {
                calendar.setTime(promocion.getFechaInicio());
                int anioInicio = calendar.get(Calendar.YEAR);

                calendar.setTime(promocion.getFechaFin());
                int anioFin = calendar.get(Calendar.YEAR);
                
                this.lstThemeMaestria.add(new ClsMaestria(promocion.getMaestria().getId(),
                        promocion.getMaestria().getDescripcion() + " (" + anioInicio +"-"+ anioFin + ")",
                        promocion.getMaestria().getDescripcion(), 
                        promocion.getMaestria().getId(),
                        anioInicio, 
                        anioFin));
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarReporte(){
        DaoRepActaEmision daoReport = new DaoRepActaEmision();
        if(themeMaestria != null){
           this.streamedContent = daoReport.reporte(this.themeMaestria.getId());

            if (msg) {
                mensajesOk("Reporte cargado correctamente");
            } else {
                mensajesError("error al cargar Reporte");
            }
        }
            
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
