package controladores;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

 
@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {
 
    
     
    
     
    public String onFlowProcess(FlowEvent event) {
        
            return event.getNewStep();
        
    }
}