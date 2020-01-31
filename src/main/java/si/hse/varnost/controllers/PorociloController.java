package si.hse.varnost.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.PorociloEjb;
import si.hse.varnost.model.Porocilo;

@ViewScoped
@Named("porociloCtrl")
public class PorociloController  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject PorociloEjb ejb;
		
	Porocilo selected = new Porocilo();

	public void create() {
		
		try {
			ejb.create(selected);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poročilo shranjeno", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri shranjevanju", e.getLocalizedMessage()));
			e.printStackTrace();
		}finally {
			selected = new Porocilo();
		}
	}

	public Porocilo getSelected() {
		return selected;
	}

	public void setSelected(Porocilo selected) {
		this.selected = selected;
	}	
	
}
