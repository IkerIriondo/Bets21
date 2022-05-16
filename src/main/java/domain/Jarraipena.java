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
@Entity  @XmlAccessorType(XmlAccessType.FIELD)
public class Jarraipena implements Serializable{
	
	@Id @GeneratedValue @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	@XmlIDREF
	private User zeinek;
	@XmlIDREF
	private User zeineri;
	private float zenbatDiru;
	
	public Jarraipena(User zeinek, User zeineri, float zenbatDiru) {
		this.zeinek = zeinek;
		this.zeineri = zeineri;
		this.zenbatDiru = zenbatDiru;
	}
	
	public Jarraipena() {
		super();
	}
	
	public User getZeinek() {
		return zeinek;
	}
	public void setZeinek(User zeinek) {
		this.zeinek = zeinek;
	}
	public User getZeineri() {
		return zeineri;
	}
	public void setZeineri(User zeineri) {
		this.zeineri = zeineri;
	}
	public float getZenbatDiru() {
		return zenbatDiru;
	}
	public void setZenbatDiru(float zenbatDiru) {
		this.zenbatDiru = zenbatDiru;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
