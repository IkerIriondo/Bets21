package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Mezua implements Serializable{

	private Elkarrizketa elkarrizketa;
	private User nork;
	private String mezua;
	
	public Mezua(Elkarrizketa elka, User nork, String mezua) {
		elkarrizketa = elka;
		this.nork = nork;
		this.mezua = mezua;
	}
	
	public Mezua() {}
	
	public Elkarrizketa getElkarrizketa() {
		return elkarrizketa;
	}
	public void setElkarrizketa(Elkarrizketa elkarrizketa) {
		this.elkarrizketa = elkarrizketa;
	}
	public User getNork() {
		return nork;
	}
	public void setNork(User nork) {
		this.nork = nork;
	}
	public String getMezua() {
		return mezua;
	}
	public void setMezua(String mezua) {
		this.mezua = mezua;
	}
	
	
}
