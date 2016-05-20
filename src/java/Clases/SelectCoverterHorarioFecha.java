/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
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
@FacesConverter("themeConverterHorarioModulo")
public class SelectCoverterHorarioFecha implements Converter {
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        int valor = 0;
        if (value == null) {
            return null;
        } else {
            if(value.equals("-1")){
                
            }else if(isNumeric(value)){
                valor = Integer.parseInt(value);
               for (ClsFechaHoras item : getSelectItems(uic)) {
                   if(item.getIdHorario() == valor)
                        return item;
                } 
            }
            
            return null;
        }
    }
    
    private static boolean isNumeric(String cadena){
	try {
                Double.parseDouble(cadena);
		//Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    protected Collection<ClsFechaHoras> getSelectItems(UIComponent component) {
        Collection<ClsFechaHoras> collection = new ArrayList<ClsFechaHoras>();

        for (UIComponent child : component.getChildren()) {
//            if (child instanceof UISelectItem) {
//                UISelectItem ui = (UISelectItem) child;
//                ClsMaestria item = (ClsMaestria) ui.getValue();
//                collection.add(item);
//            } else 
                if (child instanceof UISelectItems) {
                UISelectItems ui = (UISelectItems) child;
                Object value = ui.getValue();
                collection.addAll((Collection<ClsFechaHoras>) value);
            }
        }

        return collection;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            if(object != "")
                return String.valueOf(((ClsFechaHoras) object).getIdHorario());
            else
                return null;
        }
        else {
            return null;
        }
    } 
    
}