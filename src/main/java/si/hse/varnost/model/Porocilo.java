package si.hse.varnost.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="porocilo")
public class Porocilo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite1")
	@TableGenerator(name="sqlite1", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="porocilo")
	private Long id;
	
	@Column(name = "datum")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;
	@Column(name = "izmena")
	private String izmena;
	@Column(name = "varnostno_mesto")
	private String varnostnoMesto;
	@Column(name = "varnostnik")
	private String varnostnik;
	@Column(name = "izmenovodja")
	private String izmenovodja;
	
	@Column(name = "save_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date saveTime;
	
	public Porocilo() {}

    @ElementCollection(targetClass=Aktivnost.class)
    @CollectionTable(
          name="aktivnost",
          joinColumns=@JoinColumn(name="id_porocilo")
    )
    private Set<Aktivnost> aktivnosti = new HashSet<>();  
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}


	public String getIzmena() {
		return izmena;
	}


	public void setIzmena(String izmena) {
		this.izmena = izmena;
	}


	public String getVarnostnoMesto() {
		return varnostnoMesto;
	}


	public void setVarnostnoMesto(String varnostnoMesto) {
		this.varnostnoMesto = varnostnoMesto;
	}


	public String getVarnostnik() {
		return varnostnik;
	}


	public void setVarnostnik(String varnostnik) {
		this.varnostnik = varnostnik;
	}


	public String getIzmenovodja() {
		return izmenovodja;
	}


	public void setIzmenovodja(String izmenovodja) {
		this.izmenovodja = izmenovodja;
	}


	public Date getSaveTime() {
		return saveTime;
	}


	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}


	public Set<Aktivnost> getAktivnosti() {
		return aktivnosti;
	}


	public void setAktivnosti(Set<Aktivnost> aktivnosti) {
		this.aktivnosti = aktivnosti;
	}
}
