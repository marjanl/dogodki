package si.hse.varnost.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
	@Column(name = "od_ur")
	private int odUr;
	@Column(name = "od_min")
	private int odMin;
	@Column(name = "doUr")
	private int doUr;
	@Column(name = "doMin")
	private int doMin;

}
