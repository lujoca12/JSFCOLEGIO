/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Estudiante;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceEstudiante {
    public boolean registrar(Estudiante tEstudiante) throws Exception;
    public boolean usuarioExisre(String cedula) throws Exception;
    public List<Estudiante> getTblEstudiante(int idModulo) throws Exception;
    public List<Estudiante> getEstudiantes() throws Exception;
    public Estudiante getEstudiante(String idUsuario) throws Exception;
    public boolean update(Estudiante tEstudiante) throws Exception;
    public List<Estudiante> getEstudiantesMaestria(int idmaestria) throws Exception;
    public List<Estudiante> getEstudiantesMaestriaTitulacion(int idmaestria) throws Exception;
}
