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
import Dao.DaoTitulacion;


import Pojo.Proyecto;
import Pojo.TipoTribunal;
import Pojo.Usuario;
import Pojo.Maestria;
import Pojo.Estudiante;
import Pojo.TipoTitulacion;
import Pojo.Matricula;
import Pojo.*;
import controladores.MaestriaBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import Clases.*;
import Clases.ClsTablaTesis;
import Clases.ClsMaestria;
import Clases.ClsEstudiante;
import Clases.ClsTitulacion;
import Clases.ClsProfesor;

import Pojo.PalabrasClave;
import Pojo.Titulacion;
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
    private boolean estado = false;    
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
    private Titulacion ttitulacion;
    private TipoTitulacion ttipot;
    private Matricula tmatricula;
    
    private ArrayList<TipoTribunal> lstTheme1;
    private List<SelectItem> lstTodoMaestria;
    private List<SelectItem> lstEstudiantes;
    
    private ClsTipoTitulacion clstipot;
    private List<ClsTipoTitulacion> lstipot;
    
    private ClsMatricula clsmatricula;
    private List<ClsMatricula> lstmatricula;
    
    private ClsProfesor theme;
    private List<ClsProfesor> lstTheme;
    
    ClsTitulacion clstitulacion;
    private List<ClsTitulacion> lsttitulacion;
    
    private List<Titulacion> lstitulacion = new ArrayList<>();
    private List<Proyecto> lstproyectos = new ArrayList<>();
    
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
 //      llenarCboEstudiantes();
//        llenarTablaTesis();
        llenarCboProyecto();
        llenarCboTutor();
        llenarCboMaestria();
        tMaestria= new Maestria();
        ttitulacion= new Titulacion();
        tTesis = new Proyecto();
        ttipot = new TipoTitulacion();
        tpalabrasclave = new PalabrasClave();
        tEstudiante= new Estudiante();
    }   
    private void vaciarCajas(){
        tEstudiante= new Estudiante();
        tMaestria= new Maestria();
        tTesis = new Proyecto();
        ttitulacion= new Titulacion();
        tpalabrasclave = new PalabrasClave();
    }

    public List<Proyecto> getLstproyectos() {
        return lstproyectos;
    }

    public void setLstproyectos(List<Proyecto> lstproyectos) {
        this.lstproyectos = lstproyectos;
    }

    public List<Titulacion> getLstitulacion() {
        return lstitulacion;
    }

    public void setLstitulacion(List<Titulacion> lstitulacion) {
        this.lstitulacion = lstitulacion;
    }

    
    public ClsProfesor getTheme() {
        return theme;
    }

    public void setTheme(ClsProfesor theme) {
        this.theme = theme;
    }

    public List<ClsProfesor> getLstTheme() {
        return lstTheme;
    }

    public void setLstTheme(List<ClsProfesor> lstTheme) {
        this.lstTheme = lstTheme;
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
     public void llenarCboProyecto(){
         this.LstTablatesis = new ArrayList<ClsTablaTesis>();
         try {
            DaoTesis daoTmaestria = new DaoTesis();
            
            List<Proyecto> lstMaestria = daoTmaestria.getTodasProyectoxEstado("E");
            this.LstTablatesis.clear();
            this.LstTablatesis.add(new ClsTablaTesis(-1,"(Seleccione)"));
            
            for(Proyecto maestria: lstMaestria){
                this.LstTablatesis.add(new ClsTablaTesis(maestria.getId(), 
                        maestria.getAutor(), maestria.getTitulo(), 
                        maestria.getFechaSustentacion(), maestria.getFechaSubida(), 
                        maestria.getRuta(), maestria.getResumen(), maestria.getMaestria(),
                        maestria.getTutor(), 
                        maestria.getEstado(), 
                        maestria.getTitulacion().getId(),
                        maestria.getUsuario().getId()));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void llenarCboEstudiantes(){
//        this.lstestudiante = new ArrayList<ClsEstudiante>();
//        try{
//            DaoTEstudiante daoestudiante = new DaoTEstudiante();
//            
//            List<Estudiante> lstestu = daoestudiante.getEstudiantes();
//            this.lstestudiante.clear();
//            this.lstestudiante.add(new ClsEstudiante(-1,"Ninguno","Ninguno",-1));
//            
//            for(Estudiante e : lstestu){
//                this.lstestudiante.add(new ClsEstudiante(
//                        e.getId(), 
//                        e.getNombres()+" "+e.getApellidos(), 
//                        e.getNombres()+" "+e.getApellidos(),-1));
//                //this.lstestudiante.add(new ClsEstudiante(e.getId(), idmaestria, titulo, autor, titulo, titulo, titulo, ruta, fechaSubida, msg, msg));
//            }
//            
//        }catch (Exception e){
//            
//        }
    }
    public List<SelectItem> getLstEstudiantes() {
       this.lstEstudiantes = new ArrayList<SelectItem>();
        try {
            
            DaoTEstudiante DaoEstudia = new DaoTEstudiante();
            DaoTMaestrias DaoTMaestria1 = new DaoTMaestrias();

            List<Estudiante> lstTEstudia = DaoEstudia.getEstudiantes();
            List<Maestria> lstTMaestria = DaoTMaestria1.getMaestriasD("", false);                      
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

            List<Maestria> lstTMaestria = DaoTMaestria.getMaestriasD("", false);
            lstTodoMaestria.clear();
            for (Maestria tipoUser : lstTMaestria) {
                SelectItem usuarioItem = new SelectItem(tipoUser.getId(), tipoUser.getDescripcion());
                this.lstTodoMaestria.add(usuarioItem);
            }
        } catch (Exception ex) {

        }
        return lstTodoMaestria;
    }
        
    public void llenarCboTutor() {
        this.lstTheme = new ArrayList<ClsProfesor>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();

            List<Usuario> lstUsuario = daoTusuario.getDocentes(false);
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1, "Ninguno", "Ninguno"));
            for (Usuario user : lstUsuario) {
                this.lstTheme.add(new ClsProfesor(user.getId(), user.getApellidos() + " " + user.getNombres(), user.getApellidos() + " " + user.getNombres()));
            }
        } catch (Exception ex) {

        }

    }
        
    public void llenarTablaTesis()
    {
            DaoTesis daoTesis = new DaoTesis();
          LstTablatesis = new ArrayList<>();
        try {
            lstproyectos = daoTesis.getProyecto();
            LstTablatesis.clear();            
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
                                tes.getMaestria(),
                                tes.getTutor(),
                                tes.getEstado(),
                                tes.getTitulacion().getId(),
                                tes.getUsuario().getId()));
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
////        LstTablatesis= new ArrayList<>();
////        try{
////            LstTablatesis.clear();
////           DaoTesis dtesis = new DaoTesis();
////           List<Proyecto> lsttesis= dtesis.getProyectoxAutor(this.autor);
////           if (lsttesis.size() > 0) {
////               for(Proyecto tesi : lsttesis){
////                   LstTablatesis.add(new ClsTablaTesis(
////                           tesi.getId(), 
////                           tesi.getAutor(), 
////                           tesi.getTitulo(),
////                           tesi.getFechaSustentacion(), 
////                           tesi.getFechaSubida(), 
////                           tesi.getRuta(), 
////                           tesi.getResumen(), 
////                           1, 
////                           "") );
////                  // LstTablatesis.add(new ClsTablaTesis(id, autor, titulo, fechaSubida, fechaSubida, ruta, resumen, tMaestria, tEstudiante) );
////               }
////           }           
////        }
////        catch (Exception e){
////            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, e);
////        }
        
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
    public void onEstudianteChange() throws Exception{
       try{
        lstestudiante = new ArrayList<>();
        lstestudiante.clear();
        this.lstestudiante.add(new ClsEstudiante(-1, "(Escoja un Estudiante)","(Escoja un Estudiante)",-1));
        DaoTEstudiante daoestudiante = new DaoTEstudiante();
        List<Estudiante> lstestud= null;
        
        if(clsMaestria != null)
            lstestud = daoestudiante.getEstudiantesMaestriaTitulacion(clsMaestria.getId());
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
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestriasD("", false);
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"(Escoja una Maestría)","(Escoja una Maestría)",0,0,0, null,null));
            
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
    
    public List<ClsTitulacion> obteneridtitulacionestu(){
            DaoTitulacion daotitul = new DaoTitulacion();
         this.lsttitulacion = new ArrayList<>();
        try{
            lstitulacion = daotitul.getTitulacionEstudianteMaestria(this.clsestudiante.getId(), this.clsMaestria.getId());
            List<Titulacion> resul = daotitul.getTitulacionEstudianteMaestria(this.clsestudiante.getId(), this.clsMaestria.getId());
            this.lsttitulacion.clear();
            for(Titulacion tt : resul){
                lsttitulacion.add(new ClsTitulacion(tt.getId(), 
                        tt.getNota(), 
                        tt.getFechaInicio(),tt.getFechaFin(),
                        tt.getTipoTitulacion().getId(), 
                        tt.getMatricula().getId(), 
                        tt.getEstado()));
            }
        }
        catch(Exception e){
        }
        return lsttitulacion;
        
    }
    public List<ClsTipoTitulacion> tipotitulacionid(){
        this.lstipot = new ArrayList<>();
        try{
            DaoTitulacion daotitul = new DaoTitulacion();
            List<TipoTitulacion> resul = daotitul.gettipoTitulacionid(this.lsttitulacion.get(0).getId());
            this.lstipot.clear();
            for(TipoTitulacion tt : resul){
                lstipot.add(new ClsTipoTitulacion(tt.getId(), tt.getDescripcion(), tt.getDescripcion()));
            }
        }
        catch(Exception e){
        }
        return lstipot;
    }
    public List<ClsMatricula> matriculaID(){
         this.lstmatricula = new ArrayList<>();
        try{
            DaoTitulacion daotitul = new DaoTitulacion();
            List<Matricula> resul = daotitul.getMatriculaid(this.lsttitulacion.get(0).getId());
            this.lstmatricula.clear();
            for(Matricula tt : resul){
                lstmatricula.add(new ClsMatricula(tt.getId(), tt.getEstado()));
            }
        }
        catch(Exception e){
        }
        return lstmatricula;
    }
    
    public void registrarTesis(){        
        obteneridtitulacionestu();
        tipotitulacionid();
        matriculaID();
        //Variable para saber si esta registrada
        boolean repetida = false;       
        boolean pcc = false;
        
        Usuario usuario = new Usuario();
        usuario.setId(theme.getId());
        tTesis.setUsuario(usuario);
        tTesis.setTutor(theme.getDisplayName());
        DaoTesis daoTesis = new DaoTesis();    DaoTitulacion daot= new DaoTitulacion();
        tTesis.setAutor(this.clsestudiante.getApellidos());
        tTesis.setMaestria(this.clsMaestria.getDisplayName()); 
        
        tEstudiante.setId(this.clsestudiante.getId());  
        //tTesis. setEstudiante(tEstudiante);        
        
        tTesis.setFechaSubida(this.tTesis.getFechaSubida());
        tTesis.setFechaSustentacion(this.tTesis.getFechaSustentacion());
        
        tMaestria.setId(this.clsMaestria.getId());       
        //tTesis.setMaestria(tMaestria);
        ttitulacion.setId(lsttitulacion.get(0).getId());
        tTesis.setTitulacion(ttitulacion);
        
       // tTesis.setResumen(this.tTesis.getResumen());       
        //tTesis.setRuta(this.tTesis.getRuta());
        tTesis.setEstado('E');
        tTesis.setTitulo(this.tTesis.getTitulo());        
        try{
            //List<Titulacion> lstT=(List<Titulacion>) daot.getTitulacionEstudianteMaestria(this.clsestudiante.getId(), this.clsMaestria.getId());
            List<Proyecto> lstTesis=(List<Proyecto>) daoTesis.getProyectoxTitulo(tTesis.getTitulo());
            if(lstTesis.size() > 0){
                repetida = true;
            }
            else{
                //Si la maestria no existe se la registra
                msg =  daoTesis.registrarProyecto(tTesis);
            }                    
            ttitulacion.setId(lstitulacion.get(0).getId());
            ttitulacion.setFechaFin(lstitulacion.get(0).getFechaFin());
            ttitulacion.setFechaInicio(lstitulacion.get(0).getFechaInicio());
            
            ttipot.setId(lstipot.get(0).getId());
            ttitulacion.setTipoTitulacion(lstitulacion.get(0).getTipoTitulacion());
            //tmatricula.setId(lstmatricula.get(0).getId());
            ttitulacion.setMatricula(lstitulacion.get(0).getMatricula());
            
            ttitulacion.setEstado('G');
            msg = daot.update(ttitulacion);
        }
        catch (Exception e){
            vaciarCajas();
        }
        if(repetida){
            mensajesError("Registro repetido");            
        }else{
          //  pcc = registrarPC(tTesis);
            vaciarCajas();            
            if(msg){
                llenarCboMaestria();
                mensajesOk("Datos procesados bien");  
            
            }
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
