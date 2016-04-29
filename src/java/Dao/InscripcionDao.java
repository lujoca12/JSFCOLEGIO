/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import Pojo.SolicitudInscripcion;
import java.util.List;
/**
 *
 * @author chiti
 */
public class InscripcionDao {
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
    
    public List<SolicitudInscripcion> getInscripcionesEstudiantes() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
       
        String hql="from SolicitudInscripcion si inner join fetch si.estudiante e inner join fetch si.promocion pr inner join fetch pr.maestria m"
                + " where si.fechaRevision=null order by si.fechaRealizacion asc";
       
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<SolicitudInscripcion> lst=(List<SolicitudInscripcion>) query.list();
        sesion.close();
        return lst;
    }
}
