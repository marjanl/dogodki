package si.hse.varnost.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="varnostnik")
public class Varnostnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="varnostnikId", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="varnostnik")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String ime;

	public Varnostnik() {}
	
	public Varnostnik(Long id, String ime) {
		super();
		this.id = id;
		this.ime = ime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	
}
