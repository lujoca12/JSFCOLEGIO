/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Clases.ClsArchivos;
import Pojo.Estudiante;
import Pojo.SolicitudInscripcion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import Dao.InscripcionDao;
import Dao.MatriculaDao;
import Pojo.Archivos;
import Pojo.Matricula;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author chiti
 */
@Named(value = "asignarEntrevistaBean")
@ViewScoped
public class AsignarEntrevistaBean implements Serializable {

    /**
     * Creates a new instance of AsignarEntrevistaBean
     */
    private List<Estudiante> estudiantes;
    private List<SolicitudInscripcion> lstSInscripcion;
    private List<Archivos> lstArchivos = null;
    private SolicitudInscripcion SelectedInscripcion;
    private String observacion;
    private InscripcionDao d;
    private Object[] a = null;
    private String idS;
    private Date fechaHora;
    private String lugar;

    private StreamedContent file;
    private List<ClsArchivos> archivos;

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public List<ClsArchivos> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ClsArchivos> archivos) {
        this.archivos = archivos;
    }

    public String getIdS() {
        return idS;
    }

    public void setIdS(String idS) {
        this.idS = idS;
    }

    public Object[] getA() {
        return a;
    }

    public void setA(Object[] a) {
        this.a = a;
    }

    public InscripcionDao getD() {
        return d;
    }

    public void setD(InscripcionDao d) {
        this.d = d;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public SolicitudInscripcion getSelectedInscripcion() {
        return SelectedInscripcion;
    }

    public void setSelectedInscripcion(SolicitudInscripcion SelectedInscripcion) {
        this.SelectedInscripcion = SelectedInscripcion;

    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<SolicitudInscripcion> getLstSInscripcion() {
        return lstSInscripcion;
    }

    public void setLstSInscripcion(List<SolicitudInscripcion> lstSInscripcion) {
        this.lstSInscripcion = lstSInscripcion;
    }

    public AsignarEntrevistaBean() {

    }

    @PostConstruct
    public void init() {
        try {
            d = new InscripcionDao();
            lstSInscripcion = d.getInscripcionesEstudiantes();
        } catch (Exception ex) {
        }
    }

    public void guardarMatricula() {
        try {
            Matricula m = new Matricula();
            m.setEstado('1');
            Date fecha = new Date();
            m.setFechaMatricula(fecha);
            m.setSolicitudInscripcion(SelectedInscripcion);
            SelectedInscripcion.setEstado('A');
            SelectedInscripcion.setFechaRevision(fecha);
            SelectedInscripcion.setObservacion(observacion);
            MatriculaDao mDao = new MatriculaDao();
            mDao.insertar(m, SelectedInscripcion);
            lstSInscripcion.remove(SelectedInscripcion);
            FacesMessage message = new FacesMessage("Succesful", "Datos guardados");
            FacesContext.getCurrentInstance().addMessage(null, message);
            SelectedInscripcion = null;
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("Erorr", "Error al guardarlos datos");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void rechazarMatricula() {
        try {

            SelectedInscripcion.setEstado('R');
            Date fecha = new Date();
            SelectedInscripcion.setFechaRevision(fecha);
            MatriculaDao mDao = new MatriculaDao();
            mDao.rechazar(SelectedInscripcion);
            lstSInscripcion.remove(SelectedInscripcion);
            SelectedInscripcion = null;
        } catch (Exception ex) {

        }
    }

    public void obtenerRequisitos() {

        try {
            archivos = new ArrayList<>();
            lstArchivos = new ArrayList<>();
            if (SelectedInscripcion != null) {
                lstArchivos = d.getArchivosInscripciones(String.valueOf(SelectedInscripcion.getId()));

                for (Archivos a : lstArchivos) {
                    InputStream input = new FileInputStream(a.getRuta());
                    String extension = a.getRuta().substring(a.getRuta().lastIndexOf('.'));
                    file = new DefaultStreamedContent(input, a.getRequisitosPromo().getRequisitos().getTipoArchivo(), a.getRequisitosPromo().getRequisitos().getFormato() + extension);
                    archivos.add(new ClsArchivos(a.getRequisitosPromo().getRequisitos().getDescripcion(), file));
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(AsignarEntrevistaBean.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void enviarEmail() {
        final String username = "postgradouteq@gmail.com";
        final String password = "postgrado123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("postgradouteq@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("chiting23@gmail.com"));
            message.setSubject("Entrevista Maestria");
            message.setText("La entrevista tendrá lugar en el "+lugar+" a las "+fechaHora.toString());

            Transport.send(message);
            FacesMessage m = new FacesMessage("Succesful", "Correo enviado");
            FacesContext.getCurrentInstance().addMessage(null, m);
            lstSInscripcion.remove(SelectedInscripcion);
        } catch (AddressException e) {
            System.out.println(e.toString());
            // ...
        } catch (MessagingException e) {
            // ...
            System.out.println(e.toString());
        }
        // ...

    }

}
