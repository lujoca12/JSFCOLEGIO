/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.ClsNotas;
import Pojo.Notas;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceNotas {
    
    public boolean registrar(List<ClsNotas> lstNotas, int idModulo, Character accion, String docente) throws Exception;
    public List<Notas> getTodasNotas(int idMatricula) throws Exception;
    public List<Notas> existe(int idModulo, String estado) throws Exception;
    public Notas getNotas(String idNotas) throws Exception;
    public boolean update(List<ClsNotas> lstTblNotas, int idModulo) throws Exception;
    
}
