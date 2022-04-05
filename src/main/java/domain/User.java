package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@XmlSeeAlso ({Erabiltzailea.class, Admin.class})
public abstract class User implements Serializable{

	private String izena;
	private String abizena;
	private Date jaioData;
	@XmlID
	@Id
	private String email;
	private String username;
	private String password;
	private float dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Mugimendua> mugimenduak;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak;
	
	public User() {
		super();
	}
	
	public Date getJaioData() {
		return jaioData;
	}

	public void setJaioData(Date jaioData) {
		this.jaioData = jaioData;
	}

	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}

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
		apustuak = new Vector<Apustua>();
		
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
		String des  = diru + " � sartu duzu kontuan";;
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

	public void apustuaGehitu(Apustua apostu) {
			dirua = dirua - apostu.getDirua();
			Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apostu.getDirua() + "� gastatu duzu apostatzen",this);
			mugimenduak.add(mug);
			apustuak.add(apostu);
		
	}

	public void diruaItzuli(Apustua apustu) {
		dirua = dirua + apustu.getDirua();
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apustu.getDirua() + "� itzuli zaizkizu",this);
		mugimenduak.add(mug);
		apustuak.remove(apustu);
		
	}

	public void apustuaIrabazi(Apustua a) {
		float zenbatIrabazi = (a.getDirua()*a.getEmaitzaPosiblea().getKuota());
		dirua = dirua + zenbatIrabazi;
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,zenbatIrabazi + " � irabazi dituzu apustuetatik",this);
		mugimenduak.add(mug);
		apustuak.remove(a);
	}

	public void apustuaEguneratu(float apostu) {
		dirua = dirua - apostu;
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apostu + " � gehitu diozu apustuan",this);
		mugimenduak.add(mug);
	}
}
