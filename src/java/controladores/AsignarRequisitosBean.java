/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.DaoTPromocion;
import Dao.PermisosPromoDao;
import Dao.PromocionDao;
import Pojo.Promocion;
import Pojo.Requisitos;
import Pojo.RequisitosPromo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Seeting
 */
@Named(value = "AsignarRequisitosBean")
@ViewScoped
public class AsignarRequisitosBean implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private String msg = "";
    private Map<String, String> maestrias;
    private Map<String, String> promociones;
    private Map<String, String> requisitos;
    private RequisitosBean reqB;
    private List<SelectItem> listaPromocion;
    private String idPromo;
    private String idMaestria;
    private List<String> reqSelec;
    private List<SelectItem> listaRequisitos;

    public List<String> getReqSelec() {
        return reqSelec;
    }

    public void setReqSelec(List<String> reqSelec) {
        this.reqSelec = reqSelec;
    }

    public List<SelectItem> getListaRequisitos() {
        return listaRequisitos;
    }

    public void setListaRequisitos(List<SelectItem> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }

    public Map<String, String> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Map<String, String> requisitos) {
        this.requisitos = requisitos;
    }
    private MaestriaBean mBean;

    public RequisitosBean getReqB() {
        return reqB;
    }

    public void setReqB(RequisitosBean reqB) {
        this.reqB = reqB;
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

    public Map<String, String> getMaestrias() {
        return maestrias;
    }

    public void setMaestrias(Map<String, String> maestrias) {
        this.maestrias = maestrias;
    }

    public Map<String, String> getPromociones() {
        return promociones;
    }

    public void setPromociones(Map<String, String> promociones) {
        this.promociones = promociones;
    }

    public MaestriaBean getmBean() {
        return mBean;
    }

    public void setmBean(MaestriaBean mBean) {
        this.mBean = mBean;
    }

    public List<SelectItem> getListaPromocion() {
        return listaPromocion;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public void setListaPromocion(List<SelectItem> listaPromocion) {
        this.listaPromocion = listaPromocion;
    }

    /**
     * Creates a new instance of PromocionBean
     */
    public AsignarRequisitosBean() {

    }

    @PostConstruct
    public void init() {
        try {
            maestrias = new HashMap<String, String>();
            listaPromocion = new ArrayList<>();

            mBean = new MaestriaBean();
            mBean.init();

            List<SelectItem> items = mBean.getListaMaestrias();

            for (SelectItem i : items) {
                maestrias.put(i.getLabel(), i.getValue().toString());
                PromocionDao daoTPromocion = new PromocionDao();
                List<Promocion> lstPromocion = daoTPromocion.getPromocionesMaestrias(Integer.valueOf(i.getValue().toString()));
                Map<String, String> map = new HashMap<String, String>();
                for (Promocion p : lstPromocion) {
                    map.put(p.getDescripcion().toString(), String.valueOf(p.getId()));
                }
                data.put(i.getValue().toString(), map);
            }

        } catch (Exception ex) {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onMaestriaChange() throws Exception {
        try{
        listaRequisitos = null;reqSelec = new ArrayList<>();
        promociones = new HashMap<>();
        if (idMaestria != null && !idMaestria.equals("")) {
            promociones = data.get(idMaestria);
        } else {
            promociones = new HashMap<>();
        }
        }catch(Exception ex)
        {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onPromoChange() throws Exception {
       try{ 
        reqSelec = new ArrayList<>();
        if (idPromo != null && !idPromo.equals("")) {

            reqB = new RequisitosBean();
            reqB.init();
            listaRequisitos = reqB.getListaRequisitos();
            reqSelec = reqB.getListaReqPro(idPromo, idMaestria);

        } else {
            listaRequisitos = null;
        }
       }catch(Exception ex)
        {
            Logger.getLogger(AsignarRequisitosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void registrarReqPromo() {

        try {
            List<String> lstReq = new ArrayList<>();
            for (SelectItem i : listaRequisitos) {
                lstReq.add(i.getValue().toString());
            }
            PermisosPromoDao dao = new PermisosPromoDao();
            msg = dao.registrar(lstReq, reqSelec, Integer.valueOf(idPromo));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            vaciarCajas();
        } catch (Exception e) {
            //Logger.getLogger(MbVMaestrias.class.getName()).log(Level.SEVERE, null, e);
            mensajesError("Error al procesar datos");
        }
    }

    private int existeReqPromo(int IdReq, int IdPromo) {
        int idRP = 0;
        PermisosPromoDao d = new PermisosPromoDao();
        List<RequisitosPromo> lstRp = d.getEstadoReqPromo(IdReq, IdPromo);
        if (!lstRp.isEmpty()) {
            idRP = lstRp.get(0).getId();
        }
        return idRP;
    }

    private void vaciarCajas() {

    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
