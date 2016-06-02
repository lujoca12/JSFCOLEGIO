/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsArchivos;
import Dao.MatriculaDao;
import Dao.PagoDao;
import Pojo.Archivos;
import Pojo.Matricula;
import Pojo.Pago;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "pagosBean")
@ViewScoped
public class PagosBean implements Serializable {

    private String cedula;
    private PagoDao pDao = new PagoDao();
    private List<Pago> lstPagos = new ArrayList<>();
    private StreamedContent file;
    private Pago selectedPago;
    private Pago pago;
    private List<Archivos> lstArchivos;
    private List<ClsArchivos> archivos;

    public List<ClsArchivos> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ClsArchivos> archivos) {
        this.archivos = archivos;
    }

    public List<Archivos> getLstArchivos() {
        return lstArchivos;
    }

    public void setLstArchivos(List<Archivos> lstArchivos) {
        this.lstArchivos = lstArchivos;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Pago getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(Pago selectedPago) {
        this.selectedPago = selectedPago;
    }

    public List<Pago> getLstPagos() {
        return lstPagos;
    }

    public void setLstPagos(List<Pago> lstPagos) {
        this.lstPagos = lstPagos;
    }

    /**
     * Creates a new instance of PagosBean
     */
    public PagosBean() {
    }

    @PostConstruct
    public void init() {
        try {
            pDao = new PagoDao();
            lstPagos = pDao.getPagosAprobar();

        } catch (Exception ex) {
        }
    }

    public void obtenerRequisitos() {

        try {
            archivos = new ArrayList<>();
            lstArchivos = new ArrayList<>();
            if (selectedPago != null) {
                pDao = new PagoDao();
                pago = pDao.getPago(selectedPago.getId());
                InputStream input = new FileInputStream(pago.getRutaComprobante());
                String extension = pago.getRutaComprobante().substring(pago.getRutaComprobante().lastIndexOf('.'));
                file = new DefaultStreamedContent(input, "application/pdf", "pago" + extension);
                archivos.add(new ClsArchivos("pago", file));

            }
        } catch (Exception ex) {
            Logger.getLogger(AsignarEntrevistaBean.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void guardarMatricula() {
        try {
            pDao = new PagoDao();
            selectedPago.setEstado('A');
            if(pDao.insertarPago(selectedPago)){
            FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                FacesMessage message = new FacesMessage("Erorr", "Error al guardarlos datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }
            lstPagos.remove(selectedPago);
            selectedPago = null;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void rechazarMatricula() {
        try {
            pDao = new PagoDao();
            selectedPago.setEstado('R');
            if(pDao.insertarPago(selectedPago)){
            FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                FacesMessage message = new FacesMessage("Erorr", "Error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }
            lstPagos.remove(selectedPago);
            selectedPago = null;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
