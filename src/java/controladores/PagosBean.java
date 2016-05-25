/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.PagoDao;
import Pojo.Pago;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "pagosBean")
@ViewScoped
public class PagosBean implements Serializable  {

    private String cedula;
    private PagoDao pDao = new PagoDao();
    private List<Pago> lstPagos = new ArrayList<>();
    private StreamedContent file;
    private Pago selectedPago;

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
}
