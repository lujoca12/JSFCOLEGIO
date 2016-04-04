/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojo.DetallePermiso;
import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author server
 */
public interface InterfaceDetallePermiso {
    public String registrar(List<TreeNode> nodes, int idUsuario) throws Exception;
    
    public List<DetallePermiso> getTodosDetPermisos() throws Exception;
    public DetallePermiso getPermiso(String idUsuario) throws Exception;
    public boolean update(DetallePermiso tDetPermiso) throws Exception;
}
