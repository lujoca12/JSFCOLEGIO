/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Clases.ClsTblNotas;
import Dao.DaoTEstudiante;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Dao.DaoTNotas;
import Pojo.Estudiante;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Notas;
import Pojo.SolicitudInscripcion;
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
    
    private ClsTblNotas clsTblNotas;
    private List<ClsTblNotas> lstTblNotasReg;
    
    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;
    
    private String cedula="";
    private String estudiante="";
    private int idProm = 0;
    private int idModulo=0;
    private boolean msg;
    
    public MbVNotas() {
        tNotas = new Notas();
        cargarCboModulos();
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

    public ClsTblNotas getClsTblNotas() {
        return clsTblNotas;
    }

    public void setClsTblNotas(ClsTblNotas clsTblNotas) {
        this.clsTblNotas = clsTblNotas;
    }

    public List<ClsTblNotas> getLstTblNotasReg() {
        return lstTblNotasReg;
    }

    public ClsTablaModulosRegistrados getClsTblModulosReg() {
        return clsTblModulosReg;
    }

    public void setClsTblModulosReg(ClsTablaModulosRegistrados clsTblModulosReg) {
        this.clsTblModulosReg = clsTblModulosReg;
    }

    public List<ClsTablaModulosRegistrados> getLstCboModulos() {
        return lstCboModulos;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
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
            
            
            
                if(lstMatricula.size() > 0){
                    for(Matricula matricula : lstMatricula){
                        calendar.setTime(matricula.getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        
                        calendar.setTime(matricula.getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres();
                        
                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(), 
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres(), 
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
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarTablaNotas(int idMatricula, int idPromocion){
        lstTblNotasReg = new ArrayList<>();
        try {
            lstTblNotasReg.clear();
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = daoTnotas.getTodasNotas(idMatricula);
            
            //Validacion para saber si hay notas registradas
                if(lstNotas.size() > 0){
                    for(Notas nota : lstNotas){
                         
                        lstTblNotasReg.add(new ClsTblNotas(nota.getModulo().getId(), 
                                nota.getModulo().getDescripcion(), 
                                nota.getModulo().getCreditos(), 
                                nota.getId(), 
                                nota.getNota().doubleValue(), 
                                nota.getObservacion(), 
                                nota.getNotaTexto()));
                    }
                }else{
                    //Validacion para mostrar las materias asignadas
                    DaoTModulo daoTmodulo = new DaoTModulo();
                    List<Modulo> lstModulos = daoTmodulo.getTblModulosNotas(idPromocion);
                    
                    if(lstModulos.size() > 0){
                    for(Modulo modulos : lstModulos){
                         
                        lstTblNotasReg.add(new ClsTblNotas(modulos.getId(), 
                                modulos.getDescripcion(), 
                                modulos.getCreditos(), 
                                0, 
                                0.0, 
                                "", 
                                ""));
                        }
                    }
                    
                }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarCboModulos(){
        lstCboModulos = new ArrayList<>();
        try {
            lstCboModulos.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas();
            
            if(lstModulo != null){
                if(lstModulo.size() > 0){
                    for(Modulo modulo : lstModulo){
                        lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(), 
                                modulo.getPromocion().getMaestria().getDescripcion()+" (Dir.(a)"+modulo.getPromocion().getUsuarios()+")", 
                                modulo.getPromocion().getId(), 
                                modulo.getDescripcion()+" ("+modulo.getPromocion().getMaestria().getDescripcion()+")", 
                                modulo.getUsuario().getId(), 
                                modulo.getUsuario().getApellidos()+" "+modulo.getUsuario().getNombres(), 
                                modulo.getCreditos(), 
                                modulo.getId()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarTablaRegNotas(){
        lstTblNotas = new ArrayList<>();
        try {
            lstTblNotas.clear();
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = daoTmatricula.getMatriculaRegNotas(this.idModulo);
            Calendar calendar = Calendar.getInstance();
            int añoInicio = 0;
            int añoFin = 0;
            
                if(lstMatricula.size() > 0){
                    for(Matricula matricula : lstMatricula){
                        calendar.setTime(matricula.getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        
                        calendar.setTime(matricula.getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres();
                        
                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(), 
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos()+" "+matricula.getSolicitudInscripcion().getEstudiante().getNombres(), 
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
            
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarMaestrias(){
        cargarTblMatriculaPromocion();
    }
    
    public void consultarNotas(ClsNotas clsNotas){
        idProm = clsNotas.getIdPromocion();
        cargarTablaNotas(clsNotas.getIdMatricula(),clsNotas.getIdPromocion());
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
