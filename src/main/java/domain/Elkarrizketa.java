package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Elkarrizketa implements Serializable{
	
	@Id @GeneratedValue
	private int id;
	private User user1;
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
	
}
