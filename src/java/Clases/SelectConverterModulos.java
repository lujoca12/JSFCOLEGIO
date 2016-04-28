/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author server
 */
@FacesConverter("themeConverterModulos")
public class SelectConverterModulos implements Converter {
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else if(isNumeric(value)){
                valor = Integer.parseInt(value);
               for (ClsTablaModulosRegistrados item : getSelectItems(uic)) {
                   if(item.getIdModulo() == valor)
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
    
    protected Collection<ClsTablaModulosRegistrados> getSelectItems(UIComponent component) {
        Collection<ClsTablaModulosRegistrados> collection = new ArrayList<ClsTablaModulosRegistrados>();

        for (UIComponent child : component.getChildren()) {
//            if (child instanceof UISelectItem) {
//                UISelectItem ui = (UISelectItem) child;
//                ClsMaestria item = (ClsMaestria) ui.getValue();
//                collection.add(item);
//            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsTablaModulosRegistrados>) value);
            }
        }

        return collection;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ClsTablaModulosRegistrados) object).getIdModulo());
        }
        else {
            return null;
        }
    } 
    
}
