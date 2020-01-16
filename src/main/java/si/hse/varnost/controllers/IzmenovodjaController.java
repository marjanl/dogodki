package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.IzmenovodjaEjb;
import si.hse.varnost.model.Izmenovodja;


@ViewScoped
@Named("izmenovodjaCtrl")
public class IzmenovodjaController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject IzmenovodjaEjb ejb;
	List<Izmenovodja> list;
	Izmenovodja selected;
	
	@PostConstruct
	public void init() {
		readAll();
	}
	
	public void save() {
		try {
			if(ejb.mergeIzmenovodja(selected) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Izmenovodja shranjen", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri shranjevanju", e.getLocalizedMessage()));
			e.printStackTrace();
		}finally {
			cancel();
			readAll();
		}
	}
	
	public void cancel() {
		selected = null;
	}
	
	private void readAll() {
		list = ejb.findAll();
	}

	public void createNewIzmenovodja() {
		selected = new Izmenovodja();
	}
	
	public void uredi(Izmenovodja v) {
		selected=v;
	}
	
	public void delete(Izmenovodja v) {
		try {
			ejb.deleteIzmenovodja(v);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Izmenovodja izbrisan", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri brisanju", e.getLocalizedMessage()));
			e.printStackTrace();
		}finally {
			cancel();
			readAll();
		}
	}
	
	public List<Izmenovodja> getList() {
		return list;
	}

	public Izmenovodja getSelected() {
		return selected;
	}

	public void setSelected(Izmenovodja selected) {
		this.selected = selected;
	}
	
}
