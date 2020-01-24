package si.hse.varnost.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="ostalo")
public class Ostalo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="ostaloId", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="ostalo")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String opis;
	
	@Column(name = "vrsta")
    @Enumerated(EnumType.STRING)
	private Vrsta vrsta;

	public Ostalo() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Vrsta getVrsta() {
		return vrsta;
	}

	public void setVrsta(Vrsta vrsta) {
		this.vrsta = vrsta;
	}
}
