/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Asistencia;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceAsistencia {
    public boolean registrar(Asistencia tAsistencia) throws Exception;
    public List<Asistencia> getTodasAsistencias() throws Exception;
    
    public Asistencia getAsistencias(String idMaestria) throws Exception;
    public boolean update(Asistencia tAsistencia) throws Exception;
}
