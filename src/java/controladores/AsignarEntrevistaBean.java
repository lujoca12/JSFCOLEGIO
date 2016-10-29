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
import Dao.postgradoDao;
import Pojo.Archivos;
import Pojo.Matricula;
import Pojo.Postgrado;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
    private List<Archivos> lstArchivos;
    private SolicitudInscripcion SelectedInscripcion;
    private String observacion;
    private InscripcionDao d;
    private Object[] a = null;
    private String idS;
    private Date fecha;
    private Date fechaActual=new Date();
    private String lugar;
    private String hora;
    private String minuto;
    private String fecha2;
    private StreamedContent file;
    private List<ClsArchivos> archivos=null;
    private List<SolicitudInscripcion> lstSInscripcionFiltrada;

    public List<SolicitudInscripcion> getLstSInscripcionFiltrada() {
        return lstSInscripcionFiltrada;
    }

    public void setLstSInscripcionFiltrada(List<SolicitudInscripcion> lstSInscripcionFiltrada) {
        this.lstSInscripcionFiltrada = lstSInscripcionFiltrada;
    }    

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }    
    
    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public AsignarEntrevistaBean() throws Exception {

    }

    @PostConstruct
    public void init() {
       obtenerSolicitudes();
    }
    
    private void obtenerSolicitudes(){
    try {
            d = new InscripcionDao();
            lstSInscripcion = d.getInscripcionesEstudiantes();

        } catch (Exception ex) {
        }
    }

    
    public void enviarEmail() throws Exception {
        postgradoDao psgDao = new postgradoDao();
        Postgrado psg = psgDao.getPostgrado();
        if(psg!=null){
        final String username = psg.getEmail();
        final String password = psg.getClaveEmail();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fecha2 = dateFormat.format(fecha) + " " + hora + ":" + minuto + ":00";
            
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //falta el curso ojo
            //String msj = "Saludos Sr(a). \n Le damos a conocer que la entrevista para la aprobacion de la matricula de la "+
                    //SelectedInscripcion.getPromocion().getMaestria().getDescripcion() +" se llevará a cabo el día "+fecha2+ 
                    //" en el/la "+lugar+" \n Postgrado UTEQ.";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(SelectedInscripcion.getEstudiante().getEmail()));
            message.setSubject("Entrevista Maestria");
            //message.setText("La entrevista tendrá lugar en " + lugar + " el " + dateFormat.format(fecha));
            //message.setText(msj);
            Transport.send(message);
            FacesMessage m = new FacesMessage("Succesful", "Correo enviado");
            FacesContext.getCurrentInstance().addMessage(null, m);
            InscripcionDao iD = new InscripcionDao();
            SelectedInscripcion.setFechaEntrevista(dateFormat2.parse(fecha2));
            SelectedInscripcion.setLugarEntrevista(lugar);
            SelectedInscripcion.setEstado('T');
            if (iD.insertarEntrevista(SelectedInscripcion)) {                
                fecha = null;
                lstSInscripcion.clear();                
                obtenerSolicitudes();
            } else {
                FacesMessage m2 = new FacesMessage("Error", "No se ha podido guardar los datos");
                FacesContext.getCurrentInstance().addMessage(null, m2);
            }
        } catch (AddressException e) {
            System.out.println(e.toString());
            FacesMessage m = new FacesMessage("Error", "La dirección de correo no existe");
            FacesContext.getCurrentInstance().addMessage(null, m);
            // ...
        } catch (MessagingException e) {
            // ...
            System.out.println(e.toString());
            FacesMessage m = new FacesMessage("Error", "No se ha podido conectar con el servidor, inténtelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, m);
        } catch (Exception ex) {
            Logger.getLogger(AsignarEntrevistaBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
            FacesMessage m = new FacesMessage("Error", "No se ha podido guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, m);
        }
        }else{
            FacesMessage m = new FacesMessage("Error", "No se tiene un correo registrado en el sistema");
            FacesContext.getCurrentInstance().addMessage(null, m);
        }
        lugar="";
        fecha=null;
        hora="";
        minuto="";
            
        // ...

    }

}
