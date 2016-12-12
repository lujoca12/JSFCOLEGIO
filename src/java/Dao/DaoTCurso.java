/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceCurso;
import Pojo.Curso;
import Pojo.Precio;
import Pojo.Promocion;
import Pojo.Seccion;
import Pojo.TipoPrecio;
import Pojo.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.math.BigDecimal;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;

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
        String hql="from Curso c where c.estado = '1' order by c.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }
    
    @Override
    public List<Curso> getCursosModulos(boolean estado) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String consulta = "";
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if (usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")) {
            consulta = "and user.id = "+usuario.getId()+"";
        }else{
            consulta = "";
        }
        
        String hql="from Curso c inner join fetch c.modulos modul inner join fetch modul.usuario user "
                + "inner join fetch modul.promocion pr where c.estado = '1' "
                + "and (year(current_date) >= year(pr.fechaInicio) and year(current_date)<= year(pr.fechaFin)) "
                + ""+consulta+" "
                + "order by c.descripcion asc";
        Query query = sesion.createQuery(hql);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }
    
    
    @Override
    public List<Curso> getCursoSeccion() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Curso c inner join fetch c.seccion secc where c.estado = '1' and secc.estado = '1' order by c.descripcion, c.paralelo asc";
        Query query = sesion.createQuery(hql);

        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }
    
    @Override
    public List<Curso> getCursoD(String cursoDescripcion, boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String consulta = "";
        String estado = "";
        if(mostrar)
            estado = "0";
        else
            estado = "1";
        
        if(cursoDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and c.descripcion like '%"+cursoDescripcion+"%'";
        
        //String hql="from Maestria m where m.estado = '1' "+consulta+" order by m.id desc";
        String hql="from Curso c inner join fetch c.seccion secc where c.estado = '"+estado+"' "+consulta+" and secc.estado = '1' order by c.descripcion, c.paralelo asc";
        Query query = sesion.createQuery(hql);

        List<Curso> lstCursos=(List<Curso>) query.list();
        sesion.close();
        return lstCursos;
    }
    
    @Override
    public List<Precio> getPreciosCursoD(String cursoDescripcion, boolean mostrar) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        String consulta = "";
        String estado = "";
        if(mostrar)
            estado = "0";
        else
            estado = "1";
        
        if(cursoDescripcion.isEmpty())
            consulta = "";
        else
            consulta = "and c.descripcion like '%"+cursoDescripcion+"%'";
        
        //String hql="from Maestria m where m.estado = '1' "+consulta+" order by m.id desc";
        String hql="from Precio prec inner join fetch prec.tipoPrecio tprec inner join fetch prec.curso c inner join fetch c.seccion secc inner join fetch secc.modalidad modal "
                + "where c.estado='"+estado+"' "+consulta+" and tprec.estado='1' order by c.descripcion, c.paralelo asc";
        Query query = sesion.createQuery(hql);

        List<Precio> lstPrecio=(List<Precio>) query.list();
        sesion.close();
        return lstPrecio;
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
    public boolean updatePrecio(Precio tPrecio) throws Exception {
        boolean band = false;
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        try {
            sesion.update(tPrecio);
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
    public boolean registrarCursoPrecio(Curso tCurso, BigDecimal precioMatricula, BigDecimal precioColegiatura) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tCurso);
            tx.commit();
            sesion.close();
            
            iniciaOperacion();
            TipoPrecio tipo_Precio = null;
            Precio precio = null;
            for (int i = 1; i < 3; i++) {
                tipo_Precio = new TipoPrecio();
                tipo_Precio.setId(i);
                precio = new Precio();
                precio.setTipoPrecio(tipo_Precio);
                precio.setCurso(tCurso);
                if(i == 1)
                    precio.setValor(precioMatricula);
                else
                    precio.setValor(precioColegiatura);
                
                sesion.saveOrUpdate(precio);
            }
            
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
    public boolean existe(Curso tCurso) throws Exception {
        this.sesion = null;
        this.tx = null;
        boolean band = false;
        iniciaOperacion();
        String hql="from Curso c inner join fetch c.seccion secc where c.descripcion='"+tCurso.getDescripcion()+"' and c.paralelo='"+tCurso.getParalelo()+"' "
                + "and secc.descripcion='"+tCurso.getSeccion().getDescripcion()+"' and c.estado = '1' and secc.estado = '1'";
        Query query = sesion.createQuery(hql);
        List<Seccion> seccion=(List<Seccion>) query.list();
        if(seccion.size() > 0)
            band = true;
        else
            band = false;
        
        sesion.close();
        return band;
    }

    @Override
    public boolean registrar(Curso tCurso) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.saveOrUpdate(tCurso);

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
