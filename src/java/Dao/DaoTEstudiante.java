/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceEstudiante;
import Pojo.Estudiante;
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
public class DaoTEstudiante implements InterfaceEstudiante{
    
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion() throws HibernateException
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    private void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    @Override
    public boolean registrar(Estudiante tEstudiante) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tEstudiante);

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
    public boolean usuarioExisre(String cedula) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estudiante> getTblEstudiante(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Estudiante est inner join fetch est.solicitudInscripcions sol inner join fetch sol.matriculas matr inner join fetch matr.promocion pr inner join fetch pr.modulos modul "
                + "where modul.id="+idModulo+" order by est.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Estudiante> lstEstudiante=(List<Estudiante>) query.list();
        sesion.close();
        return lstEstudiante;
    }

    @Override
    public List<Estudiante> getEstudiantes() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql = "from Estudiante est order by est.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Estudiante> lstEstudiante=(List<Estudiante>) query.list();
        sesion.close();
        return lstEstudiante;
    }

    @Override
    public Estudiante getEstudiante(String idUsuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Estudiante tEstudiante) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
