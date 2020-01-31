package si.hse.varnost.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.hse.varnost.ejb.PorociloEjb;
import si.hse.varnost.model.Aktivnost;
import si.hse.varnost.model.Ostalo;
import si.hse.varnost.model.Porocilo;

@ViewScoped
@Named("porociloCtrl")
public class PorociloController  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject PorociloEjb ejb;
	
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

	Ostalo aktivnost = new Ostalo();
	Ostalo mestoDogodka = new Ostalo();
	Date datumOd = null;
	Date datumDo = null;
	String zaznamek = new String();
		
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
	
	public void addAktivnost() {
		selected.getAktivnosti().add(new Aktivnost(aktivnost.getOpis(), mestoDogodka.getOpis(), datumOd, datumDo, zaznamek));
		cancelAktivnost();
	}

	public void cancelAktivnost() {
		 aktivnost = new Ostalo();
		 mestoDogodka = new Ostalo();
		 datumOd = null;
		 datumDo = null;
		 zaznamek = new String();
	}
}
