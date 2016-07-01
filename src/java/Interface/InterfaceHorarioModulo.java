/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.HorarioModulo;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceHorarioModulo {
    public boolean registrar(HorarioModulo tHorarioModulo) throws Exception;
    public List<HorarioModulo> getTodosHorarios() throws Exception;
    public List<HorarioModulo> getTblHorarios(String moduloDescripcion, boolean mostrar) throws Exception;
    public HorarioModulo getHorario(String idHorarioModulo) throws Exception;
    public boolean update(HorarioModulo tHorarioModulo) throws Exception;
    public boolean delete(HorarioModulo tHorarioModulo) throws Exception;
    public boolean existe(HorarioModulo tHorarioModulo) throws Exception;
    public List<HorarioModulo> getFechaHorasModulos(int idModulo) throws Exception;
    public String getTotalHorasAsignadas(int idModulo) throws Exception;
    public List<HorarioModulo> getTotalHorasHorario(int idModulo) throws Exception;
}
