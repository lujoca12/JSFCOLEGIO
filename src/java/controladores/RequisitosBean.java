/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.DaoTMaestrias;
import Dao.RequisitosDao;
import Pojo.Maestria;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import managedBean.MbVMaestrias;
import org.primefaces.model.DualListModel;
import javax.faces.convert.Converter;

/**
 *
 * @author chiti
 */
@Named(value = "requisitosBean")
@ViewScoped
public class RequisitosBean implements Serializable {

    private Requisitos requisito;
    private RequisitosPromo reqPromo;
    private int idMaestria;
    private String descripcion;
    private Boolean respaldo;
    private List<SelectItem> listaRequisitos;
    private List<Requisitos> lstReq;
    private List<String> lstSelReq;
    private List<String> lstReqPro;
    private Map<Integer, String> lstReqProNombre;
    private String formato;
    private String tipoArhivo;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTipoArhivo() {
        return tipoArhivo;
    }

    public void setTipoArhivo(String tipoArhivo) {
        this.tipoArhivo = tipoArhivo;
    }

    public Map<Integer, String> getLstReqProNombre() {
        return lstReqProNombre;
    }

    public void setLstReqProNombre(Map<Integer, String> lstReqProNombre) {
        this.lstReqProNombre = lstReqProNombre;
    }

    public List<String> getLstReqPro() {
        return lstReqPro;
    }

    public void setLstReqPro(List<String> lstReqPro) {
        this.lstReqPro = lstReqPro;
    }

    public RequisitosPromo getReqPromo() {
        return reqPromo;
    }

    public void setReqPromo(RequisitosPromo reqPromo) {
        this.reqPromo = reqPromo;
    }

    public int getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(int idMaestria) {
        this.idMaestria = idMaestria;
    }

    public void setLstReq(List<Requisitos> lstReq) {
        this.lstReq = lstReq;
    }

    public List<Requisitos> getLstReq() {
        return lstReq;
    }

    public List<String> getLstSelReq() {
        return lstSelReq;
    }

    public void setLstSelReq(List<String> lstSelReq) {
        this.lstSelReq = lstSelReq;
    }

    public List<SelectItem> getListaRequisitos() {
        return listaRequisitos;
    }

    public void setListaRequisitos(List<SelectItem> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Boolean respaldo) {
        this.respaldo = respaldo;
    }

    /**
     * Creates a new instance of Requisitos
     */
    public RequisitosBean() {
        requisito = new Requisitos();
        reqPromo = new RequisitosPromo();
    }

    public void registrar() {

        RequisitosDao Drequisitos = new RequisitosDao();
        try {

            requisito.setDescripcion(descripcion);
            //formato se usa para describir el tipo de archivo que se le pedira al estudiante en la solicitud de inscripcion
            formato = descripcion.replaceAll("\\s+", "");
            formato = removeCaractEspeciales(formato).toLowerCase();
            requisito.setFormato(formato);
            requisito.setTipoArchivo(tipoArhivo);
            requisito.setEstado('1');
            requisito.setRespaldo('1');

            if (true == Drequisitos.insertar(requisito)) {
                mensajesOk("Datos procesados correctamente.");
                vaciarCajas();
            }
        } catch (Exception e) {
            //Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, e);
            mensajesError("Error al procesar datos");
        }
    }

//    public void registrarReqPromo() {
//
//        RequisitosDao Drequisitos = new RequisitosDao();
//        try {
//
//            reqPromo.setEstado('1');
//            reqPromo.setRequisitos(requisito);
//
//            Drequisitos.insertar(requisito);
//            mensajesOk("Datos procesados correctamente.");
//            vaciarCajas();
//        } catch (Exception e) {
//            Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, e);
//            mensajesError("Error al procesar datos");
//        }
//    }
    private void vaciarCajas() {
        descripcion = "";
        respaldo = false;
    }

    public Requisitos getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisitos requisito) {
        this.requisito = requisito;
    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void init() {
        try {
            //se lista todos los requisitos disponibles
            listaRequisitos = new ArrayList<>();
            RequisitosDao daoRequisitos = new RequisitosDao();
            List<Requisitos> lstRequisitos = daoRequisitos.getTodosRequisitos();
            for (Requisitos r : lstRequisitos) {
                SelectItem item = new SelectItem(r.getId(), r.getDescripcion());
                listaRequisitos.add(item);
            }
            lstReq = new ArrayList<>();
            lstReq = lstRequisitos;
//            
        } catch (Exception ex) {
            Logger.getLogger(MaestriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getListaReqPro(String IdPromo, String IdMaestria) throws Exception {

        lstReqPro = new ArrayList<>();
        RequisitosDao daoRequisitos = new RequisitosDao();
        List<RequisitosPromo> lstRequisitos = daoRequisitos.getRequisitosPromocion(IdPromo, IdMaestria);
        for (RequisitosPromo rp : lstRequisitos) {
            lstReqPro.add(String.valueOf(rp.getRequisitos().getId()));
        }
        return lstReqPro;

    }

    public Map<Integer, String> getListaReqProNombre(int IdPromo, int IdMaestria) throws Exception {

        lstReqProNombre = new HashMap<>();
        RequisitosDao daoRequisitos = new RequisitosDao();
        List<RequisitosPromo> lstRequisitos = daoRequisitos.getRequisitosPromocion(String.valueOf(IdPromo), String.valueOf(IdMaestria));
        for (RequisitosPromo rp : lstRequisitos) {
            lstReqProNombre.put(rp.getRequisitos().getId(), rp.getRequisitos().getDescripcion());
        }

        return lstReqProNombre;
    }

    public String removeCaractEspeciales(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }//remove1

}
