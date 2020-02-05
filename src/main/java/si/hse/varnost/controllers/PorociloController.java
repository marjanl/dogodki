package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import si.hse.varnost.ejb.OstaloEjb;
import si.hse.varnost.ejb.PorociloEjb;
import si.hse.varnost.model.Aktivnost;
import si.hse.varnost.model.Ostalo;
import si.hse.varnost.model.Porocilo;
import si.hse.varnost.model.Vrsta;

@ViewScoped
@Named("porociloCtrl")
public class PorociloController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	PorociloEjb ejb;
	@Inject
	OstaloEjb ostaloEjb;

	public List<Ostalo> getAktivnosti() {
		return aktivnosti;
	}

	public List<Ostalo> getMesta() {
		return mesta;
	}

	public List<Ostalo> getIzmene() {
		return izmene;
	}

	public List<Ostalo> getVms() {
		return vms;
	}
	
	public List<Ostalo> getVsiOstalo() {
		return vsiOstalo;
	}

	List<Ostalo> aktivnosti, mesta, izmene, vms, vsiOstalo;

	@PostConstruct
	public void init() {
		try {
			vsiOstalo = ostaloEjb.findAll();
			aktivnosti = ostaloEjb.findByVrsta(Vrsta.AKTIVNOST);
			mesta = ostaloEjb.findByVrsta(Vrsta.MESTO);
			izmene = ostaloEjb.findByVrsta(Vrsta.IZMENA);
			vms = ostaloEjb.findByVrsta(Vrsta.VM);
		} catch (Exception e) {
			System.out.println("mam exception pri init:" + e);
			e.printStackTrace();
		}
	}

	public Ostalo getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(Ostalo aktivnost) {
		this.aktivnost = aktivnost;
	}

	public Ostalo getMestoDogodka() {
		return mestoDogodka;
	}

	public void setMestoDogodka(Ostalo mestoDogodka) {
		this.mestoDogodka = mestoDogodka;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public String getZaznamek() {
		return zaznamek;
	}

	public void setZaznamek(String zaznamek) {
		this.zaznamek = zaznamek;
	}

	Ostalo aktivnost = null;
	Ostalo mestoDogodka = null;
	Date datumOd = null;
	Date datumDo = null;
	String zaznamek = new String();

	Porocilo selected = new Porocilo();

	public void create() {

		try {
			ejb.create(selected);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Poročilo shranjeno", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri shranjevanju", e.getLocalizedMessage()));
			e.printStackTrace();
		} finally {
			selected = new Porocilo();
		}
	}

	public Porocilo getSelected() {
		return selected;
	}

	public void setSelected(Porocilo selected) {
		this.selected = selected;
	}

	public void addAktivnost() {
		try {
			selected.getAktivnosti()
					.add(new Aktivnost(aktivnost.getOpis(), mestoDogodka.getOpis(), datumOd, datumDo, zaznamek));
			cancelAktivnost();
			RequestContext.getCurrentInstance().execute("PF('dialogAktivnost').hide()");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri shranjevanju", e.getLocalizedMessage()));
			e.printStackTrace();
		}
	}
	
	public void removeAktivnost(Aktivnost akt) {
		selected.getAktivnosti().remove(akt);
	}

	public void cancelAktivnost() {
		aktivnost = null;
		mestoDogodka = null;
		datumOd = null;
		datumDo = null;
		zaznamek = new String();
	}
}
