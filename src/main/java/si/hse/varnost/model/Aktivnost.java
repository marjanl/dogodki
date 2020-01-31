package si.hse.varnost.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "aktivnost")
@XmlRootElement
public class Aktivnost implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite")
	@TableGenerator(name = "aktivnostId", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "aktivnost")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_porocilo", nullable = false)
	private Porocilo porocilo;

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Porocilo getPorocilo() {
		return porocilo;
	}
	public void setPorocilo(Porocilo porocilo) {
		this.porocilo = porocilo;
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
