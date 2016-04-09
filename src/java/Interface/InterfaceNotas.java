/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.Notas;
import java.util.List;

/**
 *
 * @author server
 */
public interface InterfaceNotas {
    
    public boolean registrar(Notas tNotas) throws Exception;
    public List<Notas> getTodasNotas(int idMatricula) throws Exception;
    
    public Notas getNotas(String idNotas) throws Exception;
    public boolean update(Notas tNotas) throws Exception;
    
}
