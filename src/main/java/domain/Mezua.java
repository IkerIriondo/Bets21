package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@Entity @XmlAccessorType(XmlAccessType.FIELD)
public class Mezua implements Serializable{

	@Id @GeneratedValue  @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	@XmlIDREF
	private Elkarrizketa elkarrizketa;
	@XmlIDREF
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
