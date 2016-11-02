/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.PagoDao;
import Dao.PromocionDao;
import Dao.RequisitosDao;
import Pojo.Precio;
import Pojo.Promocion;
import Pojo.RequisitosPromo;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author chiti
 */
@Named(value = "precioBean")
@ViewScoped
public class PrecioBean implements Serializable {

    private String idPromo;
    private String idMaestria;
    private Map<String, String> maestrias;
    private Map<String, String> promociones;
    private boolean btnMostrar = true;
    private MaestriaBean mBean;
    private List<SelectItem> items;
    private List<Promocion> lstPromocion;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private BigDecimal precioMatricula;
    private BigDecimal precioColegiatura;
    private BigDecimal precioAranceles;

    public BigDecimal getPrecioAranceles() {
        return precioAranceles;
    }

    public void setPrecioAranceles(BigDecimal precioAranceles) {
        this.precioAranceles = precioAranceles;
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

   
    
    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public String getIdMaestria() {
        return idMaestria;
    }

    public void setIdMaestria(String idMaestria) {
        this.idMaestria = idMaestria;
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

    public boolean isBtnMostrar() {
        return btnMostrar;
    }

    public void setBtnMostrar(boolean btnMostrar) {
        this.btnMostrar = btnMostrar;
    }

    public MaestriaBean getmBean() {
        return mBean;
    }

    public void setmBean(MaestriaBean mBean) {
        this.mBean = mBean;
    }

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }

    public List<Promocion> getLstPromocion() {
        return lstPromocion;
    }

    public void setLstPromocion(List<Promocion> lstPromocion) {
        this.lstPromocion = lstPromocion;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    /**
     * Creates a new instance of PrecioBean
     */
    public PrecioBean() {
    }

    @PostConstruct
    public void init() {
        try {
            maestrias = new HashMap<>();
            items = new ArrayList<>();
            lstPromocion = new ArrayList<>();
            mBean = new MaestriaBean();
            mBean.init();
            items = mBean.getListaMaestrias();
            PromocionDao daoTPromocion = new PromocionDao();

            for (SelectItem i : items) {
                maestrias.put(i.getLabel(), i.getValue().toString());
                lstPromocion = daoTPromocion.getPromocionesMaestrias(Integer.valueOf(i.getValue().toString()));
                Map<String, String> map = new HashMap<>();
                for (Promocion p : lstPromocion) {
                    map.put(p.getDescripcion().toString(), String.valueOf(p.getId()));
                }
                data.put(i.getValue().toString(), map);
            }
        } catch (Exception ex) {
        }
    }

    public void onMaestriaChange() throws Exception {
        try {
            promociones = new HashMap<>();
            if (idMaestria != null && !idMaestria.equals("")) {
                promociones = data.get(idMaestria);
            } else {
                promociones = new HashMap<>();
            }
            btnMostrar = true;
        } catch (Exception ex) {
            Logger.getLogger(PrecioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void guardarPrecio(){
    
        try {
            Precio precio = new Precio();
            PromocionDao promoD = new PromocionDao();
            PagoDao pagoD = new PagoDao();
           // precio.setPromocion(promoD.getPromocion(idPromo));
            precio.setTipoPrecio(pagoD.getTipoPrecio("1"));
            precio.setValor(precioMatricula);
            pagoD.insertarPrecio(precio);
            
            precio.setTipoPrecio(pagoD.getTipoPrecio("2"));
            precio.setValor(precioColegiatura);
            pagoD.insertarPrecio(precio);
            
            precio.setTipoPrecio(pagoD.getTipoPrecio("3"));
            precio.setValor(precioAranceles);
            pagoD.insertarPrecio(precio);
            
            FacesMessage message = new FacesMessage("Succesful", "Datos procesados correctamente");
            FacesContext.getCurrentInstance().addMessage("message", message);
        } catch (Exception ex) {
            Logger.getLogger(PrecioBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Succesful", "Ha ocurrido un error");
            FacesContext.getCurrentInstance().addMessage("message", message);
        }
    }

}
