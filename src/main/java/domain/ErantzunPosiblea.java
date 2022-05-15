package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErantzunPosiblea implements Serializable{
	
	@GeneratedValue 
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	@XmlIDREF
	private Question galdera;
	private float kuota;
	private String erantzunPosiblea;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<ApustuAnitza> apustuAnitzak;
	private boolean emaitzaDu;
	private boolean emaitzaZuzenaDa;
	
	
	public ErantzunPosiblea() {
		super();
	}
	
	public ErantzunPosiblea(Question galdera, float kuo, String emaPosiblea) {
		this.galdera = galdera;
		kuota = kuo;
		erantzunPosiblea = emaPosiblea;
		apustuak = new Vector<Apustua>();
		apustuAnitzak = new Vector<ApustuAnitza>();
		emaitzaDu = false;
		emaitzaZuzenaDa = false;
	}
	
	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	public Question getGaldera() {
		return galdera;
	}
	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}
	public float getKuota() {
		return kuota;
	}
	public void setKuota(float kuota) {
		this.kuota = kuota;
	}
	public String getErantzunPosiblea() {
		return erantzunPosiblea;
	}
	public void setErantzunPosiblea(String erantzunPosiblea) {
		this.erantzunPosiblea = erantzunPosiblea;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vector<ApustuAnitza> getApustuAnitzak() {
		return apustuAnitzak;
	}

	public void setApustuAnitzak(Vector<ApustuAnitza> apustuAnitzak) {
		this.apustuAnitzak = apustuAnitzak;
	}
	
	public void addApustuAnitza(ApustuAnitza a) {
		apustuAnitzak.add(a);
	}

	public boolean isEmaitzaDu() {
		return emaitzaDu;
	}

	public void setEmaitzaDu(boolean emaitzaDu) {
		this.emaitzaDu = emaitzaDu;
	}

	public boolean isEmaitzaZuzenaDa() {
		return emaitzaZuzenaDa;
	}

	public void setEmaitzaZuzenaDa(boolean emaitzaZuzenaDa) {
		this.emaitzaZuzenaDa = emaitzaZuzenaDa;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
