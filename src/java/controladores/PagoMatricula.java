/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Dao.InscripcionDao;
import Pojo.SolicitudInscripcion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author chiti
 */
@Named(value = "pagoMatricula")
@ViewScoped
public class PagoMatricula implements Serializable {

    /**
     * Creates a new instance of PagoMatricula
     */
    private String cedula;
    private List<SolicitudInscripcion> lstSolicitudes;
    private String idComprobante;
    private BigDecimal valor;
    private int idTipoPago = 1;
    private String estudiante;

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<SolicitudInscripcion> getLstSolicitudes() {
        return lstSolicitudes;
    }

    public void setLstSolicitudes(List<SolicitudInscripcion> lstSolicitudes) {
        this.lstSolicitudes = lstSolicitudes;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public PagoMatricula() {
    }

    public void consultarSolicitudes() throws Exception {
        if (this.cedula.length() == 10) {
            this.cedula = this.cedula.replace("-", "");
            cargarSolicitudes();

        } else {
            this.estudiante = "";
        }
    }

    private void cargarSolicitudes() throws Exception {
        InscripcionDao iDao = new InscripcionDao();
        lstSolicitudes = iDao.getInscripcionesPorEstudiante(cedula);
        estudiante = lstSolicitudes.get(0).getEstudiante().getNombres() +" " +lstSolicitudes.get(0).getEstudiante().getApellidos();
    }
}
