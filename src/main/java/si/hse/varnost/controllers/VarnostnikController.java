package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.VarnostnikEjb;
import si.hse.varnost.model.Varnostnik;

@ViewScoped
@Named("varCtrl")
public class VarnostnikController implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject VarnostnikEjb ejb;
	List<Varnostnik> list;
	Varnostnik selected;
	
	@PostConstruct
	public void init() {
		readAll();
	}
	
	public void save() {
		try {
			if(ejb.mergeVarnostnik(selected) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Varnostnik shranjen", ""));
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

	public void createNewVarnostnik() {
		selected = new Varnostnik();
	}
	
	public void uredi(Varnostnik v) {
		selected=v;
	}
	
	public List<Varnostnik> getList() {
		return list;
	}

	public Varnostnik getSelected() {
		return selected;
	}

	public void setSelected(Varnostnik selected) {
		this.selected = selected;
	}
	
	
	
}
