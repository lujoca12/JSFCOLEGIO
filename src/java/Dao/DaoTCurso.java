/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceCurso;
import Pojo.Curso;
import Pojo.Promocion;
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
public class DaoTCurso implements InterfaceCurso{
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
    public List<Curso> getTodosCursos() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //Consulta para que me aparezcan solo las maestrias registradas en el anio actual
        String hql="from Curso m inner join fetch m.promocions pr where m.estado='1' and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) order by m.descripcion asc";
        //Consulta hasta Usuarios
        //String hql="from Curso m inner join fetch m.promocions pr inner join fetch pr.modulos mod inner join fetch mod.usuario user where m.estado='1' and year(pr.fechaInicio) = year(current_date) and pr.idUsuario=user.id order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }
    
    
    @Override
    public List<Curso> getCursoSeccion() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Curso m inner join fetch m.promocions pr where m.estado = '1' order by m.descripcion asc";
        Query query = sesion.createQuery(hql);

        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }

    @Override
    public Curso getCursos(String idCurso) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Curso tCurso) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        try {
            sesion.update(tCurso);
            band = true;
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
    
    @Override
    public boolean delete(Curso tCurso) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        try {
            sesion.delete(tCurso);
            band = true;
            tx.commit();
            sesion.close();
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }

    @Override
    public List<Curso> getCursosxDescripcion(String descripcion) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Curso m where m.estado='1' and m.descripcion like '%"+descripcion+"%' order by m.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }

    @Override
    public boolean registrar(Curso tCurso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.save(tCurso);

            tx.commit();
            sesion.close();
            band = true;
        } catch (Exception e) {
            tx.rollback();
            band = false;
        }
        
        return band;
    }
}
