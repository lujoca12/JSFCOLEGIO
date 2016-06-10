/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Clases.ClsNotas;
import Interface.InterfaceAsistencia;
import Pojo.Asistencia;
import Pojo.Matricula;
import Pojo.Modulo;
import Pojo.Notas;
import Pojo.Usuario;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class DaoTAsistencias implements InterfaceAsistencia{
    
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
    public boolean registrar(List<ClsNotas> lstNotas, int idModulo, Date fecha) throws Exception {
        boolean band = false;
        try {
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            iniciaOperacion();
            Asistencia tAsistencia = null;
            Matricula matricula = null;
            Modulo modulo = null;
            BigDecimal bigDec;
            
            //Date fecha = new Date();
            for (int i = 0; i < lstNotas.size(); i++) {
                tAsistencia = new Asistencia();
                tAsistencia.setUsuario(usuario.getApellidos()+" "+usuario.getNombres());
                
                if(lstNotas.get(i).getEstado())
                    tAsistencia.setEstado('1');
                else
                    tAsistencia.setEstado('0');
                
                tAsistencia.setFecha(fecha);
                matricula = new Matricula();
                matricula.setId(lstNotas.get(i).getIdMatricula());
                tAsistencia.setMatricula(matricula);
                modulo = new Modulo();
                modulo.setId(idModulo);
                tAsistencia.setModulo(modulo);
                tAsistencia.setObservacion(lstNotas.get(i).getObservacion());
                bigDec = new BigDecimal(lstNotas.get(i).getHorasAsistidas());
                tAsistencia.setHoras_asistidas(bigDec);
                
                sesion.save(tAsistencia);
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
    public List<Asistencia> getTodasAsistencias() throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Permiso as p where p.estado = '1' order by p.orden, p.descripcion asc";
        Query query = sesion.createQuery(hql);
        List<Asistencia> lstPermiso=(List<Asistencia>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Asistencia> getAlumnoRetirado(int idMatricula, int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="from Asistencia asist where asist.matricula = "+idMatricula+" and asist.modulo="+idModulo+" and asist.observacion = 'R'";
        Query query = sesion.createQuery(hql);
        List<Asistencia> lstPermiso=(List<Asistencia>) query.list();
        sesion.close();
        return lstPermiso;
    }
    
    @Override
    public List<Asistencia> getPerdidosxAsistencia(int idModulo) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
        String hql="select matr.id, ((sum(asist.horas_asistidas)/mod.totalHorasModulo)*100) from Asistencia asist inner join asist.modulo mod inner join asist.matricula matr\n" +
                    " where mod.id = "+idModulo+" group by mod.totalHorasModulo, matr.id";
        Query query = sesion.createQuery(hql);
        List<Asistencia> lstAsistencia=(List<Asistencia>) query.list();
        Object [] Obj = query.list().toArray();
        sesion.close();
        return lstAsistencia;
    }

    @Override
    public Asistencia getAsistencias(String idMaestria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Asistencia tAsistencia) throws Exception {
        boolean band = false;
        try {
            iniciaOperacion();
            sesion.update(tAsistencia);

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
    public boolean delete(List<ClsNotas> lstNotas, int idModulo) throws Exception {
        boolean band = false;
        try {
            //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            iniciaOperacion();
            Asistencia tAsistencia = null;
            Matricula matricula = null;
            Modulo modulo = null;
            
            //Date fecha = new Date();
            for (int i = 0; i < lstNotas.size(); i++) {
                tAsistencia = new Asistencia();
                tAsistencia.setUsuario(usuario.getApellidos()+" "+usuario.getNombres());
                
                tAsistencia.setEstado('E');
                
                tAsistencia.setFecha(lstNotas.get(i).getFecha());
                matricula = new Matricula();
                matricula.setId(lstNotas.get(i).getIdMatricula());
                tAsistencia.setMatricula(matricula);
                modulo = new Modulo();
                modulo.setId(idModulo);
                tAsistencia.setModulo(modulo);
                tAsistencia.setObservacion(lstNotas.get(i).getObservacion());
                tAsistencia.setId(lstNotas.get(i).getIdNota());
                sesion.update(tAsistencia);
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
    public List<Asistencia> existe(int idModulo, Date fecha) throws Exception {
        this.sesion = null;
        this.tx = null;
        iniciaOperacion();
//      Calendar cal = Calendar.getInstance();
//      cal.add(Calendar.DATE,0);
//      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//      String formatted = format1.format(cal.getTime());
        
        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        String hql="from Asistencia asist inner join fetch asist.matricula matr inner join fetch matr.solicitudInscripcion solin inner join fetch solin.estudiante est inner join fetch asist.modulo mod inner join fetch mod.promocion pr\n" +
                   " inner join fetch mod.usuario user inner join fetch pr.maestria maest where mod.id="+idModulo+" and asist.fecha ='"+fecha+"' and asist.estado<>'E' and user.nombres='"+usuario.getNombres()+"' and user.apellidos='"+usuario.getApellidos()+"' order by est.apellidos asc";
        Query query = sesion.createQuery(hql);
        List<Asistencia> lstAsist=(List<Asistencia>) query.list();
        sesion.close();
        return lstAsist;
    }
    
}
