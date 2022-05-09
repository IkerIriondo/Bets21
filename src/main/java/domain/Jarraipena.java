package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity 
public class Jarraipena implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private User zeinek;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
