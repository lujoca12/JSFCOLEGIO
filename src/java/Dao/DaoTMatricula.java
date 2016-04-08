/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceMatricula;
import Pojo.Matricula;
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
public class DaoTMatricula implements InterfaceMatricula{
    
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
    public boolean registrar(Matricula tMatricula) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tMatricula);

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
    public List<Matricula> getTodasMatriculas() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Matricula> lstPermiso=(List<Matricula>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Matricula> getMatriculaMaestria(String cedula) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Matricula matr inner join fetch matr.promocion pr inner join fetch pr.maestria maest inner join fetch matr.estudiante estud where estud.cedPasaporte = '"+cedula+"' and matr.estado='1' and maest.estado = '1'  order by maest.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Matricula> lstPermiso=(List<Matricula>) query.list();
        sesion.close();
        return lstPermiso;
    }

    @Override
    public Matricula getMatriculas(String idMatricula) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Matricula tMatricula) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
