/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.util.ArrayList;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author SERVER
 */
@FacesConverter("themeConverterEstudiante")
public class SelectConverterEstudiante implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else if(isNumeric(value)){
                valor = Integer.parseInt(value);
               for (ClsEstudiante item : getSelectItems(component)) {
                   if(item.getId() == valor)
                        return item;
                } 
            }
            
            return null;
        }
    }
    protected Collection<ClsEstudiante> getSelectItems(UIComponent component) {
        Collection<ClsEstudiante> collection = new ArrayList<ClsEstudiante>();

        for (UIComponent child : component.getChildren()) {
//            if (child instanceof UISelectItem) {
//                UISelectItem ui = (UISelectItem) child;
//                ClsMaestria item = (ClsMaestria) ui.getValue();
//                collection.add(item);
//            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsEstudiante>) value);
            }
        }

        return collection;
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((ClsEstudiante) value).getId());
        }
        else {
            return null;
        }
    }
    
}
