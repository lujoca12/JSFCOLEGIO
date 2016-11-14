/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author server
 */
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertMaterias")
public class SelectConverterMaterias implements Converter{
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else if(isNumeric(value)){
                valor = Integer.parseInt(value);
               for (ClsMaterias item : getSelectItems(uic)) {
                   if(item.getId()== valor)
                        return item;
                } 
            }
            
            return null;
        }
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    protected Collection<ClsMaterias> getSelectItems(UIComponent component) {
        Collection<ClsMaterias> collection = new ArrayList<ClsMaterias>();

        for (UIComponent child : component.getChildren()) {
//            if (child instanceof UISelectItem) {
//                UISelectItem ui = (UISelectItem) child;
//                ClsMaestria item = (ClsMaestria) ui.getValue();
//                collection.add(item);
//            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsMaterias>) value);
            }
        }

        return collection;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ClsMaterias) object).getId());
        }
        else {
            return null;
        }
    } 
}
