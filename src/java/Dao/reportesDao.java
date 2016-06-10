/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.HibernateUtil;

/**
 *
 * @author chiti
 */
public class reportesDao {
    private Session sesion;
    private Transaction tx;
    private Map param = new HashMap();
    private Date date = new Date();
    private SimpleDateFormat sf = new SimpleDateFormat("dd-M-yyyy@HH.mm.ss");
    
    
    
    
    private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        }catch(HibernateException ex){
            
        }
        
    }
     
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    public StreamedContent reporteIngresoMensuales(int Mes,int Anio){
        //boolean band = false;
        StreamedContent media = null;
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
        .getExternalContext().getContext();
        String realPath = ctx.getRealPath("/");
        realPath += "Modulos\\Reportes\\";
        
        iniciaOperacion();
        
        param.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, sesion);
        
        param.put("fecha",String.valueOf(Anio+"/"+Mes+"/"+"01"));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        String file = "reporte" + sf.format(date.getTime())+ ".pdf";
        JasperPrint jPrint = null;
        try {
           // JasperReport JReporte = JasperCompileManager.compileReport(realPath+"actaAdmision.jasper");
            jPrint = JasperFillManager.fillReport(realPath+"reporteIngresosMensual.jasper", param);
            JasperExportManager.exportReportToPdfStream(jPrint, baos);
            
             baos.flush();
        baos.close();

        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        media = new DefaultStreamedContent(is, "application/pdf", file);
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            //band = false;
        }
        
        //return band;
        return media;
    }
}
