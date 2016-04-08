/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Matricula;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceMatricula {
    
    public boolean registrar(Matricula tMatricula) throws Exception;
    public List<Matricula> getTodasMatriculas() throws Exception;
    public List<Matricula> getMatriculaMaestria(String cedula) throws Exception;
    public Matricula getMatriculas(String idMatricula) throws Exception;
    public boolean update(Matricula tMatricula) throws Exception;
    
}
