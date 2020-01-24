package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.OstaloEjb;
import si.hse.varnost.model.Ostalo;
import si.hse.varnost.model.Vrsta;
	
@ViewScoped
@Named("ostaloCtrl")
public class OstaloController  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject OstaloEjb ejb;
	List<Ostalo> list;
	Ostalo selected;
	
	@PostConstruct
	public void init() {
		readAll();
	}
	
	public void save() {
		try {
			if(ejb.mergeOstalo(selected) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ostalo shranjen", ""));
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

	public void createNewOstalo() {
		selected = new Ostalo();
	}
	
	public void uredi(Ostalo v) {
		selected=v;
	}
	
	public void delete(Ostalo v) {
		try {
			ejb.deleteOstalo(v);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ostalo izbrisan", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri brisanju", e.getLocalizedMessage()));
			e.printStackTrace();
		}finally {
			cancel();
			readAll();
		}
	}
	
	public List<Ostalo> getList() {
		return list;
	}

	public Ostalo getSelected() {
		return selected;
	}

	public void setSelected(Ostalo selected) {
		this.selected = selected;
	}

	public si.hse.varnost.model.Vrsta[] getVrste() {
        return si.hse.varnost.model.Vrsta.values();
    }
	
	public List<Ostalo> getMesto(){
		return list.stream().filter(o -> o.getVrsta().equals(Vrsta.MESTO)).collect(Collectors.toList());
	}
	
	public List<Ostalo> getIzmena(){
		return list.stream().filter(o -> o.getVrsta().equals(Vrsta.IZMENA)).collect(Collectors.toList());
	}
	
	public List<Ostalo> getAktivnost(){
		return list.stream().filter(o -> o.getVrsta().equals(Vrsta.AKTIVNOST)).collect(Collectors.toList());
	}
	
	public List<Ostalo> getVm(){
		return list.stream().filter(o -> o.getVrsta().equals(Vrsta.VM)).collect(Collectors.toList());
	}
	
	
	
}
