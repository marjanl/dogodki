package si.hse.varnost.converters;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import si.hse.varnost.controllers.PorociloController;
import si.hse.varnost.ejb.OstaloEjb;
import si.hse.varnost.model.Ostalo;

@FacesConverter(value = "OstaloConverter")
public class OstaloConverter implements Converter , Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	OstaloEjb ejb;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
	        return null;
	    }
	    try {
	    	PorociloController ctrl = (PorociloController) context.getApplication().evaluateExpressionGet(context, "#{porociloCtrl}", PorociloController.class);
	    	for(Ostalo o : ctrl.getVsiOstalo()) {
	    		if(o.getOpis().equals(submittedValue)) {
	    			return o;
	    		}
	    	}
	    	
	    } catch (Exception e) {
	        throw new ConverterException(new FacesMessage(submittedValue + " neveljavna vrednost za ostale podatke"), e);
	    }
	    return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	    if (modelValue == null) {
	        return "";
	    }
	    return modelValue.toString();
	}

}
