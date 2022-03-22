package domain;

import javax.persistence.*;

@Entity
public class Mugimendua {

	private int mugZenb;
	private User user;
	private String deskribapena;
	
	
	public Mugimendua(int zenb, String deskribapena, User user) {
		mugZenb = zenb;
		this.deskribapena = deskribapena;
		this.user = user;
	}

	public int getMugZenb() {
		return mugZenb;
	}

	public void setMugZenb(int mugZenb) {
		this.mugZenb = mugZenb;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
