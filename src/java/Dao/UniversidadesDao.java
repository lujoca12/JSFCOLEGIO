/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Universidad;
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
public class UniversidadesDao {
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
    
    public SelectItem[] getUniversidades(boolean EsEcuador) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="";
       if(EsEcuador)
         hql="from Universidad u inner join fetch u.pais p where p.descripcion like 'Ecuador' order by u.descripcion asc";
       else
           hql="from Universidad u inner join fetch u.pais p where p.descripcion not like 'Ecuador' order by u.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Universidad> lst=(List<Universidad>) query.list();
        SelectItem[] uni=new SelectItem[lst.size()];
        int i=0;
        for(Universidad u : lst){
            uni[i]=new SelectItem(u.getId(),u.getDescripcion());
            i++;
        }
            
        sesion.close();
        return uni;
    }
     public Universidad getUniversidad(String Id){
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from Universidad u where u.id = '"+Id+"' order by u.descripcion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Universidad> lst=(List<Universidad>) query.list();
        Universidad u=null;
        for(Universidad p : lst)
            u=p;
        sesion.close();
        return u; 
    }
}
