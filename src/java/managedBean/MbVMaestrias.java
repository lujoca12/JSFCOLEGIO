/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsMaestria;
import Clases.ClsTablaMaestriaPromocion;
import Clases.ClsProfesor;
import Clases.ClsTablaMaestria;
import Dao.DaoTMaestrias;
import Dao.DaoTPromocion;
import Dao.DaoTUsuario;
import Pojo.Maestria;
import Pojo.Precio;
import Pojo.Promocion;
import Pojo.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author server
 */
@Named(value = "mbVMaestrias")
@ViewScoped
public class MbVMaestrias implements Serializable{

    private boolean msg = false;
    private Maestria tMaestria;
    private Promocion tPromocion;
    
    private ClsTablaMaestriaPromocion clsTblMaestriaPromocion;
    private List<ClsTablaMaestriaPromocion> lstTblMaestriaPromocion;
    
    
    private ClsTablaMaestria clsTablamaestria;
    private List<ClsTablaMaestria> lstTablamaestria;
    
    private ClsMaestria themeMaestria; 
    private List<ClsMaestria> lstThemeMaestria;
    
    private ClsProfesor theme; 
    private List<ClsProfesor> lstTheme;
    
    private List<SelectItem> lstDirector;
    List<Promocion> lstPromocion;
    
    private int idMaestria;
    private int idDirector;
    private Date date;
    private String maestriaDescripcion;
    
    private BigDecimal precioMatricula;
    private BigDecimal precioColegiatura;
    private boolean mostrarEliminados;
    
    public MbVMaestrias() {
        tMaestria = new Maestria();
        tPromocion = new Promocion();
        tPromocion.setFechaInicio(date);
        llenarCboDirector();
        llenarCboMaestria();
        cargarTablaMaestriaPromocion();
        cargarTablaMaestria();
    }
    
    public List<String> completeText(String query) {
        DaoTMaestrias daoTmaestria = new DaoTMaestrias();
        List<Maestria> lstMaestria = null;
        try {
            lstMaestria = daoTmaestria.getMaestriasxDescripcion(query);
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < lstMaestria.size(); i++) {
            results.add(lstMaestria.get(i).getDescripcion());
        }
         
        return results;
    }
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
    public Maestria gettMaestria() {
        return tMaestria;
    }

    public void settMaestria(Maestria tMaestria) {
        this.tMaestria = tMaestria;
    }

    public Promocion gettPromocion() {
        return tPromocion;
    }

    public void settPromocion(Promocion tPromocion) {
        this.tPromocion = tPromocion;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public List<ClsProfesor> getLstTheme() {
        
        return lstTheme;
    }

    public ClsTablaMaestriaPromocion getClsTblMaestria() {
        return clsTblMaestriaPromocion;
    }

    public void setClsTblMaestria(ClsTablaMaestriaPromocion clsTblMaestriaPromocion) {
        this.clsTblMaestriaPromocion = clsTblMaestriaPromocion;
    }

    public List<ClsTablaMaestriaPromocion> getLstTblMaestria() {
        return lstTblMaestriaPromocion;
    }

    public ClsTablaMaestria getClsTablamaestria() {
        return clsTablamaestria;
    }

    public void setClsTablamaestria(ClsTablaMaestria clsTablamaestria) {
        this.clsTablamaestria = clsTablamaestria;
    }

    public List<ClsTablaMaestria> getLstTablamaestria() {
        return lstTablamaestria;
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

    public String getMaestriaDescripcion() {
        return maestriaDescripcion;
    }

    public void setMaestriaDescripcion(String maestriaDescripcion) {
        this.maestriaDescripcion = maestriaDescripcion;
    }
    
    public BigDecimal getPrecioMatricula() {
        return precioMatricula;
    }

    public void setPrecioMatricula(BigDecimal precioMatricula) {
        this.precioMatricula = precioMatricula;
    }

    public BigDecimal getPrecioColegiatura() {
        return precioColegiatura;
    }

    public void setPrecioColegiatura(BigDecimal precioColegiatura) {
        this.precioColegiatura = precioColegiatura;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    
    

    public void llenarCboDirector(){
        this.lstTheme = new ArrayList<ClsProfesor>();
         try {
            DaoTUsuario daoTusuario = new DaoTUsuario();
            
            List<Usuario> lstUsuario = daoTusuario.getDocentes(mostrarEliminados);
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1,"Ninguno","Ninguno"));
            for(Usuario user: lstUsuario){
                this.lstTheme.add(new ClsProfesor(user.getId(), 
                        user.getApellidos()+" "+user.getNombres(), 
                        user.getApellidos()+" "+user.getNombres()));
            }
        } catch (Exception ex) {
            
        }
    }
    
    public void llenarCboMaestria(){
        this.lstThemeMaestria = new ArrayList<ClsMaestria>();
         try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstMaestria = daoTmaestria.getMaestriasD("", mostrarEliminados);
            this.lstThemeMaestria.clear();
            this.lstThemeMaestria.add(new ClsMaestria(-1,"Ninguno","Ninguno",0,0,0,null, null));
            
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

    public ClsProfesor getTheme() {
        return theme;
    }

    public void setTheme(ClsProfesor theme) {
        this.theme = theme;
    }
    
    public void cargarTablaMaestriaPromocion(){
        lstTblMaestriaPromocion = new ArrayList<>();
        try {
            lstTblMaestriaPromocion.clear();
            DaoTPromocion daoTmodulo = new DaoTPromocion();
            //lstPromocion = null;
            if(maestriaDescripcion == null){
                lstPromocion = daoTmodulo.getPromocionesMaestrias("", mostrarEliminados);
            }else{
                lstPromocion = daoTmodulo.getPromocionesMaestrias(maestriaDescripcion, mostrarEliminados);
            }
            
            if(lstPromocion != null){
                if(lstPromocion.size() > 0){
                    for(Promocion promocion : lstPromocion){
                        lstTblMaestriaPromocion.add(new ClsTablaMaestriaPromocion(promocion.getMaestria().getId(),
                                promocion.getMaestria().getDescripcion(),
                                promocion.getMaestria().getEstado(),
                                promocion.getId(),
                                promocion.getDescripcion(),
                                promocion.getFechaResolucion(),
                                promocion.getFechaInicio(),
                                promocion.getFechaFin(),
                                promocion.getCupo() == null ? 0 :promocion.getCupo(),
                                promocion.getNCuotas(),
                                promocion.getIdUsuario(),
                                promocion.getUsuario(),
                                promocion.getResolucion(),
                                precioMatricula,
                                precioColegiatura));
                    }
                }
                List<Precio> lstPrecio = null;
                for (int i = 0; i < lstTblMaestriaPromocion.size(); i++) {
                    lstPrecio = daoTmodulo.getPromocionesPrecios(lstTblMaestriaPromocion.get(i).getIdPromocion());
                    
                    if(lstPrecio.size() > 0){
                        for (int j = 0; j < lstPrecio.size(); j++) {
                            if(lstPrecio.get(j).getTipoPrecio().getId() == 1)
                                lstTblMaestriaPromocion.get(i).setPrecioMatricula(lstPrecio.get(j).getValor());
                            else if(lstPrecio.get(j).getTipoPrecio().getId() == 2)
                                lstTblMaestriaPromocion.get(i).setPrecioColegiatura(lstPrecio.get(j).getValor());
                        }
                        
                    }
                }
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaMaestria(){
        lstTablamaestria = new ArrayList<>();
        try {
            lstTablamaestria.clear();
            List<Maestria> lstMaestria = null;
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            if(maestriaDescripcion == null){
                lstMaestria = daoTmaestria.getMaestriasD("", mostrarEliminados);
            }else{
                lstMaestria = daoTmaestria.getMaestriasD(maestriaDescripcion, mostrarEliminados);
            }
            
            
            if(lstMaestria != null){
                if(lstMaestria.size() > 0){
                    for(Maestria maestria : lstMaestria){
                        lstTablamaestria.add(new ClsTablaMaestria(maestria.getId(),
                                maestria.getDescripcion(),
                                maestria.getEstado()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void onMostrarElminados(){
//        cargarTablaMaestria();
//        
//    }
    
    public void registrar(){

        DaoTPromocion daoTpromocion = new DaoTPromocion();
            boolean band = false;

            //Variable para saber si esta registrada ya la maestria promocion
            boolean repetida = false;

        try {
            //Aqui obtnego el id del usuario que seleccione
//            tPromocion.setIdUsuario(this.theme.getId());
//            tPromocion.setUsuario(this.theme.getName());
            tPromocion.setEstado('1');
            //Establesco en 1 para habilitarlo
            tMaestria.setEstado('1');

            int i = 0;

            i = daoTpromocion.getUltimoidPromocion(this.themeMaestria.getDisplayName());
            tPromocion.setDescripcion(i + 1);
            tMaestria.setId(this.themeMaestria.getId());
            tPromocion.setMaestria(tMaestria);
            //Si la maestria existe comparo que no este repetida
            repetida = daoTpromocion.existe(tPromocion);
            if (!repetida) {
                msg = daoTpromocion.registrar(tPromocion, precioMatricula, precioColegiatura);
            }

        } catch (Exception e) {
            vaciarCajas();
        }

        if (repetida) {
            mensajesError("Registro repetido");
        } else {
            cargarTablaMaestriaPromocion();
            vaciarCajas();
            if (msg) {
                mensajesOk("Datos procesados correctamente");
            } else {
                mensajesError("Error al procesar datos");
            }
        }
        
        
    }
    
    public void registrarMaestria(){
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        boolean band = false;
        
        //Variable para saber si esta registrada ya la maestria promocion
        boolean repetida = false;
        
        try {
            tMaestria.setEstado('1');
            //invocando al metodo que obtienesi ya esta la maestria ingresada
            List<Maestria> lstMaestria = (List<Maestria>)daoTmaestrias.getMaestriasxDescripcion(tMaestria.getDescripcion());

            if(lstMaestria.size() > 0){
                repetida = true;
            }
            else{
                //Si la tesis no existe se la registra
                msg =  daoTmaestrias.registrar(tMaestria);
            }
            
        } catch (Exception e) {
            vaciarCajas();
        }
        
        if(repetida){
            mensajesError("Registro repetido");
        }else{
            cargarTablaMaestria();
            vaciarCajas();
                if(msg)
                    mensajesOk("Datos procesados correctamente");
                else
                    mensajesError("Error al procesar datos");
        }
    }
    
    private void vaciarCajas(){
        tMaestria = new Maestria();
        tPromocion = new Promocion();
        idDirector = 0;
        llenarCboDirector();
        llenarCboMaestria();
        precioMatricula = null;
        precioColegiatura = null;
    }
    
    private void mensajesOk(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    private void mensajesError(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onRowEdit(RowEditEvent event) {
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        Promocion promocion = new Promocion();
        promocion.setCupo(((ClsTablaMaestriaPromocion) event.getObject()).getCupo());
        promocion.setNCuotas(((ClsTablaMaestriaPromocion) event.getObject()).getCuotas());
        promocion.setDescripcion(((ClsTablaMaestriaPromocion) event.getObject()).getDescripcionP());
        
        if(theme== null){
            promocion.setIdUsuario(((ClsTablaMaestriaPromocion) event.getObject()).getIdUsuario());
            promocion.setUsuario(((ClsTablaMaestriaPromocion) event.getObject()).getNombresUsuarios());
        }else{
            promocion.setIdUsuario(theme.getId());
            promocion.setUsuario(theme.getName());
        }
        promocion.setFechaResolucion(((ClsTablaMaestriaPromocion) event.getObject()).getFechaResolucion());
        promocion.setFechaInicio(((ClsTablaMaestriaPromocion) event.getObject()).getFechaInicio());
        promocion.setFechaFin(((ClsTablaMaestriaPromocion) event.getObject()).getFechaFin());
        promocion.setEstado('1');
        Maestria maestria = new Maestria();
        maestria.setId(((ClsTablaMaestriaPromocion) event.getObject()).getIdMaestria());
        promocion.setId(((ClsTablaMaestriaPromocion) event.getObject()).getIdPromocion());
        promocion.setMaestria(maestria);
        promocion.setResolucion(((ClsTablaMaestriaPromocion) event.getObject()).getN_resolucion());
        precioMatricula = ((ClsTablaMaestriaPromocion) event.getObject()).getPrecioMatricula();
        precioColegiatura = ((ClsTablaMaestriaPromocion) event.getObject()).getPrecioColegiatura();
        try {
            msg = daoTpromocion.update(promocion, precioMatricula, precioColegiatura);
            cargarTablaMaestriaPromocion();
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
        
    }
    
    public void onRowEditMaestria(RowEditEvent event) {
        boolean repetida = false;
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        tMaestria.setId(((ClsTablaMaestria) event.getObject()).getIdMaestria());
        tMaestria.setDescripcion(((ClsTablaMaestria) event.getObject()).getDescripcionM());
        tMaestria.setEstado('1');
        
        try {
            List<Maestria> lstMaestria = (List<Maestria>)daoTmaestrias.getMaestriasxDescripcion(tMaestria.getDescripcion());

            if(lstMaestria.size() > 0){
                repetida = true;
            }
            else{
                msg = daoTmaestrias.update(tMaestria);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(repetida){
            mensajesError("Registro repetido");
        } else{ 
            if (msg) {
                cargarTablaMaestria();
                mensajesOk("Datos actualizados correctamente");
            }else {
                mensajesError("Error al actualizar datos");
            }
        }
        
    }
    
    public void onRowCancel(RowEditEvent event) {
        
        cargarTablaMaestriaPromocion();
    }
    public void onDelete(ClsTablaMaestriaPromocion clsTblMaestrias){
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        Promocion promocion = new Promocion();
        
        promocion.setId(clsTblMaestrias.getIdPromocion());
        promocion.setCupo(clsTblMaestrias.getCupo());
        promocion.setDescripcion(clsTblMaestrias.getDescripcionP());
        promocion.setEstado('0');
        promocion.setFechaFin(clsTblMaestrias.getFechaFin());
        promocion.setFechaInicio(clsTblMaestrias.getFechaInicio());
        promocion.setFechaResolucion(clsTblMaestrias.getFechaResolucion());
        promocion.setIdUsuario(clsTblMaestrias.getIdUsuario());
        promocion.setNCuotas(clsTblMaestrias.getCuotas());
        promocion.setResolucion(clsTblMaestrias.getN_resolucion());
        promocion.setUsuario(clsTblMaestrias.getNombresUsuarios());
        Maestria maestria = new Maestria();
        maestria.setId(clsTblMaestrias.getIdMaestria());
        promocion.setMaestria(maestria);
        precioMatricula = (clsTblMaestrias.getPrecioMatricula());
        precioColegiatura = (clsTblMaestrias.getPrecioColegiatura());
        
        try {
            msg = daoTpromocion.update(promocion, precioMatricula,precioColegiatura);
            cargarTablaMaestriaPromocion();
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar datos");
        }
    }
    
    public void onRecuperacionMaestria(ClsTablaMaestriaPromocion clsTblMaestrias){
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        Promocion promocion = new Promocion();
        
        promocion.setId(clsTblMaestrias.getIdPromocion());
        promocion.setCupo(clsTblMaestrias.getCupo());
        promocion.setDescripcion(clsTblMaestrias.getDescripcionP());
        promocion.setEstado('1');
        promocion.setFechaFin(clsTblMaestrias.getFechaFin());
        promocion.setFechaInicio(clsTblMaestrias.getFechaInicio());
        promocion.setFechaResolucion(clsTblMaestrias.getFechaResolucion());
        promocion.setIdUsuario(clsTblMaestrias.getIdUsuario());
        promocion.setNCuotas(clsTblMaestrias.getCuotas());
        promocion.setResolucion(clsTblMaestrias.getN_resolucion());
        promocion.setUsuario(clsTblMaestrias.getNombresUsuarios());
        Maestria maestria = new Maestria();
        maestria.setId(clsTblMaestrias.getIdMaestria());
        maestria.setDescripcion(clsTblMaestrias.getDescripcionM());
        promocion.setMaestria(maestria);
        precioMatricula = (clsTblMaestrias.getPrecioMatricula());
        precioColegiatura = (clsTblMaestrias.getPrecioColegiatura());
        
        try {
            List<Promocion> lstPromocion = daoTpromocion.getValidacionPromocionesMaestrias(maestria.getDescripcion(),promocion.getDescripcion());
            if(lstPromocion.size() <= 0){
                msg = daoTpromocion.update(promocion, precioMatricula,precioColegiatura);
                cargarTablaMaestriaPromocion();
            }else{
                mensajesError("error! maestría-promoción repetida");
                return;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Dato recuperado correctamente");
        } else {
            mensajesError("Error al recuperar dato");
        }
    }
    
    public void onDeleteMaestria(ClsTablaMaestria clsTblMaestriaD){
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        
        tMaestria.setId(clsTblMaestriaD.getIdMaestria());
        tMaestria.setDescripcion(clsTblMaestriaD.getDescripcionM());
        tMaestria.setEstado('0');
        
        try {
            msg = daoTmaestrias.update(tMaestria);
            cargarTablaMaestria();
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar datos");
        }
    }
    
    public void onRecuperar(ClsTablaMaestria clsTblMaestriaD){
        boolean repetida = false;
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        
        tMaestria.setId(clsTblMaestriaD.getIdMaestria());
        tMaestria.setDescripcion(clsTblMaestriaD.getDescripcionM());
        tMaestria.setEstado('1');
        
        try {
            List<Maestria> lstMaestria = (List<Maestria>)daoTmaestrias.getMaestriasxDescripcion(tMaestria.getDescripcion());

            if(lstMaestria.size() > 0){
                repetida = true;
            }
            else{
                msg = daoTmaestrias.update(tMaestria);
                cargarTablaMaestria();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(repetida){
            mensajesError("Registro repetido");
        } else{
            if (msg) {
                mensajesOk("Dato Restaurado correctamente");
            } else {
                mensajesError("No se pudo recuperar el dato");
            }
        }
        
    }
    
}
