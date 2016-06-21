/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsProfesor;
import Clases.ClsTablaPermisos;
import Dao.DaoTMenu;
import Pojo.DetallePermiso;
import Pojo.Permiso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import javax.faces.application.FacesMessage;

/**
 *
 * @author server
 */
@Named(value = "mbVTablaPermisos")
@ViewScoped
public class MbVTablaPermisos implements Serializable{

    private List<Permiso> lstMenus;
    private List<DetallePermiso> lstMenusDetalle;
    private List<ClsTablaPermisos> lstTablaPermisos;
    private TreeNode root;
    private boolean msg = false;
    DetallePermiso tDetallePermiso;
    Permiso tPermiso;
    private ClsTablaPermisos clsTablaPermisos;
    private int btnOcultarMostrar;
    private boolean disable;
    private String permisoDescripcion;
    private boolean mostrarEliminados;
    
    public MbVTablaPermisos() {
        cargarTablaPermisos();
        this.disable = false;
        //btnOcultarMostrar = 0;
    }

    public List<ClsTablaPermisos> getLstTablaPermisos() {
        return lstTablaPermisos;
    }
    
    public TreeNode getRoot() {
        return root;
    }

    public ClsTablaPermisos getClsTablaPermisos() {
        return clsTablaPermisos;
    }

    public int getBtnOcultarMostrar() {
        return btnOcultarMostrar;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    
    
    public void setClsTablaPermisos(ClsTablaPermisos clsTablaPermisos) {
        this.clsTablaPermisos = clsTablaPermisos;
    }

    public String getPermisoDescripcion() {
        return permisoDescripcion;
    }

    public void setPermisoDescripcion(String permisoDescripcion) {
        this.permisoDescripcion = permisoDescripcion;
    }

    public boolean isMostrarEliminados() {
        return mostrarEliminados;
    }

    public void setMostrarEliminados(boolean mostrarEliminados) {
        this.mostrarEliminados = mostrarEliminados;
    }
    

    public void cargarTablaPermisos() {

        
        
        try {
            
            
            if (permisoDescripcion == null || permisoDescripcion.isEmpty()) {
                cargarMenu();
            } else {
                buscarPermisoDescripcion();
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void cargarMenu() {
        String contenedor = "";
        String ruta = "";
        DaoTMenu daoTmenu = new DaoTMenu();
        lstTablaPermisos = new ArrayList<>();
        lstTablaPermisos.clear();
        boolean bandera = false;
        try {
            lstMenus = daoTmenu.getTodosPermisos(mostrarEliminados);//("Files", 0, "Folder")
        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lstMenus != null) {
            //root = new DefaultTreeNode(new ClsTablaPermisos(0,"File","0","0",0,"Folder",0), null);
            for (Permiso p : lstMenus) {
                
                if(mostrarEliminados){
                    bandera = true;
                    if (p.getPadre() == 0) {
                        lstTablaPermisos.add(new ClsTablaPermisos(p.getId(), p.getDescripcion(), "", "", p.getOrden(), "Folder", p.getPadre()));
                        //node0 = new DefaultTreeNode(new ClsTablaPermisos(p.getId(),p.getDescripcion(),"","",p.getOrden(), "Folder",p.getPadre()), root);

                        for (Permiso p1 : lstMenus) {
                            if (p.getId() == p1.getPadre() && p1.getEstado().equals('0')) {
                                bandera = false;
                                contenedor = p1.getForm().substring(17, 33);
                                ruta = p1.getForm().substring(54, p1.getForm().length() - 2);
                                lstTablaPermisos.add(new ClsTablaPermisos(p1.getId(), p1.getDescripcion(), contenedor, ruta, p1.getOrden(), "Folder", p1.getPadre()));
                                //node00 = new DefaultTreeNode(new ClsTablaPermisos(p1.getId(),p1.getDescripcion(),contenedor,ruta,p1.getOrden(), "Folder",1), node0);
                            }
                        }
                        if(bandera){
                            if(p.getEstado().equals('1'))
                                lstTablaPermisos.remove(lstTablaPermisos.size()-1);
                        }
                    }
                }else{
                    if (p.getPadre() == 0) {
                        lstTablaPermisos.add(new ClsTablaPermisos(p.getId(), p.getDescripcion(), "", "", p.getOrden(), "Folder", p.getPadre()));
                        //node0 = new DefaultTreeNode(new ClsTablaPermisos(p.getId(),p.getDescripcion(),"","",p.getOrden(), "Folder",p.getPadre()), root);

                        for (Permiso p1 : lstMenus) {
                            if (p.getId() == p1.getPadre()) {
                                contenedor = p1.getForm().substring(17, 33);
                                ruta = p1.getForm().substring(54, p1.getForm().length() - 2);
                                lstTablaPermisos.add(new ClsTablaPermisos(p1.getId(), p1.getDescripcion(), contenedor, ruta, p1.getOrden(), "Folder", p1.getPadre()));
                                //node00 = new DefaultTreeNode(new ClsTablaPermisos(p1.getId(),p1.getDescripcion(),contenedor,ruta,p1.getOrden(), "Folder",1), node0);
                            }
                        }
                    }
                }
                
            }

        } else {
            lstTablaPermisos.clear();
        }
    }
    
    public void buscarPermisoDescripcion(){
        List<ClsTablaPermisos> lst = new ArrayList<>();
        if(lstTablaPermisos.size()<= 0){
            cargarMenu();
        }
            
            
            int id = 0;
            int cont = 0;
            for (int i = 0; i < lstTablaPermisos.size(); i++) {
                if (id <= 0) {
                    if (lstTablaPermisos.get(i).getDescripcion().contains(permisoDescripcion) && lstTablaPermisos.get(i).getPadre() == 0) {
                        id = lstTablaPermisos.get(i).getId();
                        lst.add(new ClsTablaPermisos(lstTablaPermisos.get(i).getId(), 
                                lstTablaPermisos.get(i).getDescripcion(), 
                                "", 
                                "", 
                                lstTablaPermisos.get(i).getOrden(), 
                                "Folder", 
                                lstTablaPermisos.get(i).getPadre()));
                    }
                } else {
                    if(id == lstTablaPermisos.get(i).getPadre()){
                        lst.add(new ClsTablaPermisos(lstTablaPermisos.get(i).getId(), 
                                lstTablaPermisos.get(i).getDescripcion(), 
                                lstTablaPermisos.get(i).getContenedor(), 
                                lstTablaPermisos.get(i).getRuta(), 
                                lstTablaPermisos.get(i).getOrden(), 
                                "Folder", 
                                lstTablaPermisos.get(i).getPadre()));
                    }
                }
            }
            lstTablaPermisos = lst;
        
        
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        String id =  String.valueOf(((ClsTablaPermisos) event.getObject()).getId());
        
        DaoTMenu daotMenu = new DaoTMenu();
        tPermiso = new Permiso();
        String ruta = "";
        ruta += "CargarPaginaURL('"+((ClsTablaPermisos) event.getObject()).getContenedor()+"','/JSFSISFIAC/faces/"+((ClsTablaPermisos) event.getObject()).getRuta()+"')";
        
        try {
            tPermiso.setId(((ClsTablaPermisos) event.getObject()).getId());
            tPermiso.setDescripcion(((ClsTablaPermisos) event.getObject()).getDescripcion());
            tPermiso.setForm(ruta);
            tPermiso.setPadre(((ClsTablaPermisos) event.getObject()).getPadre());
            tPermiso.setOrden(((ClsTablaPermisos) event.getObject()).getOrden());
            tPermiso.setEstado('1');
            msg = daotMenu.update(tPermiso);
            
        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(msg)
            mensajesOk("Datos actualizados correctamente");
        else
            mensajesError("Error al actualizar datos");
//        FacesMessage msg = new FacesMessage("Fila editada", id);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void actualizar(){
        
    }
     
    public void onRowCancel(RowEditEvent event) {
       // btnOcultarMostrar = 0;
//        FacesMessage msg = new FacesMessage("Edición Cancelada", ((ClsTablaPermisos) event.getObject()).getDescripcion());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowEditCel(RowEditEvent event) {
        //btnOcultarMostrar = 1;
//        FacesMessage msg = new FacesMessage("Pilas mijin", ((ClsTablaPermisos) event.getObject()).getDescripcion());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onDelete(ClsTablaPermisos clsTablaPermisos)
    {
        //lstTablaPermisos.remove(clsTablaPermisos);
        DaoTMenu daotMenu = new DaoTMenu();
        tPermiso = new Permiso();
        String ruta = "";
        ruta += "CargarPaginaURL('"+clsTablaPermisos.getContenedor()+"','/JSFSISFIAC/faces/"+clsTablaPermisos.getRuta()+"')";
        
        try {
            tPermiso.setId(clsTablaPermisos.getId());
            tPermiso.setDescripcion(clsTablaPermisos.getDescripcion());
            if(clsTablaPermisos.getPadre() != 0)
                tPermiso.setForm(ruta);
            else
                tPermiso.setForm("");
            
            tPermiso.setPadre(clsTablaPermisos.getPadre());
            tPermiso.setOrden(clsTablaPermisos.getOrden());
            tPermiso.setEstado('0');
            msg = daotMenu.update(tPermiso);
            
            if(clsTablaPermisos.getPadre() == 0){
                for (int i = 0; i < lstTablaPermisos.size(); i++) {
                    if(lstTablaPermisos.get(i).getPadre() == clsTablaPermisos.getId()){
                        ruta = "";
                        ruta += "CargarPaginaURL('"+lstTablaPermisos.get(i).getContenedor()+"','/JSFSISFIAC/faces/"+lstTablaPermisos.get(i).getRuta()+"')";
                        tPermiso.setId(lstTablaPermisos.get(i).getId());
                        tPermiso.setDescripcion(lstTablaPermisos.get(i).getDescripcion());
                        tPermiso.setForm(ruta);
                        tPermiso.setPadre(lstTablaPermisos.get(i).getPadre());
                        tPermiso.setOrden(lstTablaPermisos.get(i).getOrden());
                        tPermiso.setEstado('0');
                        //lstTablaPermisos.remove(i);
                        msg = daotMenu.update(tPermiso);
                    }
                }
            }
            permisoDescripcion = null;
            cargarTablaPermisos();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(msg)
            mensajesOk("Datos eliminados correctamente");
        else
            mensajesError("Error al eliminar datos");
        
    }
    
    public void onRecuperar(ClsTablaPermisos clsTablaPermisos){
        DaoTMenu daotMenu = new DaoTMenu();
        tPermiso = new Permiso();
        String ruta = "";
        ruta += "CargarPaginaURL('"+clsTablaPermisos.getContenedor()+"','/JSFSISFIAC/faces/"+clsTablaPermisos.getRuta()+"')";
        
        try {
            tPermiso.setId(clsTablaPermisos.getId());
            tPermiso.setDescripcion(clsTablaPermisos.getDescripcion());
            if(clsTablaPermisos.getPadre() != 0)
                tPermiso.setForm(ruta);
            else
                tPermiso.setForm("");
            
            tPermiso.setPadre(clsTablaPermisos.getPadre());
            tPermiso.setOrden(clsTablaPermisos.getOrden());
            tPermiso.setEstado('1');
            msg = daotMenu.update(tPermiso);
            
            if(clsTablaPermisos.getPadre() == 0){
                for (int i = 0; i < lstTablaPermisos.size(); i++) {
                    if(lstTablaPermisos.get(i).getPadre() == clsTablaPermisos.getId()){
                        ruta = "";
                        ruta += "CargarPaginaURL('"+lstTablaPermisos.get(i).getContenedor()+"','/JSFSISFIAC/faces/"+lstTablaPermisos.get(i).getRuta()+"')";
                        tPermiso.setId(lstTablaPermisos.get(i).getId());
                        tPermiso.setDescripcion(lstTablaPermisos.get(i).getDescripcion());
                        tPermiso.setForm(ruta);
                        tPermiso.setPadre(lstTablaPermisos.get(i).getPadre());
                        tPermiso.setOrden(lstTablaPermisos.get(i).getOrden());
                        tPermiso.setEstado('1');
                        //lstTablaPermisos.remove(i);
                        msg = daotMenu.update(tPermiso);
                    }
                }
            }
            permisoDescripcion = null;
            cargarTablaPermisos();
            
        } catch (Exception ex) {
            Logger.getLogger(MbVTablaPermisos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(msg)
            mensajesOk("Permiso recuperado correctamente");
        else
            mensajesError("Error al recuperar permiso");
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
