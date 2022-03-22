package domain;

import java.util.Date;
import java.util.Vector;

import javax.persistence.*;

@Entity
public abstract class User {

	private String izena;
	private String abizena;
	private Date jaioData;
	@Id
	private String email;
	private String username;
	private String password;
	private float dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mugimendua> mugimenduak;
	
	
	@SuppressWarnings("deprecation")
	public User(String izena, String abizena, String jaioDate, String email, String username, String password) {
		super();
		this.izena = izena;
		this.abizena = abizena;
		String[] data = jaioDate.split("/");
		int urtea = Integer.parseInt(data[0]);
		int hil = Integer.parseInt(data[1]);
		int eg = Integer.parseInt(data[2]);
		Date jaioData =  new Date(urtea-1900,hil-1,eg);
		this.jaioData = jaioData;
		this.email = email;
		this.username = username;
		this.password = password;
		dirua = 0;
		mugimenduak = new Vector<Mugimendua>();
		
	}
	
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public Date getJaioDate() {
		return jaioData;
	}
	public void setJaioDate(Date jaioData) {
		this.jaioData = jaioData;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isCorrectPassword(String password) {
		return this.getPassword().contentEquals(password);
	}
	
	public float getDirua() {
		return dirua;
	}

	public void setDirua(float dirua) {
		this.dirua = dirua;
	}

	public void diruaGehitu(float diru) {
		String des  = diru + " € sartu duzu kontuan";;
		dirua = dirua + diru;
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1, des,this);
		mugimenduak.add(mug);
	}

	public Vector<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(Vector<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}
}
