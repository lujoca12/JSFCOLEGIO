/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTAsistencias;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Pojo.Asistencia;
import Pojo.Matricula;
import Pojo.Modulo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author server
 */
@Named(value = "mbVAsistencia")
@ViewScoped
public class MbVAsistencia implements Serializable {

    private Asistencia tAsistencia;

    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;

    private ClsNotas clsNotas;
    private List<ClsNotas> lstTblNotas;

    private String cedula = "";
    private String estudiante = "";
    
    private String msgRegistro = "";
    private int estado;
    
    
    private int idProm = 0;
    private int idModulo = 0;
    private boolean msg;

    public MbVAsistencia() {
        tAsistencia = new Asistencia();
        cargarCboModulos();
    }

    public ClsTablaModulosRegistrados getClsTblModulosReg() {
        return clsTblModulosReg;
    }

    public void setClsTblModulosReg(ClsTablaModulosRegistrados clsTblModulosReg) {
        this.clsTblModulosReg = clsTblModulosReg;
    }

    public List<ClsTablaModulosRegistrados> getLstCboModulos() {
        return lstCboModulos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public int getIdProm() {
        return idProm;
    }

    public void setIdProm(int idProm) {
        this.idProm = idProm;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public Asistencia gettAsistencia() {
        return tAsistencia;
    }

    public void settAsistencia(Asistencia tAsistencia) {
        this.tAsistencia = tAsistencia;
    }

    public ClsNotas getClsNotas() {
        return clsNotas;
    }

    public void setClsNotas(ClsNotas clsNotas) {
        this.clsNotas = clsNotas;
    }

    public List<ClsNotas> getLstTblNotas() {
        return lstTblNotas;
    }

    public String getMsgRegistro() {
        return msgRegistro;
    }

    public void setMsgRegistro(String msgRegistro) {
        this.msgRegistro = msgRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public void cargarCboModulos() {
        lstCboModulos = new ArrayList<>();
        try {
            lstCboModulos.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas();

            if (lstModulo != null) {
                if (lstModulo.size() > 0) {
                    for (Modulo modulo : lstModulo) {
                        lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                modulo.getPromocion().getMaestria().getDescripcion() + " (Dir.(a)" + modulo.getPromocion().getUsuario() + ")",
                                modulo.getPromocion().getId(),
                                modulo.getDescripcion() + " (" + modulo.getPromocion().getMaestria().getDescripcion() + ")",
                                modulo.getUsuario().getId(),
                                modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                modulo.getCreditos().toString(),
                                modulo.getId(),
                                modulo.getN_modulo(),
                                modulo.getFechaInicio(),
                                modulo.getFechaFin()));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarTablaRegAsistencia() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        
        try {
            lstTblNotas.clear();
            
            DaoTAsistencias daoTasistencia = new DaoTAsistencias();
            List<Asistencia> lstAsistencia = daoTasistencia.existe(this.idModulo);
            
            
            boolean asist = false;
            
            if(lstAsistencia.size() > 0){
                this.estado = 1;
                mensajesOk("Asistencia ya registrada");
                for (Asistencia asistencia : lstAsistencia) {
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        cont++;
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                        if(asistencia.getEstado().equals('1'))
                            asist = true;
                        else
                            asist = false;
                        
                        lstTblNotas.add(new ClsNotas(asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getId(),
                                estudiante,
                                asistencia.getMatricula().getId(),
                                asistencia.getMatricula().getNMatricula(),
                                asistencia.getMatricula().getFechaMatricula(),
                                asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getId(),
                                añoInicio + "-" + añoFin,
                                asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                cont,
                                "",
                                "",
                                null,
                                asist, 0));
                    }
            }else{
                DaoTMatricula daoTmatricula = new DaoTMatricula();
                List<Matricula> lstMatricula = daoTmatricula.getMatriculaRegNotas(this.idModulo);
                if (lstMatricula.size() > 0) {
                    this.estado = 0;
                    
                    for (Matricula matricula : lstMatricula) {
                        calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        cont++;
                        calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres();

                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(),
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres(),
                                matricula.getId(),
                                matricula.getNMatricula(),
                                matricula.getFechaMatricula(),
                                matricula.getSolicitudInscripcion().getPromocion().getId(),
                                añoInicio + "-" + añoFin,
                                matricula.getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                matricula.getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                matricula.getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                cont,
                                "",
                                "",
                                null,
                                true, 0));
                    }
                }
                
            }

            

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrar() {
        DaoTAsistencias daoTasistencia = new DaoTAsistencias();
        try {
            msg = daoTasistencia.registrar(lstTblNotas, this.idModulo);
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (msg) {
            mensajesOk("Datos procesados correctamente");
        } else {
            mensajesError("Error al procesar datos");
        }
        vaciarCajas();
    }

    private void vaciarCajas() {
        this.idModulo = 0;
        cargarCboModulos();
        lstTblNotas = new ArrayList<>();
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
