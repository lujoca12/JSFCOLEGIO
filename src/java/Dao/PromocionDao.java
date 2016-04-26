/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Pojo.Promocion;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Seeting
 */

public class PromocionDao {
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
    
    
    public List<Promocion> getPromocionesMaestrias(int IdMaestria) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Promocion pr inner join fetch pr.maestria m where m.estado='1' and m.id='"+IdMaestria+"' and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) order by pr.id desc";
        Query query = sesion.createQuery(hql);
        List<Promocion> lstPermiso=(List<Promocion>) query.list();
        sesion.close();
        return lstPermiso;
    }
    public List<Promocion> getMaestriasPromocion() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las maestrias registradas en el anio actual
        String hql="from Promocion order by m.descripcion asc";
        //Consulta hasta Usuarios
        //String hql="from Maestria m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Promocion> lstPromocion=(List<Promocion>) query.list();
        sesion.close();
        return lstPromocion;
    }
    
}
