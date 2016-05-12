/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.Archivos;
import Pojo.DatosDom;
import Pojo.DatosLab;
import Pojo.DatosNac;
import Pojo.Estudiante;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import Pojo.SolicitudInscripcion;
import Pojo.Titulo;
import Pojo.Universidad;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author chiti
 */
public class InscripcionDao implements Serializable{
    private Session sesion;
    private Transaction tx;
    
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
    public boolean insertar(Estudiante Estudiante,DatosNac DatosNac,DatosDom DatosDom ,DatosLab DatosLab,SolicitudInscripcion sInscripcon,Titulo titulo,Universidad Universidad) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            //guardar todo
            sesion.save(Estudiante);
            sesion.save(DatosDom);
            if(DatosNac!=null)
                sesion.save(DatosNac);
            sesion.save(DatosLab);
            if(Universidad.getDescripcion()!=null)
                sesion.saveOrUpdate(Universidad);
            sesion.save(titulo);
            sesion.save(sInscripcon);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    public List<SolicitudInscripcion> getInscripcionesEstudiantes() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from SolicitudInscripcion si inner join fetch si.estudiante e inner join fetch si.promocion pr inner join fetch pr.maestria m"
                + "  where si.fechaRevision=null and si.estado='E' order by si.fechaRealizacion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<SolicitudInscripcion> lst=(List<SolicitudInscripcion>) query.list();
        sesion.close();
        return lst;
    }
    public List<Archivos> getArchivosInscripciones(String IdInscripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Archivos a inner join fetch a.requisitosPromo rp inner join fetch rp.requisitos where a.solicitudInscripcion.id ='"+IdInscripcion+"'";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Archivos> lst=(List<Archivos>) query.list();
        sesion.close();
        return lst;
    }
}
