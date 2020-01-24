package si.hse.varnost.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="porocilo")
public class Porocilo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="porociloId", table="sqlite_sequence",
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
	
	
	@OneToMany(mappedBy = "porocilo", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Aktivnost> aktivnosti = new HashSet<>();
	
}
