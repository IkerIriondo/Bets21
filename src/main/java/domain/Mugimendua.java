package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mugimendua implements Serializable{

	private int mugZenb;
	@XmlIDREF
	private User user;
	private String deskribapena;
	
	public Mugimendua() {
		super();
	}
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
