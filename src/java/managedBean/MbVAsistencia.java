/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsFechaHoras;
import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Dao.DaoTAsistencias;
import Dao.DaoTHorarioModulo;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Pojo.Asistencia;
import Pojo.HorarioModulo;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Usuario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

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
    private int habilitar;
    
    
    private int idProm = 0;
    private int idModulo = 0;
    private Date fecha;
    private boolean msg;
    private BigDecimal horasAsistidas;
    private List<ClsFechaHoras> lstCboFecha;
    private ClsFechaHoras clsFechaHora;
    private SelectItem seleccion;

    public MbVAsistencia() {
        tAsistencia = new Asistencia();
        cargarCboModulos();
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fecha = new Date();
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public BigDecimal getHorasAsistidas() {
        return horasAsistidas;
    }

    public void setHorasAsistidas(BigDecimal horasAsistidas) {
        this.horasAsistidas = horasAsistidas;
    }

    public List<ClsFechaHoras> getLstCboFecha() {
        return lstCboFecha;
    }

    public void setLstCboFecha(List<ClsFechaHoras> lstCboFecha) {
        this.lstCboFecha = lstCboFecha;
    }

    public ClsFechaHoras getClsFechaHora() {
        return clsFechaHora;
    }

    public void setClsFechaHora(ClsFechaHoras clsFechaHora) {
        this.clsFechaHora = clsFechaHora;
    }
    
    public SelectItem getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(SelectItem seleccion) {
        this.seleccion = seleccion;
    }

    public int getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(int habilitar) {
        this.habilitar = habilitar;
    }
    
    
    public void cargarCboModulos() {
        lstCboModulos = new ArrayList<>();
        try {
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            lstCboModulos.clear();
            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulo = daoTmodulo.getCboModulosAsistencias(usuario.getId());

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
                                modulo.getModulo(),
                                modulo.getFechaInicio() == null ? null:modulo.getFechaInicio(),
                                modulo.getFechaFin() == null ? null:modulo.getFechaFin(),
                                modulo.getFechaInicioExamen() == null ? null:modulo.getFechaInicioExamen(),
                                modulo.getFechaFinExamen() == null ? null:modulo.getFechaFinExamen(),
                                modulo.getTotalHorasModulo() == null ? null:modulo.getTotalHorasModulo().toString(),
                                modulo.getPromocion().getFechaInicio(),
                                modulo.getPromocion().getFechaFin()
                                ));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarCboFechas() {
        lstCboFecha = new ArrayList<>();
        this.estado = 0;
        try {
            lstCboFecha.clear();
            DaoTHorarioModulo daoHorario = new DaoTHorarioModulo();
            List<HorarioModulo> lstHorario = daoHorario.getFechaHorasModulos(idModulo);

            if (lstHorario != null) {
                if (lstHorario.size() > 0) {
                    for (HorarioModulo horario : lstHorario) {
                        lstCboFecha.add(new ClsFechaHoras(horario.getId(), horario.getHora(),horario.getFecha()));
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
        this.estado = 0;
        
        try {
            if (this.idModulo > 0 && this.clsFechaHora != null) {

                lstTblNotas.clear();

                DaoTAsistencias daoTasistencia = new DaoTAsistencias();

                List<Asistencia> lstAsistencia = daoTasistencia.existe(this.idModulo, this.clsFechaHora.getFecha());

                boolean asist = false;

                if (lstAsistencia.size() > 0) {
                    this.estado = 1;
                    this.habilitar = 1;
                    mensajesOk("Asistencia ya registrada");
                    for (Asistencia asistencia : lstAsistencia) {
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        cont++;
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                        if (asistencia.getEstado().equals('1')) {
                            asist = true;
                        } else {
                            asist = false;
                        }

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
                                asistencia.getObservacion(),
                                this.clsFechaHora.getFecha(),
                                asist, 0, "", "",this.clsFechaHora.getHoras().toString(),
                                0.0));
                    }
                } else {
                    DaoTMatricula daoTmatricula = new DaoTMatricula();
                    List<Matricula> lstMatricula = daoTmatricula.getMatriculaRegNotas(this.idModulo);
                    if (lstMatricula.size() > 0) {
                        this.estado = 1;
                        this.habilitar = 0;
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
                                    this.clsFechaHora.getFecha(),
                                    true, 0, "", "",this.clsFechaHora.getHoras().toString(),
                                    0.0));
                        }
                    }

                }
            }
            

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaEdiAsistencia() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        this.estado = 0;
        this.habilitar = 1;
        
        try {
            lstTblNotas.clear();
            if (this.idModulo > 0 && this.clsFechaHora != null) {
                DaoTAsistencias daoTasistencia = new DaoTAsistencias();
                List<Asistencia> lstAsistencia = daoTasistencia.existe(this.idModulo, this.clsFechaHora.getFecha());

                boolean asist = false;

                if (lstAsistencia.size() > 0) {
                    this.estado = 1;
                    this.habilitar = 0;

                    for (Asistencia asistencia : lstAsistencia) {
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);
                        cont++;
                        calendar.setTime(asistencia.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + asistencia.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                        if (asistencia.getEstado().equals('1')) {
                            asist = true;
                        } else {
                            asist = false;
                        }

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
                                asistencia.getObservacion(),
                                this.clsFechaHora.getFecha(),
                                asist,
                                asistencia.getId(), "", "", this.clsFechaHora.getHoras().toString(),
                                0.0));
                    }
                } else {
                    this.estado = 0;
                    mensajesOk("No hay Asistencia registrada con esta fecha");
                    vaciarCajas();
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrar() {
        DaoTAsistencias daoTasistencia = new DaoTAsistencias();
        try {
            msg = daoTasistencia.registrar(lstTblNotas, this.idModulo, this.clsFechaHora.getFecha());
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
    public void onRowEdit(RowEditEvent event) {
        DaoTAsistencias daoTasistencia = new DaoTAsistencias();
        tAsistencia = new Asistencia();
        
                
        tAsistencia.setId(((ClsNotas) event.getObject()).getIdNota());
        if(((ClsNotas) event.getObject()).getEstado())
            tAsistencia.setEstado('1');
        else
            tAsistencia.setEstado('0');
        
        tAsistencia.setFecha(((ClsNotas) event.getObject()).getFecha());
        Matricula matricula = new Matricula();
        matricula.setId(((ClsNotas) event.getObject()).getIdMatricula());
        tAsistencia.setMatricula(matricula);
        Modulo modulo = new Modulo();
        modulo.setId(this.idModulo);
        tAsistencia.setModulo(modulo);
        tAsistencia.setObservacion(((ClsNotas) event.getObject()).getObservacion());
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        tAsistencia.setUsuario(usuario.getApellidos()+" "+usuario.getNombres());
        
        try {
            msg = daoTasistencia.update(tAsistencia);
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (msg) {
            mensajesOk("Datos actualizados correctamente");
        } else {
            mensajesError("Error al actualizar datos");
        }
        
        //vaciarCajas();
        cargarTablaEdiAsistencia();
    }
    
    public void onRowCancel(RowEditEvent event) {

    }
    
    public void onDelete() {
        DaoTAsistencias daoTasistencia = new DaoTAsistencias();
        try {
            msg = daoTasistencia.delete(lstTblNotas, this.idModulo);
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (msg) {
            mensajesOk("Datos Eliminados correctamente");
        } else {
            mensajesError("Error al eliminar datos");
        }
        vaciarCajas();
    }

    private void vaciarCajas() {
        this.idModulo = 0;
        cargarCboFechas();
        this.estado = 0;
        
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
