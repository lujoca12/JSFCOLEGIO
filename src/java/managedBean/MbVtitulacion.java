/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsTipoTitulacion;
import Clases.ClsMaestria;
import Clases.ClsEstudiante;
import Clases.ClsMatricula;
import Dao.DaoTMaestrias;
import Dao.DaoTEstudiante;
import Dao.DaoTMatricula;
import Pojo.Maestria;
import Pojo.Titulacion;
import Dao.DaoTitulacion;
import Pojo.Estudiante;
import Pojo.Matricula;
import Pojo.SolicitudInscripcion;
import Pojo.TipoTitulacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVtitulacion")
@ViewScoped
public class MbVtitulacion implements Serializable{
    private boolean estado = false;    
    private String idmaestria;
     private boolean msg = false;
     private ClsTipoTitulacion clstipotitulacion;
     private List<ClsTipoTitulacion> lsttipotitulacion;  
     
     private ClsMatricula clsmatricula;
     private List<ClsMatricula> lstmatricula;   
     private ClsMaestria clsmaestria;
     private List<ClsMaestria> lstmaestria;     
     private ClsEstudiante clsestudiante;
     private List<ClsEstudiante> lstestudiante;
     private Date date;
     private Date fechainicio;
     private Date fechafin;
     
     private Titulacion ttitulacion;
     private TipoTitulacion ttipotitulacion;
     private Matricula tmatricula;
     private Estudiante testudiante;
    /**
     * Creates a new instance of MbVtitulacion
     */
    public MbVtitulacion() {
        ttitulacion = new Titulacion();
        tmatricula = new Matricula();
        ttipotitulacion = new TipoTitulacion();
        ttitulacion.setFechaInicio(date);
        llenarCboTipoTitulacion();
        llenarCboMaestria();
    }
    
    private void vaciarCajas(){
         ttitulacion = new Titulacion();
        tmatricula = new Matricula();
        ttipotitulacion = new TipoTitulacion();
    }
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Titulacion getTtitulacion() {
        return ttitulacion;
    }
    public void setTtitulacion(Titulacion ttitulacion) {
        this.ttitulacion = ttitulacion;
    } 
    public ClsEstudiante getClsestudiante() {
        return clsestudiante;
    }
    public void setClsestudiante(ClsEstudiante clsestudiante) {
        this.clsestudiante = clsestudiante;
    }
    public List<ClsEstudiante> getLstestudiante() {
        return lstestudiante;
    }
    public ClsMaestria getClsmaestria() {
        return clsmaestria;
    }
    public void setClsmaestria(ClsMaestria clsmaestria) {
        this.clsmaestria = clsmaestria;
    }
    public List<ClsMaestria> getLstmaestria() {
        return lstmaestria;
    }        
    public ClsTipoTitulacion getClstipotitulacion() {
        return clstipotitulacion;
    }
    public void setClstipotitulacion(ClsTipoTitulacion clstipotitulacion) {
        this.clstipotitulacion = clstipotitulacion;
    }
    public List<ClsTipoTitulacion> getLsttipotitulacion() {
        return lsttipotitulacion;
    }
    public void setLsttipotitulacion(List<ClsTipoTitulacion> lsttipotitulacion) {
        this.lsttipotitulacion = lsttipotitulacion;
    }
    
    
    public void llenarCboMaestria(){
        this.lstmaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
        List<Maestria> lst = daoTmaestria.getMaestrias();
            this.lstmaestria.clear();
            this.lstmaestria.add(new ClsMaestria(-1, "ninguno", "ninguno", -1, -1, -1));
            for(Maestria tt: lst){
                this.lstmaestria.add(new ClsMaestria(
                        tt.getId(),
                        tt.getDescripcion(), 
                        tt.getDescripcion(), 
                        tt.getId(), 
                        0, 
                        0));
                }
            String nreak;
            nreak="";
        } catch (Exception ex) {
            
        }
    }
    
    public void onEstudianteChange() throws Exception{
        if(clsestudiante != null){estado=true;}
        else{estado=false;}
    }
    
    public void onMaestriaChange() throws Exception{
        try{
        lstestudiante = new ArrayList<>();
        lstestudiante.clear();
        this.lstestudiante.add(new ClsEstudiante(-1, "(Escoja un Estudiante)","(Escoja un Estudiante)"));
        DaoTEstudiante daoestudiante = new DaoTEstudiante();
        List<Estudiante> lstestud= null;
        
        if(clsmaestria != null && !clsmaestria.equals(""))
            lstestud = daoestudiante.getEstudiantesMaestria(clsmaestria.getId());
        else{
            lstestudiante.clear();
            estado=false;
        }
        
        if(lstestud != null){
            if(lstestud.size()>0){
                for(Estudiante es : lstestud){
                    lstestudiante.add(new ClsEstudiante(
                            es.getId(),
                            es.getNombres(), 
                            es.getNombres()+" "+es.getApellidos()
                    ));
                }                
            }
        }
        }catch (Exception e){}
        
    }
    
     public void llenarCboTipoTitulacion(){
        this.lsttipotitulacion = new ArrayList<ClsTipoTitulacion>();
         try {
            DaoTitulacion daoTusuario = new DaoTitulacion();
            
            List<TipoTitulacion> lsttipo = daoTusuario.getTodastipoTitulacion();
            this.lsttipotitulacion.clear();
            this.lsttipotitulacion.add(new ClsTipoTitulacion(-1,"Ninguno","Ninguno"));
            for(TipoTitulacion tt: lsttipo){
                this.lsttipotitulacion.add(new ClsTipoTitulacion(tt.getId(),
                        tt.getDescripcion(), tt.getDescripcion()));
            }
        } catch (Exception ex) {
            
        }
    }    
     
     public void llenarCboEstudiante(){
          this.lstestudiante = new ArrayList<ClsEstudiante>();
         try {
            DaoTEstudiante daoTusuario = new DaoTEstudiante();
            
            List<Estudiante> lsttipo = daoTusuario.getEstudiantes();
            this.lstestudiante.clear();
            this.lstestudiante.add(new ClsEstudiante(-1,"Ninguno","Ninguno"));
            for(Estudiante tt: lsttipo){
                this.lstestudiante.add(new ClsEstudiante(tt.getId(),
                        tt.getApellidos()+""+tt.getNombres(), tt.getNombres()));
            }
        } catch (Exception ex) {
            
        }
     }

     public void ObteneridMatriculaEstudiante(){
         try{
             DaoTMatricula daomatricula = new DaoTMatricula();
         
            this.lstmatricula = new ArrayList<ClsMatricula>();
            int cedul = this.clsestudiante.getId();
            String cedula = Integer.toString(this.clsestudiante.getId());
            List<Matricula> matr = daomatricula.getMatriculaEstudiante(cedul);
            this.lstmatricula.clear();
            for(Matricula ma : matr){
            //lstmatricula.add(new ClsMatricula(cedul, fechainicio, 0, idmaestria, fechainicio)
            lstmatricula.add(new ClsMatricula(
                    ma.getId(),              
                    ma.getFechaMatricula(),  
                    ma.getEstado(),  
                    ma.getNMatricula(),
                    ma.getFechaGraduacion()));
            }
            boolean a =false;
         }catch(Exception e){
             
         }
     }
     
     public void registrarTitulacion(){
         DaoTitulacion daotitulacion = new DaoTitulacion();
         DaoTMatricula daomatricula = new DaoTMatricula();
         boolean band = false;
         ObteneridMatriculaEstudiante();

        //Variable para saber si esta registrada
        boolean repetida = false;
            ttipotitulacion.setId(this.clstipotitulacion.getId());
            ttitulacion.setTipoTitulacion(ttipotitulacion);
            ttitulacion.setFechaFin(ttitulacion.getFechaFin());
            ttitulacion.setFechaInicio(ttitulacion.getFechaInicio());
            tmatricula.setId(this.clsmatricula.getId());
            //ttitulacion.setMatricula(tmatricula);
        try{
            List<Titulacion> lstT=(List<Titulacion>) daomatricula.getTitulacionxMatricula(ttitulacion.getMatricula().getId());
            if(lstT.size() > 0){
                repetida = true;
            }
            else{
                //Si la maestria no existe se la registra
                msg =  daotitulacion.registrarTitulacion(ttitulacion);
            }
        }
        catch (Exception e){
            vaciarCajas();
        }
        if(repetida){
            mensajesError("Registro repetido");            
        }else{
            vaciarCajas();            
            if(msg)
                mensajesOk("Datos procesados bien");            
            else
                mensajesError("error al intentar procesar");
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