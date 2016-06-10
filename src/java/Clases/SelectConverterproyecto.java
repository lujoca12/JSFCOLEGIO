/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@FacesConverter("themeConverterProyecto")
public class SelectConverterproyecto implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else if(isNumeric(value)){
                valor = Integer.parseInt(value);
               for (ClsTablaTesis item : getSelectItems(uic)) {
                   if(item.getIdTesis()== valor)
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
    
    protected Collection<ClsTablaTesis> getSelectItems(UIComponent component) {
        Collection<ClsTablaTesis> collection = new ArrayList<ClsTablaTesis>();

        for (UIComponent child : component.getChildren()) {
//            if (child instanceof UISelectItem) {
//                UISelectItem ui = (UISelectItem) child;
//                ClsProfesor item = (ClsProfesor) ui.getValue();
//                collection.add(item);
//            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsTablaTesis>) value);
            }
        }

        return collection;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ClsTablaTesis) object).getIdTesis());
        }
        else {
            return null;
        }
    }   
} 