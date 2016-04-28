/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Pojo.Tesis;
import Pojo.TipoTribunal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author SERVER
 */
@Named(value = "mbVTesi")
@ViewScoped
public class MbVTesi {
    
    private Tesis tTesis;
    private String estudiante;
    private ArrayList<TipoTribunal> lstTheme;
    private List<SelectItem> lstTodoMaestria;
    private List<SelectItem> lstTodoEstudiante;
    /**
     * Creates a new instance of MbVTesi
     */
    public MbVTesi() {
    }

    public List<SelectItem> getLstTodoMaestria() {
        return lstTodoMaestria;
    }

    public List<SelectItem> getLstTodoEstudiante() {
        return lstTodoEstudiante;
    }
    
    
    
    
}
