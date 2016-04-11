/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsProfesor;
import Clases.ClsTablaMaestrias;
import Dao.DaoTMaestrias;
import Dao.DaoTPromocion;
import Dao.DaoTUsuario;
import Pojo.Maestria;
import Pojo.Modulo;
import Pojo.Promocion;
import Pojo.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

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
    
    private ClsTablaMaestrias clsTblMaestria;
    private List<ClsTablaMaestrias> lstTblMaestria;
    
    private ClsProfesor theme; 
    private List<ClsProfesor> lstTheme;
    private List<SelectItem> lstDirector;
    List<Promocion> lstPromocion;
    
    private int idMaestria;
    private int idDirector;
    private Date date;
    
    public MbVMaestrias() {
        tMaestria = new Maestria();
        tPromocion = new Promocion();
        tPromocion.setFechaInicio(date);
        llenarCboDirector();
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

    public ClsTablaMaestrias getClsTblMaestria() {
        return clsTblMaestria;
    }

    public void setClsTblMaestria(ClsTablaMaestrias clsTblMaestria) {
        this.clsTblMaestria = clsTblMaestria;
    }

    public List<ClsTablaMaestrias> getLstTblMaestria() {
        return lstTblMaestria;
    }
    
    public void llenarCboDirector(){
        this.lstTheme = new ArrayList<ClsProfesor>();
         try {
            DaoTUsuario daoTusuario = new DaoTUsuario();
            
            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            this.lstTheme.clear();
            this.lstTheme.add(new ClsProfesor(-1,"Ninguno","Ninguno"));
            for(Usuario user: lstUsuario){
                this.lstTheme.add(new ClsProfesor(user.getId(), user.getApellidos()+" "+user.getNombres(), user.getApellidos()+" "+user.getNombres()));
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
    
    public void cargarTablaMaestria(){
        lstTblMaestria = new ArrayList<>();
        try {
            lstTblMaestria.clear();
            DaoTPromocion daoTmodulo = new DaoTPromocion();
            //lstPromocion = null;
            lstPromocion = daoTmodulo.getPromocionesMaestrias();
            
            if(lstPromocion != null){
                if(lstPromocion.size() > 0){
                    for(Promocion promocion : lstPromocion){
                        lstTblMaestria.add(new ClsTablaMaestrias(promocion.getMaestria().getId(),
                                promocion.getMaestria().getDescripcion(),promocion.getMaestria().getEstado(),
                                promocion.getId(),promocion.getDescripcion(),promocion.getFechaResolucion(),promocion.getFechaInicio(),
                                promocion.getFechaFin(),promocion.getCupo(),promocion.getNCuotas(),promocion.getIdUsuario(),promocion.getUsuario()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void registrar(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(tPromocion.getFechaFin() );
//        int anioInicio = calendar.get(Calendar.YEAR);
//        
//        calendar.setTime(endDate);
//        int anioFin = calendar.get(Calendar.YEAR);
//        if()
        DaoTMaestrias daoTmaestrias = new DaoTMaestrias();
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        boolean band = false;
        
        //Variable para saber si esta registrada ya la maestria promocion
        boolean repetida = false;
        
        try {
            //Aqui obtnego el id del usuario que seleccione
            tPromocion.setIdUsuario(this.theme.getId());
            tPromocion.setUsuario(this.theme.getName());
            
            //Establesco en 1 para habilitarlo
            tMaestria.setEstado('1');
            
            //invocando al metodo que obtienesi ya esta la maestria ingresada
            List<Maestria> lstMaestria = (List<Maestria>)daoTmaestrias.getMaestriasxDescripcion(tMaestria.getDescripcion());
            
            int i = 0;
            
            //Si la maestria esta registrada entonces sera mayor que cero
            if(lstMaestria.size() > 0){
                i = daoTpromocion.getUltimoidPromocion(tMaestria.getDescripcion());
                tPromocion.setDescripcion(i+1);
                tMaestria.setId(lstMaestria.get(0).getId());
                tPromocion.setMaestria(tMaestria);
                //Si la maestria existe comparo que no este repetida
                repetida = daoTpromocion.existe(tPromocion);
                if(!repetida)
                    msg = daoTpromocion.registrar(tPromocion);
            }
            else{
                //Si la maestria no existe se la registra
                band =  daoTmaestrias.registrar(tMaestria);
            }
            //Compruebo que la maestria la registre en la bd
            if(band){
               tPromocion.setDescripcion(i+1);
               tPromocion.setMaestria(tMaestria);
               msg = daoTpromocion.registrar(tPromocion);
            }
            
            try {
                //daoTpromocion.existe(tPromocion);
                
            } catch (Exception ex) {
                Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
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
        promocion.setCupo(((ClsTablaMaestrias) event.getObject()).getCupo());
        promocion.setNCuotas(((ClsTablaMaestrias) event.getObject()).getCuotas());
        promocion.setDescripcion(((ClsTablaMaestrias) event.getObject()).getDescripcionP());
        
        if(theme== null){
            promocion.setIdUsuario(((ClsTablaMaestrias) event.getObject()).getIdUsuario());
            promocion.setUsuario(((ClsTablaMaestrias) event.getObject()).getNombresUsuarios());
        }else{
            promocion.setIdUsuario(theme.getId());
            promocion.setUsuario(theme.getName());
        }
        promocion.setFechaResolucion(((ClsTablaMaestrias) event.getObject()).getFechaResolucion());
        promocion.setFechaInicio(((ClsTablaMaestrias) event.getObject()).getFechaInicio());
        promocion.setFechaFin(((ClsTablaMaestrias) event.getObject()).getFechaFin());
        Maestria maestria = new Maestria();
        maestria.setId(((ClsTablaMaestrias) event.getObject()).getIdMaestria());
        promocion.setId(((ClsTablaMaestrias) event.getObject()).getIdPromocion());
        promocion.setMaestria(maestria);
        try {
            msg = daoTpromocion.update(promocion);
            cargarTablaMaestria();
        } catch (Exception ex) {
            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (msg) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
        
    }
    public void onRowCancel(RowEditEvent event) {
        //cargarTablaMaestria();
    }
    public void onDelete(ClsTablaMaestrias clsTblMaestrias){
        DaoTPromocion daoTpromocion = new DaoTPromocion();
        Promocion promocion = new Promocion();
        
        promocion.setId(clsTblMaestrias.getIdPromocion());
        
        try {
            msg = daoTpromocion.delete(promocion);
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
    
}
