package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

import configuration.UtilDate;

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
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Jarraipena> jarraitzaileak;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Jarraipena> jarraituak;
	
	private boolean baneatua;
	private Date zenbatDenboraBan;
	private int zenbatApostu;
	private float zenbatDiruIrabazi;
	private int zenbatAposIrabazi;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Elkarrizketa> elkarrizketak;
	
	public User() {
		super();
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
		jarraitzaileak = new Vector<Jarraipena>();
		jarraituak = new Vector<Jarraipena>();
		baneatua = false;
		
		String gaur = LocalDate.now().toString();
		String[] gaurkoa = gaur.split("-");
		int u = Integer.parseInt(gaurkoa[0]);
		int h = Integer.parseInt(gaurkoa[1]) -1;
		int e = Integer.parseInt(gaurkoa[2]);
		zenbatDenboraBan = UtilDate.newDate(u,h,e);
		
		zenbatApostu = 0;
		zenbatDiruIrabazi = 0;
		zenbatAposIrabazi = 0;
		
		elkarrizketak = new Vector<Elkarrizketa>();
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

	public void apustuaGehitu(Apustua apostu) {
			dirua = dirua - apostu.getDirua();
			Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apostu.getDirua() + "€ gastatu duzu apostatzen",this);
			mugimenduak.add(mug);
			apustuak.add(apostu);
			zenbatApostu++;
			zenbatDiruIrabazi = zenbatDiruIrabazi - apostu.getDirua();
	}

	public void diruaItzuli(Apustua apustu) {
		dirua = dirua + apustu.getDirua();
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apustu.getDirua() + "€ itzuli zaizkizu",this);
		mugimenduak.add(mug);
		apustuak.remove(apustu);
		zenbatApostu--;
		zenbatDiruIrabazi = zenbatDiruIrabazi + apustu.getDirua();
	}

	public void apustuaIrabazi(Apustua a) {
		float zenbatIrabazi = (a.getDirua()*a.getEmaitzaPosiblea().getKuota());
		dirua = dirua + zenbatIrabazi;
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,zenbatIrabazi + " € irabazi dituzu apustuetatik",this);
		mugimenduak.add(mug);
		apustuak.remove(a);
		zenbatDiruIrabazi = zenbatDiruIrabazi + zenbatIrabazi;
		zenbatAposIrabazi++;
	}

	public void apustuaEguneratu(float apostu) {
		dirua = dirua - apostu;
		Mugimendua mug = new Mugimendua(mugimenduak.size()+1,apostu + " € gehitu diozu apustuan",this);
		mugimenduak.add(mug);
		zenbatDiruIrabazi = zenbatDiruIrabazi - apostu;
	}
	
	public Vector<Jarraipena> getJarraitzaileak() {
		return jarraitzaileak;
	}

	public void setJarraitzaileak(Vector<Jarraipena> jarraitzaileak) {
		this.jarraitzaileak = jarraitzaileak;
	}

	public Vector<Jarraipena> getJarraituak() {
		return jarraituak;
	}

	public void setJarraituak(Vector<Jarraipena> jarraituak) {
		this.jarraituak = jarraituak;
	}

	public boolean isBaneatua() {
		return baneatua;
	}

	public void setBaneatua(boolean baneatua) {
		this.baneatua = baneatua;
	}

	public Date getZenbatDenboraBan() {
		return zenbatDenboraBan;
	}

	public void setZenbatDenboraBan(Date zenbatDenboraBan) {
		this.zenbatDenboraBan = zenbatDenboraBan;
	}

	public Integer getZenbatApostu() {
		return zenbatApostu;
	}

	public void setZenbatApostu(int zenbatApostu) {
		this.zenbatApostu = zenbatApostu;
	}

	public Float getZenbatDiruIrabazi() {
		return zenbatDiruIrabazi;
	}

	public void setZenbatDiruIrabazi(float zenbatDiruIrabazi) {
		this.zenbatDiruIrabazi = zenbatDiruIrabazi;
	}

	public Integer getZenbatAposIrabazi() {
		return zenbatAposIrabazi;
	}

	public void setZenbatAposIrabazi(int zenbatAposIrabazi) {
		this.zenbatAposIrabazi = zenbatAposIrabazi;
	}

	public void gehituJarraitua(Jarraipena jarrai) {
		jarraituak.add(jarrai);
		dirua = dirua - jarrai.getZenbatDiru();
	}

	public void gehituJarraitzailea(Jarraipena jarrai) {
		jarraitzaileak.add(jarrai);	
	}

	public void ezabatuJarraitzailea(Jarraipena ja) {
		jarraitzaileak.remove(ja);
	}

	public void ezabatuJarraituak(Jarraipena ja) {
		jarraituak.remove(ja);
	}

	public Vector<Elkarrizketa> getElkarrizketak() {
		return elkarrizketak;
	}

	public void setElkarrizketak(Vector<Elkarrizketa> elkarrizketak) {
		this.elkarrizketak = elkarrizketak;
	}
	
	public void addElkarrizketa(Elkarrizketa elkar) {
		elkarrizketak.add(elkar);
		
	}

	public void elkarrizketaEzabatu(Elkarrizketa e) {
		elkarrizketak.remove(e);
		
	}
	
}
