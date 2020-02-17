package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.OstaloEjb;
import si.hse.varnost.ejb.PorociloEjb;
import si.hse.varnost.model.Porocilo;

@ViewScoped
@Named("pregledCtrl")
public class PregledController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	PorociloEjb ejb;
	@Inject
	OstaloEjb ostaloEjb;
	
	private List<Porocilo> porocila;
	private Porocilo selected;

	@PostConstruct
	public void init() {
		porocila = ejb.findAll();
	}
	
	public void pdf(Porocilo porocilo) {
		return;
	}
	
	public List<Porocilo> getPorocila() {
		return porocila;
	}

	public void setPorocila(List<Porocilo> porocila) {
		this.porocila = porocila;
	}
	
	public Porocilo getSelected() {
		return selected;
	}

	public void setSelected(Porocilo selected) {
		this.selected = selected;
	} 
	
	
}
