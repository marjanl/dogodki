package si.hse.varnost.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
		

@Embeddable
public class Aktivnost implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "aktivnost")
	private String aktivnost;
	@Column(name = "mesto_dogodka")
	private String mestoDogodka;
	@Column(name = "zaznamek")
	private String zaznamek;
	@Column(name = "datum_od")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumOd;
	@Column(name = "datum_do")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumDo;

	public Aktivnost() {}
	
	public Aktivnost(String string, String string2, Date datumOd2, Date datumDo2, String zaznamek2) {
		this.aktivnost=string;
		this.mestoDogodka=string2;
		this.datumOd=datumOd2;
		this.datumDo=datumDo2;
		this.zaznamek=zaznamek2;
	}
	public String getAktivnost() {
		return aktivnost;
	}
	public void setAktivnost(String aktivnost) {
		this.aktivnost = aktivnost;
	}
	public String getMestoDogodka() {
		return mestoDogodka;
	}
	public void setMestoDogodka(String mestoDogodka) {
		this.mestoDogodka = mestoDogodka;
	}
	public String getZaznamek() {
		return zaznamek;
	}
	public void setZaznamek(String zaznamek) {
		this.zaznamek = zaznamek;
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
}
