package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@Entity @XmlAccessorType(XmlAccessType.FIELD)
public class Elkarrizketa implements Serializable{
	
	@Id @GeneratedValue @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	@XmlIDREF
	private User user1;
	@XmlIDREF
	private User user2;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Mezua> mezuak;
	
	public Elkarrizketa(User user1, User user2) {
		this.user1 = user1;
		this.user2 = user2;
		mezuak = new Vector<Mezua>();
	}
	
	public Elkarrizketa() {}
	
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Vector<Mezua> getMezuak() {
		return mezuak;
	}
	public void setMezuak(Vector<Mezua> mezuak) {
		this.mezuak = mezuak;
	}

	public void gehituMezua(Mezua m) {
		mezuak.add(m);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
