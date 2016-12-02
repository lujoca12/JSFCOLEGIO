/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.PonderacionFecha;
import Pojo.Ponderaciones;
import Pojo.Usuario;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfacePonderacion {
    public boolean registrar(Ponderaciones tPonderaciones) throws Exception;
    public List<Ponderaciones> getPadres() throws Exception;
    public List<Ponderaciones> getTodosPonderacioness(boolean mostrar) throws Exception;
    public Ponderaciones getPonderaciones(String idUsuario) throws Exception;
    public boolean update(Ponderaciones tPonderaciones) throws Exception;
    public boolean updatePadres(Ponderaciones tPonderaciones) throws Exception;
    public List<Ponderaciones> getParciales() throws Exception;
    public boolean registrarPondFecha(PonderacionFecha tPondFecha) throws Exception;
    public List<PonderacionFecha> getTblPonderacionFecha(String ponderacionDescripcion, boolean mostrar) throws Exception;
    public List<PonderacionFecha> getPonderacionFecha(boolean mostrar) throws Exception;
}
