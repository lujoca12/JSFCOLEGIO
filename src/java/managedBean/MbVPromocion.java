/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.DaoTMaestrias;
import Dao.DaoTUsuario;
import Pojo.Maestria;
import Pojo.Promocion;
import Pojo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Usuario08
 */
@Named(value = "mbVPromocion")
@ViewScoped
public class MbVPromocion implements Serializable{

    private List<SelectItem> lstMaestria;
    private List<SelectItem> lstDocente;
    private Promocion tPromocion;
    private int idMaestria;
    private int idDocente;
    
    public MbVPromocion() {
        tPromocion = new Promocion();
    }

    public List<SelectItem> getLstMaestria() {
        this.lstMaestria = new ArrayList<SelectItem>();
        try {
            DaoTMaestrias daoTmaestria = new DaoTMaestrias();
            
            List<Maestria> lstPer = daoTmaestria.getTodasMaestrias();
            lstMaestria.clear();
            for(Maestria maestria: lstPer){
                SelectItem usuarioItem = new SelectItem(maestria.getId(),maestria.getDescripcion());
                this.lstMaestria.add(usuarioItem);
            }
        } catch (Exception ex) {
            
        }
        return lstMaestria;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
    
    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }
    
    

    public List<SelectItem> getLstDocente() {
        this.lstDocente = new ArrayList<SelectItem>();
        try {
            DaoTUsuario daoTusuario = new DaoTUsuario();
            
            List<Usuario> lstUsuario = daoTusuario.getDocentes();
            lstDocente.clear();
            for(Usuario user: lstUsuario){
                SelectItem usuarioItem = new SelectItem(user.getId(),user.getApellidos() +" "+user.getNombres());
                this.lstMaestria.add(usuarioItem);
            }
        } catch (Exception ex) {
            
        }
        return lstDocente;
    }

    public Promocion gettPromocion() {
        return tPromocion;
    }

    public void settPromocion(Promocion tPromocion) {
        this.tPromocion = tPromocion;
    }
    
    
    
    private void vaciarCajas(){
        tPromocion = new Promocion();
    }
    
}
