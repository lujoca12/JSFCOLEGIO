/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.ClsNotas;
import Pojo.Asistencia;
import java.util.Date;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceAsistencia {
    public boolean registrar(List<ClsNotas> lstNotas, int idModulo, Date fecha, Character estado) throws Exception;
    public List<Asistencia> getTodasAsistencias() throws Exception;
    public List<Asistencia> existe(int idModulo,Date fecha) throws Exception;
    public Asistencia getAsistencias(String idMaestria) throws Exception;
    public boolean update(Asistencia tAsistencia) throws Exception;
    public boolean delete(List<ClsNotas> lstNotas, int idModulo, Character estado) throws Exception;
    public List<Asistencia> getPerdidosxAsistencia(int idModulo) throws Exception;
    public List<Asistencia> getAlumnoRetirado(int idMatricula, int idModulo) throws Exception;
}