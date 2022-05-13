package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Mezua implements Serializable{

	@Id @GeneratedValue
	private int id;
	private Elkarrizketa elkarrizketa;
	private User nork;
	private String mezua;
	private boolean reported;
	
	public Mezua(Elkarrizketa elka, User nork, String mezua) {
		elkarrizketa = elka;
		this.nork = nork;
		this.mezua = mezua;
		reported = false;
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

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
