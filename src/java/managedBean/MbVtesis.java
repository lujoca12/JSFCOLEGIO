/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTEstudiante;
import Dao.DaoTMaestrias;
import Dao.DaoTUsuario;
import Dao.DaoTesis;

import Pojo.Proyecto;
import Pojo.TipoTribunal;
import Pojo.Usuario;
import Pojo.Maestria;
import Pojo.Estudiante;

import controladores.MaestriaBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import Clases.ClsTablaTesis;
import Clases.ClsMaestria;
import Clases.ClsEstudiante;
import Pojo.PalabrasClave;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVtesis")
@ViewScoped
public class MbVtesis implements Serializable{
    
    private boolean msg=false;
    
    private Map<String, String> maestrias;
    private String idMaestria;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private MaestriaBean mBean;

    
     private int id;
     private int idestudiante;
     private int idmaestria;
     private String titulo;
     private String autor;
     private Date fechaSustentacion;
     private String ruta;
     private String resumen;
     private Date fechaSubida;
     
     private String pc1,pc2,pc3,pc4,pc5;
     private PalabrasClave tpalabrasclave;
    
    private Maestria tMaestria;    
    private Proyecto tTesis;
    private Estudiante tEstudiante;
    private ArrayList<TipoTribunal> lstTheme;
    private List<SelectItem> lstTodoMaestria;
    private List<SelectItem> lstEstudiantes;
    
    ClsMaestria clsMaestria;
    private List<ClsMaestria> lstThemeMaestria;
    
    ClsTablaTesis clsTablaTesis; 
    private List<ClsTablaTesis> LstTablatesis;
    
    ClsEstudiante clsestudiante;
    private List<ClsEstudiante> lstestudiante;

    /**
     * Creates a new instance of MbVtesis
     */
    public MbVtesis(){
        llenarCboEstudiantes();
        llenarTablaTesis();
        llenarCboMaestria();
        tMaestria= new Maestria();
        tTesis = new Proyecto();
        tpalabrasclave = new PalabrasClave();
        tEstudiante= new Estudiante();
    }
            
    private void vaciarCajas(){
        tTesis = new Proyecto();
        tpalabrasclave = new PalabrasClave();
    }

    public String getPc1() {
        return pc1;
    }

    public PalabrasClave getTpalabrasclave() {
        return tpalabrasclave;
    }

    public void setTpalabrasclave(PalabrasClave tpalabrasclave) {
        this.tpalabrasclave = tpalabrasclave;
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

    public void setLstestudiante(List<ClsEstudiante> lstestudiante) {
        this.lstestudiante = lstestudiante;
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

    public Date getFechaSustentacion() {
        return fechaSustentacion;
    }

    public void setFechaSustentacion(Date fechaSustentacion) {
        this.fechaSustentacion = fechaSustentacion;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    
    

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    

    public ClsMaestria getClsMaestria() {
        return clsMaestria;
    }

    public void setClsMaestria(ClsMaestria clsMaestria) {
        this.clsMaestria = clsMaestria;
    }

    public List<ClsMaestria> getLstThemeMaestria() {
        return lstThemeMaestria;
    }

    public void setLstThemeMaestria(List<ClsMaestria> lstThemeMaestria) {
        this.lstThemeMaestria = lstThemeMaestria;
    }
    

    public ClsTablaTesis getClsTablaTesis() {
        return clsTablaTesis;
    }

    public void setClsTablaTesis(ClsTablaTesis clsTablaTesis) {
        this.clsTablaTesis = clsTablaTesis;
    }
    
    public Map<String, String> getMaestrias() {
        return maestrias;
    }

    public void setMaestrias(Map<String, String> maestrias) {
        this.maestrias = maestrias;
    }

    public String getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(String idMaestria) {
        this.idMaestria = idMaestria;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public MaestriaBean getmBean() {
        return mBean;
    }

    public void setmBean(MaestriaBean mBean) {
        this.mBean = mBean;
    }
      
    
    public List<ClsTablaTesis> getLstTablatesis() {
        return LstTablatesis;
    }    

    public Proyecto gettTesis() {
        return tTesis;
    }

    public void settTesis(Proyecto tTesis) {
        this.tTesis = tTesis;
    }

    public Estudiante gettEstudiante() {
        return tEstudiante;
    }

    public void settEstudiante(Estudiante tEstudiante) {
        this.tEstudiante = tEstudiante;
    }
    

    public Maestria gettMaestria() {
        return tMaestria;
    }

    public void settMaestria(Maestria tMaestria) {
        this.tMaestria = tMaestria;
    }
          
    public void llenarCboEstudiantes(){
        this.lstestudiante = new ArrayList<ClsEstudiante>();
        try{
            DaoTEstudiante daoestudiante = new DaoTEstudiante();
            
            List<Estudiante> lstestu = daoestudiante.getEstudiantes();
            this.lstestudiante.clear();
            this.lstestudiante.add(new ClsEstudiante(-1,"Ninguno","Ninguno"));
            
            for(Estudiante e : lstestu){
                this.lstestudiante.add(new ClsEstudiante(
                        e.getId(), 
                        e.getNombres()+" "+e.getApellidos(), 
                        e.getNombres()+" "+e.getApellidos()));
                //this.lstestudiante.add(new ClsEstudiante(e.getId(), idmaestria, titulo, autor, titulo, titulo, titulo, ruta, fechaSubida, msg, msg));
            }
            
        }catch (Exception e){
            
        }
    }

    public List<SelectItem> getLstEstudiantes() {
       this.lstEstudiantes = new ArrayList<SelectItem>();
        try {
            
            DaoTEstudiante DaoEstudia = new DaoTEstudiante();
            DaoTMaestrias DaoTMaestria1 = new DaoTMaestrias();

            List<Estudiante> lstTEstudia = DaoEstudia.getEstudiantes();
            List<Maestria> lstTMaestria = DaoTMaestria1.getMaestrias();                      
            lstEstudiantes.clear();
            for (Estudiante tipoUser : lstTEstudia) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getNombres());
                this.lstEstudiantes.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstEstudiantes;
        
    }
        
    public List<SelectItem> getLstTodoMaestria() {
       this.lstTodoMaestria = new ArrayList<SelectItem>();
        try {
            DaoTMaestrias DaoTMaestria = new DaoTMaestrias();

            List<Maestria> lstTMaestria = DaoTMaestria.getMaestrias();
            lstTodoMaestria.clear();
            for (Maestria tipoUser : lstTMaestria) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getDescripcion());
                this.lstTodoMaestria.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodoMaestria;
    }
        
    public void llenarCboTipotribunal() {
//        this.lstTheme = new ArrayList<TipoTribunal>();
//        try {
//            DaoTUsuario daoTusuario = new DaoTUsuario();
//
//            List<Usuario> lstUsuario = daoTusuario.getDocentes();
//            this.lstTheme.clear();
//            this.lstTheme.add(new TipoTribunal(-1, "Ninguno"));
//            for (Usuario user : lstUsuario) {
//                this.lstTheme.add(new TipoTribunal(user.getId(), 
//                        user.getApellidos() + " " + user.getNombres()));
//            }
//        } catch (Exception ex) {
//
//        }
    }
    
    public void llenarTablaTesis()
    {
          LstTablatesis = new ArrayList<>();
        try {
            LstTablatesis.clear();            
            DaoTesis daoTesis = new DaoTesis();
            List<Proyecto> lsttesis = daoTesis.getProyecto();            
            if(lsttesis != null){
                if(lsttesis.size() > 0){
                    for(Proyecto tes : lsttesis){   
                        LstTablatesis.add(new ClsTablaTesis(tes.getId(),
                                tes.getAutor(), 
                                tes.getTitulo(), 
                                tes.getFechaSustentacion(),
                                tes.getFechaSubida() , 
                                tes.getRuta(),
                                tes.getResumen(), 
                                null,
                               null));
                      //LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, idMaestria, idEstudiante));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void consultarTesisAutor(){
        if(this.autor==""){
        }else{
            CargarTesisAutor();
        }        
    }
    public void CargarTesisAutor(){
//        LstTablatesis= new ArrayList<>();
//        try{
//            LstTablatesis.clear();
//           DaoTesis dtesis = new DaoTesis();
//           List<Proyecto> lsttesis= dtesis.getProyectoxAutor(this.autor);
//           if (lsttesis.size() > 0) {
//               for(Proyecto tesi : lsttesis){
//                   LstTablatesis.add(new ClsTablaTesis(
//                           tesi.getId(), 
//                           tesi.getAutor(), 
//                           tesi.getTitulo(),
//                           tesi.getFechaSustentacion(), 
//                           tesi.getFechaSubida(), 
//                           tesi.getRuta(), 
//                           tesi.getResumen(), 
//                           1, 
//                           "") );
//                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
//               }
//           }           
//        }
//        catch (Exception e){
//            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
//        }
        
    }
    public void consultarTesisTitulo(){
        if(this.titulo == null){
        }else{
            CargarTesisTitulo();
        }
    }
     public void CargarTesisTitulo(){
//         LstTablatesis= new ArrayList<>();
//        try{
//            LstTablatesis.clear();
//           DaoTesis dtesis = new DaoTesis();
//           List<Proyecto> lsttesis= dtesis.getProyectoxTitulo(this.titulo);
//           if (lsttesis.size() > 0) {
//               for(Proyecto tesi : lsttesis){
//                   LstTablatesis.add(new ClsTablaTesis(
//                           tesi.getId(), 
//                           tesi.getAutor(), 
//                           tesi.getTitulo(),
//                           tesi.getFechaSustentacion(), 
//                           tesi.getFechaSubida(), 
//                           tesi.getRuta(), 
//                           tesi.getResumen(), 
//                           1, 
//                           "") );
//                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
//               }
//           }           
//        }
//        catch (Exception e){
//            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
//        }
     }
    
    public void consultarTesisFechaSus(){
        if(this.fechaSustentacion == null){
        }else{
            CargarTesisFechaSust();
        }
    }
    public void CargarTesisFechaSust(){
//        LstTablatesis= new ArrayList<>();
//        try{
//            LstTablatesis.clear();
//           DaoTesis dtesis = new DaoTesis();
//           List<Proyecto> lsttesis= dtesis.getProyectoxFechaSust(this.fechaSustentacion);
//           if (lsttesis.size() > 0) {
//               for(Proyecto tesi : lsttesis){
//                   LstTablatesis.add(new ClsTablaTesis(
//                           tesi.getId(), 
//                           tesi.getAutor(), 
//                           tesi.getTitulo(),
//                           tesi.getFechaSustentacion(), 
//                           tesi.getFechaSubida(), 
//                           tesi.getRuta(), 
//                           tesi.getResumen(), 
//                           1, 
//                           "tesi.getEstudiante()") );
//                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
//               }
//           }           
//        }
//        catch (Exception e){
//            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
//        }
    }    
    public void llenarCboMaestria(){
        
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestrias();
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0,0,0));
            
            for(Maestria maestria: lstMaestria){
                this.lstThemeMaestria.add(new ClsMaestria(maestria.getId(),
                        maestria.getDescripcion(),
                        maestria.getDescripcion(),
                        maestria.getId(),
                        0,
                        0));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void registrarTesis(){        
        //Variable para saber si esta registrada
        boolean repetida = false;       
        boolean pcc = false;
        
        DaoTesis daoTesis = new DaoTesis();    
        tTesis.setAutor(this.clsestudiante.getNombres());
        
        tEstudiante.setId(this.clsestudiante.getId());  
        //tTesis. setEstudiante(tEstudiante);        
        
        tTesis.setFechaSubida(this.tTesis.getFechaSubida());
        tTesis.setFechaSustentacion(this.tTesis.getFechaSustentacion());
        
        tMaestria.setId(this.clsMaestria.getId());       
        //tTesis.setMaestria(tMaestria);
        
        tTesis.setResumen(this.tTesis.getResumen());       
        tTesis.setRuta(this.tTesis.getRuta());
        tTesis.setTitulo(this.tTesis.getTitulo());        
        try{
            List<Proyecto> lstTesis=(List<Proyecto>) daoTesis.getProyectoxTitulo(tTesis.getTitulo());
            if(lstTesis.size() > 0){
                repetida = true;
            }
            else{
                //Si la maestria no existe se la registra
                msg =  daoTesis.registrarProyecto(tTesis);
            }
        }
        catch (Exception e){
            vaciarCajas();
        }
        if(repetida){
            mensajesError("Registro repetido");            
        }else{
          //  pcc = registrarPC(tTesis);
            vaciarCajas();            
            if(msg)
                mensajesOk("Datos procesados bien");            
            else
                mensajesError("error al intentar procesar");
                }
    }
    
    public void registrarPC(Proyecto ttesi){
        boolean repetida=false;
        DaoTesis daoTesis = new DaoTesis();
        tpalabrasclave.setDescripcion(tpalabrasclave.getDescripcion());
        tpalabrasclave.setProyecto(ttesi);
        
        try{       
            List<Proyecto> lstTesis=(List<Proyecto>) daoTesis.getProyectoxTitulo(tTesis.getTitulo());
            if(lstTesis.size() > 0){
                repetida = true;
            }
            else{
                //Si la maestria no existe se la registra
               // msg =  daoTesis.registrarTesis(tTesis);
                msg= daoTesis.registrarPalabrasClave(tpalabrasclave);
            }    
            
        }catch (Exception e){
            
        }               
        if(repetida){
            mensajesError("Registro repetido");            
        }else{
          //  pcc = registrarPC(tTesis);
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
