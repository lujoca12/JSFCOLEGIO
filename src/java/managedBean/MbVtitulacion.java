/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsTipoTitulacion;
import Clases.ClsMaestria;
import Clases.ClsTitulacion;
import Clases.ClsCargo;

import Clases.ClsEstudiante;
import Clases.ClsMatricula;
import Dao.DaoTMaestrias;
import Dao.DaoTEstudiante;
import Dao.DaoTMatricula;
import Pojo.Maestria;
import Pojo.Titulacion;
import Dao.DaoTitulacion;
import Dao.DaoTUsuario;
import Dao.DaoTribunal;
import Pojo.Cargo;
import Pojo.Estudiante;
import Pojo.Matricula;
import Pojo.SolicitudInscripcion;
import Pojo.TipoTitulacion;
import Pojo.TipoTribunal;
import Pojo.Persona;
import Pojo.Acta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     
      private ClsCargo clscargo;     
     private List<ClsCargo> lstcargo; 
     
     private ClsTitulacion clstitulacion1;     
     private List<ClsTitulacion> lsttitulacion1; 
     
     private ClsTitulacion clstitulacion;     
     private List<ClsTitulacion> lsttitulacion;  
     
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
     private List<Titulacion> lsttitulacin = new ArrayList<>();
     private TipoTitulacion ttipotitulacion;
     private Matricula tmatricula;
     private Estudiante testudiante;
     private Persona tpersona;
     
    private List<Persona> lstPersona = new ArrayList<>();
    private List<Acta> lstActa = new ArrayList<>();
     private Cargo tcargo;
     private String cargoDescripcion;
    /**
     * Creates a new instance of MbVtitulacion
     */
    public MbVtitulacion() {
        ttitulacion = new Titulacion();
        tmatricula = new Matricula();
        ttipotitulacion = new TipoTitulacion();
        ttitulacion.setFechaInicio(date);
        llenarCboTipoTtribunal();
        llenarCboMaestria();
        CargarTitulacionNO();
        cargarTablaCargos();CargarPersona();
        
    }
    
    private void vaciarCajas(){
         ttitulacion = new Titulacion();
        tmatricula = new Matricula();
        ttipotitulacion = new TipoTitulacion();
    }

    public List<Acta> getLstActa() {
        return lstActa;
    }

    public void setLstActa(List<Acta> lstActa) {
        this.lstActa = lstActa;
    }
    

    public List<Titulacion> getLsttitulacin() {
        return lsttitulacin;
    }

    public void setLsttitulacin(List<Titulacion> lsttitulacin) {
        this.lsttitulacin = lsttitulacin;
    }

    
    public List<Persona> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<Persona> lstPersona) {
        this.lstPersona = lstPersona;
    }

    public Persona getTpersona() {
        return tpersona;
    }

    public void setTpersona(Persona tpersona) {
        this.tpersona = tpersona;
    }

    public String getCargoDescripcion() {
        return cargoDescripcion;
    }

    public void setCargoDescripcion(String cargoDescripcion) {
        this.cargoDescripcion = cargoDescripcion;
    }

    public ClsCargo getClscargo() {
        return clscargo;
    }

    public void setClscargo(ClsCargo clscargo) {
        this.clscargo = clscargo;
    }

    public List<ClsCargo> getLstcargo() {
        return lstcargo;
    }

    public void setLstcargo(List<ClsCargo> lstcargo) {
        this.lstcargo = lstcargo;
    }
    

    public Cargo getTcargo() {
        return tcargo;
    }

    public void setTcargo(Cargo tcargo) {
        this.tcargo = tcargo;
    }
    

    public ClsMatricula getClsmatricula() {
        return clsmatricula;
    }
    public void setClsmatricula(ClsMatricula clsmatricula) {
        this.clsmatricula = clsmatricula;
    }
    public List<ClsMatricula> getLstmatricula() {
        return lstmatricula;
    }
    public void setLstmatricula(List<ClsMatricula> lstmatricula) {
        this.lstmatricula = lstmatricula;
    }
    public ClsTitulacion getClstitulacion() {
        return clstitulacion;
    }
    public void setClstitulacion(ClsTitulacion clstitulacion) {
        this.clstitulacion = clstitulacion;
    }
    public List<ClsTitulacion> getLsttitulacion() {
        return lsttitulacion;
    }
    public void setLsttitulacion(List<ClsTitulacion> lsttitulacion) {
        this.lsttitulacion = lsttitulacion;
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
            
        List<Maestria> lst = daoTmaestria.getMaestriasD("", false);
            this.lstmaestria.clear();
            this.lstmaestria.add(new ClsMaestria(-1, "ninguno", "ninguno", -1, -1, -1, null, null));
            for(Maestria tt: lst){
                this.lstmaestria.add(new ClsMaestria(
                        tt.getId(),
                        tt.getDescripcion(), 
                        tt.getDescripcion(), 
                        tt.getId(), 
                        0, 
                        0,
                        null,
                        null));
                }
            String nreak;
            nreak="";
        } catch (Exception ex) {
            
        }
    }
    
    public void onEstudianteChange() throws Exception{
       try{
        lstestudiante = new ArrayList<>();
        lstestudiante.clear();
        this.lstestudiante.add(new ClsEstudiante(-1, "(Escoja un Estudiante)","(Escoja un Estudiante)",-1));
        DaoTEstudiante daoestudiante = new DaoTEstudiante();
        List<Estudiante> lstestud= null;
        
        if(clsmaestria != null)
            lstestud = daoestudiante.getEstudiantesMaestriaTitulacion(clsmaestria.getId());
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
                            es.getNombres()+" "+es.getApellidos(),
                            -1
                    ));
                }                
            }
        }
       }catch (Exception e){}
        
    }
    
    public void onMaestriaChange() throws Exception{
        try{
        lstestudiante = new ArrayList<>();
        lstestudiante.clear();
        this.lstestudiante.add(new ClsEstudiante(-1, "(Escoja un Estudiante)","(Escoja un Estudiante)",-1));
        DaoTEstudiante daoestudiante = new DaoTEstudiante();
        List<Estudiante> lstestud= null;
        
        if(clsmaestria != null)
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
                            es.getNombres()+" "+es.getApellidos(),
                            -1
                    ));
                }                
            }
         //   ObteneridMatriculaEstudiante();
         //
//         lstmatricula = new ArrayList<>();
//         lstmaestria.clear();
//         DaoTMatricula daomatricula = new DaoTMatricula();
//         
         //
         
        }
        
        }catch (Exception e){}
        
    }
    
     public void llenarCboTipoTtribunal(){
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
            this.lstestudiante.add(new ClsEstudiante(-1,"Ninguno","Ninguno",-1));
            for(Estudiante tt: lsttipo){
                this.lstestudiante.add(new ClsEstudiante(tt.getId(),
                        tt.getApellidos()+""+tt.getNombres(), tt.getNombres(),-1));
            }
        } catch (Exception ex) {
            
        }
     }

     public List<ClsMatricula> ObteneridMatriculaEstudiante() {
         
            this.lstmatricula = new ArrayList<ClsMatricula>();
            try{
             DaoTMatricula daomatricula = new DaoTMatricula();
         
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
//            clsmatricula = (ClsMatricula) lstmatricula;
            boolean a =false;
         }catch(Exception e){
             
         }
         return lstmatricula;
     }
     
     public void registrarTitulacion() throws Exception {
         DaoTitulacion daotitulacion = new DaoTitulacion();
         DaoTMatricula daomatricula = new DaoTMatricula();
         boolean band = false;
         ObteneridMatriculaEstudiante();
        // onEstudianteChange();

        //Variable para saber si esta registrada
//        clsmatricula = (ClsMatricula) daomatricula.getMatriculaEstudiante(clsestudiante.getId());
        
        boolean repetida = false;
            ttipotitulacion.setId(this.clstipotitulacion.getId());
            ttitulacion.setTipoTitulacion(ttipotitulacion);
            ttitulacion.setFechaFin(ttitulacion.getFechaFin());
            ttitulacion.setFechaInicio(ttitulacion.getFechaInicio());
            
            tmatricula.setId(lstmatricula.get(0).getId());
            ttitulacion.setMatricula(tmatricula);
            ttitulacion.setEstado('E');
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
            vaciarCajas();
            mensajesError("Registro repetido: Estudiante");            
        }else{
            CargarTitulacionNO();
            vaciarCajas();            
            if(msg)
                mensajesOk("Datos procesados bien");            
            else
                mensajesError("error al intentar procesar");
                }
        
     }
     
     public void CargarTitulacionok(){
             DaoTitulacion daotitula = new DaoTitulacion(); 
         lsttitulacion = new ArrayList<>();
         try{
         lsttitulacin = daotitula.getTitulacionOK();
             
         }catch(Exception e){             
            Logger.getLogger(MbVtitulacion.class.getName()).log(Level.SEVERE, null, e);
         }
     }
     public void CargarTitulacionNO(){
         
             DaoTitulacion daotitula = new DaoTitulacion(); 
         lsttitulacion1 = new ArrayList<>();
         try{ 
         lsttitulacin = daotitula.getTitulacionNO();
         
         }catch(Exception e){             
            Logger.getLogger(MbVtitulacion.class.getName()).log(Level.SEVERE, null, e);
         }
     }
     public void registrarCargo(){
         DaoTitulacion daoTmaestrias = new DaoTitulacion();
        boolean band = false;
//        CargarTitulacionok();//        cargarTablaCargos();        //Variable para saber si esta registrada ya la maestria promocion
        boolean repetida = false;        
        try {
            tcargo.setEstado('1');
            //invocando al metodo que obtienesi ya esta la maestria ingresada
            List<Cargo> lstMaestria = (List<Cargo>)daoTmaestrias.getCargosxDescripcion(tcargo.getDescripcion());

            if(lstMaestria.size() > 0){
                repetida = true;
            }
            else{
                //Si no existe se la registra
                msg =  daoTmaestrias.registrarCargo(tcargo);
            }
            
        } catch (Exception e) {
            vaciarCajas();
        }
        
        if(repetida){
            mensajesError("Registro repetido");
        }else{
            cargarTablaCargos();
            vaciarCajas();
                if(msg)
                    mensajesOk("Datos procesados correctamente");
                else
                    mensajesError("Error al procesar datos");
        }   
     }
     public void cargarTablaCargos(){
         lstcargo = new ArrayList<>();
        try {
            lstcargo.clear();
            List<Cargo> lstMaestria = null;
            DaoTitulacion daoTmaestria = new DaoTitulacion();
            if(cargoDescripcion == null){
                lstMaestria = daoTmaestria.getCargosD("");
            }else{
                lstMaestria = daoTmaestria.getCargosD(cargoDescripcion);
            }
            
            
            if(lstMaestria != null){
                if(lstMaestria.size() > 0){
                    for(Cargo maestria : lstMaestria){
                        lstcargo.add(new ClsCargo(maestria.getId(),
                                maestria.getDescripcion(),
                                maestria.getEstado()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void registrarPersona(){
         DaoTUsuario daousuario = new DaoTUsuario();
          boolean band = false;
          boolean repetida = false;        
          try{
              msg = daousuario.registrarPersona(tpersona);
          }catch(Exception e){
          }
          
          if(repetida){
            mensajesError("Registro repetido");
        }else{CargarPersona();
            vaciarCajas();
                if(msg)
                    mensajesOk("Datos procesados correctamente");
                else
                    mensajesError("Error al procesar datos");
        }
          
     }
     public void CargarPersona(){
         try{
         DaoTUsuario daousu= new DaoTUsuario();
         lstPersona = daousu.getPersonas();
         }catch(Exception e){}
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
