/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceModulos;
import Pojo.Modulo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTModulo implements InterfaceModulos{
    
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

    @Override
    public boolean registrar(Modulo tModulo) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tModulo);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Modulo> getTodosModulo() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Modulo> lstPermiso=(List<Modulo>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public Modulo getModulo(String idModulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Modulo tModulo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean existe(Modulo tModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        
        
        iniciaOperacion();
        String hql="from Modulo m where m.descripcion='"+tModulo.getDescripcion()+"' and "
                + "m.promocion="+tModulo.getPromocion().getId()+" and "
                + "m.usuario="+tModulo.getUsuario().getId()+"";
        Query query = sesion.createQuery(hql);
        List<Modulo> modulo=(List<Modulo>) query.list();
        if(modulo.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }
    
}
