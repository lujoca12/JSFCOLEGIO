/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Clases.ClsNotas;
import Clases.ClsTablaModulosRegistrados;
import Clases.ClsTblNotas;
import Clases.ClsTblPagos;
import Dao.DaoTAsistencias;
import Dao.DaoTCurso;
import Dao.DaoTEstudiante;
import Dao.DaoTHorarioModulo;
import Dao.DaoTMatricula;
import Dao.DaoTModulo;
import Dao.DaoTNotas;
import Dao.DaoTPonderaciones;
import Dao.InscripcionDao;
import Dao.PagosDao;
import Pojo.Asistencia;
import Pojo.Curso;
import Pojo.Estudiante;
import Pojo.HorarioModulo;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Notas;
import Pojo.Pago;
import Pojo.PonderacionFecha;
import Pojo.Ponderaciones;
import Pojo.SolicitudInscripcion;
import Pojo.TipoPago;
import Pojo.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author server
 */
@Named(value = "mbVNotas")
@ViewScoped
public class MbVNotas implements Serializable {

    private Notas tNotas;

    private ClsNotas clsNotas;
    private List<ClsNotas> lstTblNotas;
    private List<Pago> lstPagos = new ArrayList<>();
    private ClsTblNotas clsTblNotas;
    private ClsTblPagos clsTblPagos;
    private List<ClsTblNotas> lstTblNotasReg;
    private List<ClsTblPagos> lstTblPagosReg;

    private ClsTablaModulosRegistrados clsTblModulosReg;
    private List<ClsTablaModulosRegistrados> lstCboModulos;

    private String cedula = "";
    private String estudiante = "";
    private String maestria;
    private int idProm = 0;
    private int idModulo = 0;
    private int tipo_user;
    private Character estado;
    private String docente;
    private boolean msg;
    private Pago pago;
    private UploadedFile file;
    private ClsNotas selectedNota;
    private String idComprobante;
    private BigDecimal valor;
    private int idTipoPago = 1;
    private List<SolicitudInscripcion> lstSolicitudes;
    private SolicitudInscripcion selectedPago;
    private List<SelectItem> cboParciales;
    private PonderacionFecha pondFecha;
    private List<SelectItem> cboCurso;
    private Curso tCurso;

    public List<SelectItem> getCboCurso() {
        return cboCurso;
    }

    public void setCboCurso(List<SelectItem> cboCurso) {
        this.cboCurso = cboCurso;
    }

    
    public List<SelectItem> getCboParciales() {
        return cboParciales;
    }

    public void setCboParciales(List<SelectItem> cboParciales) {
        this.cboParciales = cboParciales;
    }

    public PonderacionFecha getPondFecha() {
        return pondFecha;
    }

    public void setPondFecha(PonderacionFecha pondFecha) {
        this.pondFecha = pondFecha;
    }

    public SolicitudInscripcion getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(SolicitudInscripcion selectedPago) {
        this.selectedPago = selectedPago;
    }
    
    public List<SolicitudInscripcion> getLstSolicitudes() {
        return lstSolicitudes;
    }

    public void setLstSolicitudes(List<SolicitudInscripcion> lstSolicitudes) {
        this.lstSolicitudes = lstSolicitudes;
    }
    

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
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

    public ClsNotas getSelectedNota() {
        return selectedNota;
    }

    public void setSelectedNota(ClsNotas selectedNota) {
        this.selectedNota = selectedNota;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public List<Pago> getLstPagos() {
        return lstPagos;
    }

    public void setLstPagos(List<Pago> lstPagos) {
        this.lstPagos = lstPagos;
    }

    public ClsTblPagos getClsTblPagos() {
        return clsTblPagos;
    }

    public void setClsTblPagos(ClsTblPagos clsTblPagos) {
        this.clsTblPagos = clsTblPagos;
    }

    public List<ClsTblPagos> getLstTblPagosReg() {
        return lstTblPagosReg;
    }

    public void setLstTblPagosReg(List<ClsTblPagos> lstTblPagosReg) {
        this.lstTblPagosReg = lstTblPagosReg;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    

    public Notas gettNotas() {
        return tNotas;
    }

    public void settNotas(Notas tNotas) {
        this.tNotas = tNotas;
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

    public ClsTblNotas getClsTblNotas() {
        return clsTblNotas;
    }

    public void setClsTblNotas(ClsTblNotas clsTblNotas) {
        this.clsTblNotas = clsTblNotas;
    }

    public List<ClsTblNotas> getLstTblNotasReg() {
        return lstTblNotasReg;
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

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getMaestria() {
        return maestria;
    }

    public void setMaestria(String maestria) {
        this.maestria = maestria;
    }

    public int getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(int tipo_user) {
        this.tipo_user = tipo_user;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public Curso gettCurso() {
        return tCurso;
    }

    public void settCurso(Curso tCurso) {
        this.tCurso = tCurso;
    }
    
    public MbVNotas() {
        tNotas = new Notas();
        pondFecha = new PonderacionFecha();
        tCurso = new Curso();
        //cargarCboModulos();
        cargarCboParciales();
        llenarCboCurso();
    }
    public void llenarCboCurso() {
        try {
            lstCboModulos = new ArrayList<>();
            if(lstCboModulos.size() > 0)
                lstCboModulos.clear();

            this.lstCboModulos.add(new ClsTablaModulosRegistrados(-1, "(Ninguna)", -1, "(Ninguna)", -1, "(Ninguna)", "(Ninguna)", -1, "(Ninguna)", null, null, null, null, "", null, null, 0,'1'));
            
            cboCurso = new ArrayList<>();
            DaoTCurso daoCurso = new DaoTCurso();
            List<Curso> cursos = daoCurso.getCursosModulos(true);
            for (Curso m : cursos) {
                SelectItem item = new SelectItem(m.getId(), m.getDescripcion()+" "+m.getParalelo());
                cboCurso.add(item);
            }
        } catch (Exception ex) {
            
        }

    }
    public void cargarCboParciales() {
        try {
            
            cboParciales = new ArrayList<>();
            DaoTPonderaciones daoPonderacion = new DaoTPonderaciones();
            DaoTPonderaciones daoTponderaciones = new DaoTPonderaciones();
            List<Ponderaciones> lstPer = daoTponderaciones.getParciales();
            List<PonderacionFecha> ponderacion = daoPonderacion.getPonderacionFecha(false);
            SelectItem item = null;
            for (PonderacionFecha s : ponderacion) {
                for (int i = 0; i < lstPer.size(); i++) {
                    if(lstPer.get(i).getId() == s.getPonderaciones().getClave() && s.getPonderaciones().getClave() > 0){
                        item = new SelectItem(s.getId(), s.getPonderaciones().getDescripcion()+"->"+lstPer.get(i).getDescripcion());
                        cboParciales.add(item);
                    }
                        
                    
                }
                if(s.getPonderaciones().getClave() == 0){
                        item = new SelectItem(s.getId(), s.getPonderaciones().getDescripcion());
                        cboParciales.add(item);
                    }
                
            }
            
        } catch (Exception ex) {
            
        }
    }
    private void cargarTblMatriculaPromocion() {
        this.idProm = 0;
        lstTblNotas = new ArrayList<>();
        try {
            lstTblNotas.clear();
            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = daoTmatricula.getMatriculaMaestria(this.cedula);
            Calendar calendar = Calendar.getInstance();
            int añoInicio = 0;
            int añoFin = 0;
            int cont = 0;
            Modulo mod = null;
            Object[] modulo = null;
            if (lstMatricula.size() > 0) {
                for (Matricula matricula : lstMatricula) {
                    modulo = matricula.getSolicitudInscripcion().getCurso().getModulos().toArray();
                    mod = (Modulo) modulo[0];
                    calendar.setTime(mod.getPromocion().getFechaInicio());
                    añoInicio = calendar.get(Calendar.YEAR);
                    cont++;
                    calendar.setTime(mod.getPromocion().getFechaFin());
                    añoFin = calendar.get(Calendar.YEAR);
                    estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres();

                    lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(),//id Estudiante
                            matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres(),//Nombres del estudiante
                            matricula.getId(),//id matricula
                            matricula.getNMatricula(),//n_matricula
                            matricula.getFechaMatricula(),//fecha matricula
                            //matricula.getSolicitudInscripcion().getPromocion().getId(),
                            mod.getPromocion().getId(),//id promocion
                            añoInicio + "-" + añoFin,
                            null,
                            0,//id maestria mod.getMaterias().getId()
                            "",//mod.getMaterias().getDescripcion()
                            //matricula.getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                            //matricula.getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                            //matricula.getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                            cont,
                            "",
                            "",
                            null,
                            true, 0, "", "", null, 0.0, ' ',
                            matricula.getSolicitudInscripcion().getCurso().getDescripcion()+" "+matricula.getSolicitudInscripcion().getCurso().getParalelo()));
                }
//                for (int i = 0; i < lstTblNotas.size(); i++) {
//                    for (int j = 0; j < modulo.length; j++) {
//                        if(lstTblNotas.get(i).getid)
//                        mod = (Modulo) modulo[j];
//                    }
//                }
            } else {
                this.estudiante = "";
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarTablaNotas(int idMatricula, int idPromocion) {
        lstTblNotasReg = new ArrayList<>();
        try {
            lstTblNotasReg.clear();
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = daoTnotas.getTodasNotas(idMatricula);

            DaoTModulo daoTmodulo = new DaoTModulo();
            List<Modulo> lstModulos = daoTmodulo.getTblModulosNotas(idPromocion);

            //Validacion para saber si hay notas registradas
//            if (lstNotas.size() > 0) {
//                for (Notas nota : lstNotas) {
//
//                    lstTblNotasReg.add(new ClsTblNotas(nota.getModulo().getId(),
//                            nota.getModulo().getDescripcion(),
//                            nota.getModulo().getCreditos(),
//                            nota.getId(),
//                            nota.getNota().doubleValue(),
//                            nota.getObservacion(),
//                            nota.getNotaTexto()));
//                }
//            } else {
            boolean bandera = true;
            if (lstModulos.size() > 0) {
                for (Modulo modulos : lstModulos) {

                    if (lstNotas.size() > 0) {
                        for (Notas nota : lstNotas) {
                            if (modulos.getId() == nota.getModulo().getId()) {
                                bandera = false;
                                lstTblNotasReg.add(new ClsTblNotas(modulos.getId(),
                                        modulos.getMaterias().getDescripcion(),
                                        modulos.getCreditos().toString(),
                                        nota.getId(),
                                        nota.getNota().doubleValue(),
                                        nota.getObservacion(),
                                        nota.getNotaTexto()));
                            }

                        }
                    }
                    if (bandera) {
                        lstTblNotasReg.add(new ClsTblNotas(modulos.getId(),
                                modulos.getMaterias().getDescripcion(),
                                modulos.getCreditos().toString(),
                                0,
                                0.0,
                                "",
                                ""));
                    }
                    bandera = true;
                }
            }

//            }
        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCboModulos() {
        lstCboModulos = new ArrayList<>();
        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            lstCboModulos.clear();

            this.lstCboModulos.add(new ClsTablaModulosRegistrados(-1, "(Escoja una Materia)", -1, "(Escoja una Materia)", -1, "(Escoja una Materia)", "(Escoja una Materia)", -1, "(Escoja una Materia)", null, null, null, null, "", null, null, 0,'1'));
            if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
                DaoTModulo daoTmodulo = new DaoTModulo();
                List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas(usuario.getId(),tCurso.getId());
                tipo_user = 1;
                if (lstModulo != null) {
                    if (lstModulo.size() > 0) {
                        for (Modulo modulo : lstModulo) {
                            lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                    modulo.getMaterias().getDescripcion() + " -->" + modulo.getCurso().getDescripcion() + "",
                                    modulo.getPromocion().getId(),
                                    modulo.getMaterias().getDescripcion() + " (" + modulo.getPromocion().getMaestria().getDescripcion() + ")",
                                    modulo.getUsuario().getId(),
                                    modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                    modulo.getCreditos().toString(),
                                    modulo.getId(),
                                    modulo.getModulo(),
                                    modulo.getFechaInicio() == null ? null : modulo.getFechaInicio(),
                                    modulo.getFechaFin() == null ? null : modulo.getFechaFin(),
                                    modulo.getFechaInicioExamen() == null ? null : modulo.getFechaInicioExamen(),
                                    modulo.getFechaFinExamen() == null ? null : modulo.getFechaFinExamen(),
                                    modulo.getTotalHorasModulo() == null ? null : modulo.getTotalHorasModulo().toString(),
                                    modulo.getPromocion().getFechaInicio(),
                                    modulo.getPromocion().getFechaFin(),
                                    modulo.getPromocion().getDescripcion(),
                                    modulo.getEstado()
                            ));
                        }
                    }
                }
            } else {
                DaoTModulo daoTmodulo = new DaoTModulo();
                List<Modulo> lstModulo = daoTmodulo.getCboModulosNotas(0,tCurso.getId());
                tipo_user = 0;
                if (lstModulo != null) {
                    if (lstModulo.size() > 0) {
                        for (Modulo modulo : lstModulo) {
                            lstCboModulos.add(new ClsTablaModulosRegistrados(modulo.getPromocion().getMaestria().getId(),
                                    modulo.getMaterias().getDescripcion() + " -->" + modulo.getCurso().getDescripcion() + "",
                                    modulo.getPromocion().getId(),
                                    modulo.getMaterias().getDescripcion() + " (" + modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres() + ")",
                                    modulo.getUsuario().getId(),
                                    modulo.getUsuario().getApellidos() + " " + modulo.getUsuario().getNombres(),
                                    modulo.getCreditos().toString(),
                                    modulo.getId(),
                                    modulo.getModulo(),
                                    modulo.getFechaInicio() == null ? null : modulo.getFechaInicio(),
                                    modulo.getFechaFin() == null ? null : modulo.getFechaFin(),
                                    modulo.getFechaInicioExamen() == null ? null : modulo.getFechaInicioExamen(),
                                    modulo.getFechaFinExamen() == null ? null : modulo.getFechaFinExamen(),
                                    modulo.getTotalHorasModulo() == null ? null : modulo.getTotalHorasModulo().toString(),
                                    modulo.getPromocion().getFechaInicio(),
                                    modulo.getPromocion().getFechaFin(),
                                    modulo.getPromocion().getDescripcion(),
                                    modulo.getEstado()
                            ));
                        }
                    }
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarTablaRegNotas() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        boolean bandera = true;
        maestria = "";
        if (this.clsTblModulosReg != null) {
            for (int i = 0; i < lstCboModulos.size(); i++) {
                if (lstCboModulos.get(i).getIdModulo() == this.clsTblModulosReg.getIdModulo()) {
                    maestria = lstCboModulos.get(i).getMaestria();
                    i = lstCboModulos.size();
                }
            }
        }

        try {
            lstTblNotas.clear();
            estado = ' ';
            tipo_user = 0;
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = null;
            if (this.clsTblModulosReg != null) {
                lstNotas = daoTnotas.existe(this.clsTblModulosReg.getIdModulo(), "0", pondFecha.getId());
            } else {
                lstNotas = daoTnotas.existe(0, "0", 0);
            }

            DaoTMatricula daoTmatricula = new DaoTMatricula();
            List<Matricula> lstMatricula = null;

            if (this.clsTblModulosReg != null) {
                lstMatricula = daoTmatricula.getMatriculaRegNotas(this.clsTblModulosReg.getIdModulo());
            } else {
                lstMatricula = daoTmatricula.getMatriculaRegNotas(0);
            }

            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
                tipo_user = 1;

            } else {
                tipo_user = 0;
            }

            if (lstMatricula.size() > 0) {
                for (Matricula matricula : lstMatricula) {

                    if (lstNotas.size() > 0) {
                        for (Notas notas : lstNotas) {

                            if (matricula.getId() == notas.getMatricula().getId()) {

                                estado = notas.getEstado();
                                docente = notas.getModulo().getUsuario().getApellidos() + " " + notas.getModulo().getUsuario().getNombres();
                                bandera = false;
                                //calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                                añoInicio = calendar.get(Calendar.YEAR);

                                //calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                                añoFin = calendar.get(Calendar.YEAR);

                                estudiante = notas.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + notas.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                                cont++;
                                lstTblNotas.add(new ClsNotas(notas.getMatricula().getSolicitudInscripcion().getEstudiante().getId(),
                                        estudiante,
                                        notas.getMatricula().getId(),
                                        notas.getMatricula().getNMatricula(),
                                        notas.getMatricula().getFechaMatricula(),
                                        //notas.getMatricula().getSolicitudInscripcion().getPromocion().getId(),
                                        0,
                                        añoInicio + "-" + añoFin,
                                        null,
                                        0,
                                        "",
                                        //notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                        //notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                        //notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                        cont,
                                        notas.getNota().toString(),
                                        notas.getObservacion(),
                                        null,
                                        true,
                                        notas.getId(),
                                        notas.getUsuario(),
                                        notas.getResponsable(),
                                        null,
                                        0.0, ' ',""));
                            }
                        }
                    }

                    if (bandera) {
//                        calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaInicio());
                        añoInicio = calendar.get(Calendar.YEAR);

  //                      calendar.setTime(matricula.getSolicitudInscripcion().getPromocion().getFechaFin());
                        añoFin = calendar.get(Calendar.YEAR);
                        estudiante = matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres();
                        cont++;
                        lstTblNotas.add(new ClsNotas(matricula.getSolicitudInscripcion().getEstudiante().getId(),
                                matricula.getSolicitudInscripcion().getEstudiante().getApellidos() + " " + matricula.getSolicitudInscripcion().getEstudiante().getNombres(),
                                matricula.getId(),
                                matricula.getNMatricula(),
                                matricula.getFechaMatricula(),
                               // matricula.getSolicitudInscripcion().getPromocion().getId(),
                                0,
                                añoInicio + "-" + añoFin,
                                null,
                                0,
                                "",
                                //matricula.getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                                //matricula.getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                                //matricula.getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                                cont,
                                "0",
                                "",
                                null,
                                true, 0, "", "", null, 0.0, ' ',""));

                    }

                    bandera = true;
                }
            }
            perdidosxAsistencia(lstTblNotas);
            if (estado != null) {
                if (estado.equals('A')) {
                    mensajesOk("Notas ya Registradas");
                }
            }

            if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
                tipo_user = 1;
                if (estado != null) {
                    if (estado.equals('G')) {
                        mensajesOk("Notas ya Registradas");
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void perdidosxAsistencia(List<ClsNotas> lstTblNotases) throws Exception {
        DaoTAsistencias daoTasistencia = new DaoTAsistencias();
        DaoTHorarioModulo daoHorario = new DaoTHorarioModulo();
        List<Asistencia> lstAsist = null;
        List<HorarioModulo> lstHorario = null;
        Object[] obj = null;
        Object objHorario = null;
        Double sumaAsistencia = null;
        Double sumaHorario = null;
        BigDecimal porcentaje = null;
        if (this.clsTblModulosReg != null) {
            lstAsist = daoTasistencia.getPerdidosxAsistencia(this.clsTblModulosReg.getIdModulo());
            lstHorario = daoHorario.getTotalHorasHorario(this.clsTblModulosReg.getIdModulo());
        } else {
            lstAsist = daoTasistencia.getPerdidosxAsistencia(0);
            lstHorario = daoHorario.getTotalHorasHorario(0);
        }
        if(lstHorario.size() > 0){
            objHorario = (Object) lstHorario.get(0);
            sumaHorario = ((BigDecimal) objHorario).doubleValue();
        }
        
        if (lstAsist.size() > 0) {
            for (int i = 0; i < lstTblNotases.size(); i++) {
                for (int j = 0; j < lstAsist.size(); j++) {
                    obj = (Object[]) (Object) lstAsist.get(j);
                    if (obj[0].equals(lstTblNotases.get(i).getIdMatricula())) {
                        sumaAsistencia = ((BigDecimal) obj[1]).doubleValue();
                        porcentaje = new BigDecimal((sumaAsistencia/sumaHorario)*100);
                        lstTblNotases.get(i).setTotalAsistencia(porcentaje.doubleValue());
                    }
                }
            }
        }

    }

    public void cargarTablaEdicionRegNotas() {
        lstTblNotas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int añoInicio = 0;
        int añoFin = 0;
        int cont = 0;
        boolean bandera = true;
        maestria = "";
        if (this.clsTblModulosReg != null) {
            for (int i = 0; i < lstCboModulos.size(); i++) {
                if (lstCboModulos.get(i).getIdModulo() == this.clsTblModulosReg.getIdModulo()) {
                    maestria = lstCboModulos.get(i).getMaestria();
                    i = lstCboModulos.size();
                }
            }
        }

        try {
            lstTblNotas.clear();
            estado = ' ';
            tipo_user = 0;
            DaoTNotas daoTnotas = new DaoTNotas();
            List<Notas> lstNotas = null;
            if (this.clsTblModulosReg != null) {
                lstNotas = daoTnotas.existe(this.clsTblModulosReg.getIdModulo(), "1",pondFecha.getId());
            } else {
                lstNotas = daoTnotas.existe(0, "1",0);
            }

            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

            if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
                tipo_user = 1;

            } else {
                tipo_user = 0;
            }
            if (lstNotas.size() > 0) {
                for (Notas notas : lstNotas) {

                    estado = notas.getEstado();
                    docente = notas.getModulo().getUsuario().getApellidos() + " " + notas.getModulo().getUsuario().getNombres();
                    bandera = false;
//                    calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaInicio());
                    añoInicio = calendar.get(Calendar.YEAR);

  //                  calendar.setTime(notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaFin());
                    añoFin = calendar.get(Calendar.YEAR);

                    estudiante = notas.getMatricula().getSolicitudInscripcion().getEstudiante().getApellidos() + " " + notas.getMatricula().getSolicitudInscripcion().getEstudiante().getNombres();
                    cont++;
                    lstTblNotas.add(new ClsNotas(notas.getMatricula().getSolicitudInscripcion().getEstudiante().getId(),
                            estudiante,
                            notas.getMatricula().getId(),
                            notas.getMatricula().getNMatricula(),
                            notas.getMatricula().getFechaMatricula(),
                            //notas.getMatricula().getSolicitudInscripcion().getPromocion().getId(),
                            0,
                            añoInicio + "-" + añoFin,
                            null,
                            0,
                            "",
                            //notas.getMatricula().getSolicitudInscripcion().getPromocion().getFechaResolucion(),
                            //notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getId(),
                            //notas.getMatricula().getSolicitudInscripcion().getPromocion().getMaestria().getDescripcion(),
                            cont,
                            notas.getNota().toString(),
                            notas.getObservacion(),
                            null,
                            true,
                            notas.getId(),
                            notas.getUsuario(),
                            notas.getResponsable(), null,
                            0.0, ' ',""));
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrar(Character accion) {
        DaoTNotas daoTnotas = new DaoTNotas();
        try {
            if (lstTblNotas.size() > 0) {
                msg = daoTnotas.registrar(lstTblNotas, this.clsTblModulosReg.getIdModulo(), accion, docente,pondFecha.getId());
            }

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
//    B  = Borrador-> Docente
//    G  = Guardado-> Docente
//    A  = Archivado-> Secretario u otros
//    E  = Eliminado-> Calificaciones de baja

    public void registrar_borrador() {
        registrar('B');
    }

    public void registrar_notas_docente() {
        registrar('G');
    }

    public void confirmar_notas_Secretaria() {
        registrar('A');
    }

    public void consultarMaestrias() {
        if (this.cedula.length() < 10) {
            this.estudiante = "";
            this.idProm = 0;
        } else {
            this.cedula = this.cedula.replace("-", "");
            cargarTblMatriculaPromocion();
        }

    }
    

    public void eliminarNotas() {

        DaoTNotas daoTnotas = new DaoTNotas();
        try {
            msg = daoTnotas.update(lstTblNotas, this.clsTblModulosReg.getIdModulo());
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (msg) {
            mensajesOk("Dato eliminado correctamente");
        } else {
            mensajesError("Error al eliminar dato");
        }
        vaciarCajas();
    }

    private void vaciarCajas() {
        this.idModulo = 0;
        cargarCboModulos();
        this.clsTblModulosReg.setIdModulo(0);
        lstTblNotas = new ArrayList<>();
    }

    public void consultarNotas(ClsNotas clsNotas) {
        idProm = clsNotas.getIdPromocion();
        cargarTablaNotas(clsNotas.getIdMatricula(), clsNotas.getIdPromocion());
    }

    public void consultarPagos(ClsNotas clsNotas) {
        cargarTablaPagos(clsNotas.getIdMatricula());
    }

    private void mensajesOk(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void mensajesError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje de la Aplicacion", msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void cargarTablaPagos(int idMatricula) {
        lstTblPagosReg = new ArrayList<>();
        try {
            lstTblPagosReg.clear();
            PagosDao pDao = new PagosDao();
            lstPagos = pDao.getTodosPagos(idMatricula);

        } catch (Exception ex) {
            Logger.getLogger(MbVModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPago() {
        try {
            if ((!file.getFileName().equals("") && valor != null) && valor.doubleValue() > 0) {
                PagosDao pDao = new PagosDao();
                if (!pDao.existeComprobante(idComprobante)) {
                    Matricula m = new Matricula();
                    pago = new Pago();
                    m.setId(selectedNota.getIdMatricula());
                    pago.setValor(valor);
                    pago.setIdComprobante(idComprobante);
                    pago.setEstado('E');
                    Date fecha = new Date();
                    pago.setFecha(fecha);
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateobj = new Date();
                    String nombreCarpeta = selectedNota.getNombresEstudiante().trim();
                    String maest = removeCaractEspeciales(selectedNota.getDescripMaestria()).trim();
                    File directorio = new File("c:/Postgrado/pagos/" + maest + "/" + selectedNota.getIdMatricula() + "-" + nombreCarpeta + "/");
                    if (!directorio.exists()) {
                        directorio.mkdirs();
                    }
                    String filename = selectedNota.getIdMatricula() + (df.format(dateobj).replaceAll(":", "-")).trim();
                    String extension = FilenameUtils.getExtension(file.getFileName());
                    Path ruta = Paths.get(directorio + "/" + filename + "." + extension);
                    InputStream input = file.getInputstream();
                    Files.copy(input, ruta, StandardCopyOption.REPLACE_EXISTING);
                    pago.setMatricula(m);
                    pago.setTipoPago(pDao.getTipoPago(idTipoPago));
                    pago.setRutaComprobante(ruta.toString());
                    pDao.registrar(pago);

                    FacesMessage message = new FacesMessage("Succesful", "Datos Guardados correctamente");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    FacesMessage message = new FacesMessage("Error", "El número de comprobante ya existe");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                valor = BigDecimal.valueOf(0.00);
                FacesMessage message = new FacesMessage("Error", "Ingrese datos");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (IOException ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Error", ex.toString());
            System.out.println(ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            Logger.getLogger(MbVNotas.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage("Error", ex.toString());
            System.out.println(ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        idComprobante = "";
        valor = BigDecimal.valueOf(0.00);
        file = null;
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
