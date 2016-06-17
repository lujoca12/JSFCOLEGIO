/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Interface.InterfaceTribunal;
import Pojo.TipoTribunal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author SERVER
 */
public class DaoTribunal implements InterfaceTribunal{
    private Session sesion;
    private Transaction tx;
    private Session sesion1;
    private Transaction tx1;
  private void iniciaOperacion()
    {
        try{
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion1 = HibernateUtil.getSessionFactory().openSession();
            tx1 = sesion1.beginTransaction();
        }catch(HibernateException ex){            
        }
    }
    @Override
    public List<TipoTribunal> getTodastipoTribunal() throws Exception {
   this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from TipoTribunal";
       Query query = sesion.createQuery(hql);
        List<TipoTribunal> lsttipotitu=(List<TipoTribunal>) query.list();
        sesion.close();
        return lsttipotitu;
    }
    
}
