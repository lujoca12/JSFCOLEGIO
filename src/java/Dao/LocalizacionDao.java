/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.Canton;
import Pojo.Pais;
import Pojo.Parroquia;
import Pojo.Provincia;
import Pojo.Universidad;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
/**
 *
 * @author chiti
 */
public class LocalizacionDao {
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
    
    public List<Pais> getPaises(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Pais p order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Pais> lst=(List<Pais>) query.list();
        sesion.close();
        return lst; 
    }
    public Parroquia getParroquia(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Parroquia p where p.id = '"+Id+"' order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Parroquia> lst=(List<Parroquia>) query.list();
        Parroquia pa = null;
        for(Parroquia p : lst)
            pa=p;
        sesion.close();
        return pa; 
    }
    public String getNombrePais(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Pais p where p.id = '"+Id+"' order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Pais> lst=(List<Pais>) query.list();
        String pais="" ;
        for(Pais p : lst)
            pais=p.getDescripcion();
        sesion.close();
        return pais; 
    }
    public Pais getPais(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Pais p where p.id = '"+Id+"' order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Pais> lst=(List<Pais>) query.list();
        Pais pais=null;
        for(Pais p : lst)
            pais=p;
        sesion.close();
        return pais; 
    }
    public List<Provincia> getProvincias(){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Provincia p inner join fetch p.cantons c order by p.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Provincia> lst=(List<Provincia>) query.list();
        sesion.close();
        return lst; 
    }
    public List<Canton> getCantonProvicia(int IdProvincia) throws Exception {
        List<Canton> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Canton c inner join fetch c.provincia p where p.id='"+IdProvincia+"' order by c.descripcion asc";
            Query query = sesion.createQuery(hql);
            lst = (List<Canton>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
     public List<Parroquia> getParroquiaCanton(int IdCanton) throws Exception {
        List<Parroquia> lst = null;
        try {
            this.sesion = null;
            this.tx = null;
            iniciaOperacion();
            String hql = "from Parroquia p inner join fetch p.canton c where c.id='"+IdCanton+"' order by p.descripcion asc";
            Query query = sesion.createQuery(hql);
            lst = (List<Parroquia>) query.list();
            sesion.close();
        } catch (Exception ex) {
        }
        return lst;
    }
    
}
