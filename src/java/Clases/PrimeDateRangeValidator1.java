/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author server
 */
@FacesValidator("primeDateRangeValidator1")
public class PrimeDateRangeValidator1 implements Validator{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
         
        Object startDateValue = component.getAttributes().get("txtFechaInicio");
        Object endDateValue = component.getAttributes().get("txtFechaFin");
        Date startDate = null;
        Date endDate = null;
        //System.out.println("Fecha Obtenida: "+startDateValue);
        if (startDateValue==null && endDateValue == null) {
            return;
        }else if(startDateValue==null && endDateValue != null){
            
            startDate = (Date)value;
            endDate = (Date)endDateValue;
        }else if(startDateValue!=null && endDateValue == null){
            
            startDate = (Date)startDateValue;
            endDate = (Date)value;
        }
        
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int anioInicio = calendar.get(Calendar.YEAR);
        
        calendar.setTime(endDate);
        int anioFin = calendar.get(Calendar.YEAR);
        
        if (endDate.before(startDate) || endDate.equals(startDate)) {
             FacesMessage message = new FacesMessage("La fecha Final no puede ser igual o menor que la inicial.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
