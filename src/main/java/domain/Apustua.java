package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Apustua implements Serializable{

	@Id
	@GeneratedValue
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	private float dirua;
	@XmlIDREF
	private User erabiltzailea;
	@XmlIDREF
	private ErantzunPosiblea emaitzaPosiblea;
	
	public Apustua() {
		super();
	}
	
	public Apustua(float dirua, User erabil, ErantzunPosiblea ema) {
		this.dirua = dirua;
		this.erabiltzailea = erabil;
		this.emaitzaPosiblea = ema;
	}
	
	public float getDirua() {
		return dirua;
	}
	public void setDirua(float dirua) {
		this.dirua = dirua;
	}
	public User getErabiltzailea() {
		return erabiltzailea;
	}
	public void setErabiltzailea(User erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}
	public ErantzunPosiblea getEmaitzaPosiblea() {
		return emaitzaPosiblea;
	}
	public void setEmaitzaPosiblea(ErantzunPosiblea emaitzaPosiblea) {
		this.emaitzaPosiblea = emaitzaPosiblea;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
