/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Dao.DaoTMatricula;
import Pojo.Matricula;
import Pojo.Notas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author server
 */
@Named(value = "mbVNotas")
@ViewScoped
public class MbVNotas implements Serializable{

    private Notas tNotas;
    private ClsNotas clsNotas;
    private List<ClsNotas> lstTblNotas;
    private String cedula="";
    private String estudiante="";
    private int idProm = 0;
    private boolean msg;
    
    public MbVNotas() {
        tNotas = new Notas();
    }

    public Notas gettNotas() {
        return tNotas;
    }

    public void settNotas(Notas tNotas) {
        this.tNotas = tNotas;
    }

    public ClsNotas getClsNotas() {
        return clsNotas;
    }

    public void setClsNotas(ClsNotas clsNotas) {
        this.clsNotas = clsNotas;
    }

    public List<ClsNotas> getLstTblNotas() {
        return lstTblNotas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public int getIdProm() {
        return idProm;
    }

    public void setIdProm(int idProm) {
        this.idProm = idProm;
    }
    
    
    private void cargarTblMatriculaPromocion(){
        lstTblNotas = new ArrayList<>();
        try {
            lstTblNotas.clear();
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = daoTmatricula.getMatriculaMaestria(this.cedula);
            Calendar calendar = Calendar.getInstance();
            int añoInicio = 0;
            int añoFin = 0;
            
            if(lstMatricula != null){
                if(lstMatricula.size() > 0){
                    for(Matricula matricula : lstMatricula){
                        calendar.setTime(matricula.getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        
                        calendar.setTime(matricula.getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getEstudiante().getApellidos()+" "+matricula.getEstudiante().getNombres();
                         
                        lstTblNotas.add(new ClsNotas(matricula.getEstudiante().getId(), 
                                matricula.getEstudiante().getApellidos()+" "+matricula.getEstudiante().getNombres(), 
                                matricula.getId(), 
                                matricula.getNMatricula(), 
                                matricula.getFechaMatricula(), 
                                matricula.getPromocion().getId(), 
                                añoInicio+"-"+añoFin, 
                                matricula.getPromocion().getFechaResolucion(), 
                                matricula.getPromocion().getMaestria().getId(), 
                                matricula.getPromocion().getMaestria().getDescripcion()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarMaestrias(){
        cargarTblMatriculaPromocion();
    }
    
    public void consultarNotas(ClsNotas clsNotas){
        idProm = clsNotas.getIdPromocion();
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
