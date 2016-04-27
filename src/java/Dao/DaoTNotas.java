/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clases.ClsNotas;
import Interface.InterfaceNotas;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Notas;
import Pojo.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author server
 */
public class DaoTNotas implements InterfaceNotas{
    
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
    public boolean registrar(List<ClsNotas> lstNotas, int idModulo, Character accion) throws Exception {
        boolean band = false;
        try {
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            iniciaOperacion();
            Notas tNotas = null;
            Matricula matricula = null;
            Modulo modulo = null;
            BigDecimal bigdec;
            Date fecha = new Date();
            for (int i = 0; i < lstNotas.size(); i++) {
                tNotas = new Notas();
                
                tNotas.setUsuario(usuario.getApellidos()+" "+usuario.getNombres());
                bigdec = new BigDecimal(Double.parseDouble(lstNotas.get(i).getNota()));
                tNotas.setNota(bigdec);
                tNotas.setEstado(accion);
                tNotas.setFecha(fecha);
                matricula = new Matricula();
                matricula.setId(lstNotas.get(i).getIdMatricula());
                tNotas.setMatricula(matricula);
                modulo = new Modulo();
                modulo.setId(idModulo);
                tNotas.setModulo(modulo);
                tNotas.setObservacion(lstNotas.get(i).getObservacion());
                tNotas.setId(lstNotas.get(i).getIdNota());
                
                sesion.saveOrUpdate(tNotas);
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
    public List<Notas> getTodasNotas(int idMatricula) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        //String hql="from Notas nota inner join fetch nota.modulo modul where nota.modulo = "+idModulo+" and "
          //      + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        String hql="from Notas nota inner join fetch nota.modulo modul where "
                + "nota.matricula="+idMatricula+" order by modul.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Notas> lstNotas=(List<Notas>) query.list();
        sesion.close();
        return lstNotas;
    }

    @Override
    public Notas getNotas(String idNotas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Notas tNotas) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notas> existe(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        
        int tipo_user = 0;
        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String consulta = "";
         
        if(usuario.getTipoUsuario().getDescripcion().equals("Profesor(a)") || usuario.getTipoUsuario().getDescripcion().equals("Docente") || usuario.getTipoUsuario().getDescripcion().equals("PROFESOR(A)") || usuario.getTipoUsuario().getDescripcion().equals("DOCENTE")){
            consulta = "and user.nombres='"+usuario.getNombres()+"' and user.apellidos='"+usuario.getApellidos()+"'";
        }else{
            consulta = "";
        }
        
        String hql="from Notas nota inner join fetch nota.matricula matr inner join fetch matr.solicitudInscripcion solin inner join fetch solin.estudiante est inner join fetch nota.modulo mod inner join fetch mod.promocion pr\n" +
                   " inner join fetch mod.usuario user inner join fetch pr.maestria maest where mod.id="+idModulo+" and nota.estado='0' "+consulta+"  order by est.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Notas> lstNotas=(List<Notas>) query.list();
        sesion.close();
        return lstNotas;
    }
    
    
}
