package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ErantzunPosiblea {
	
	@GeneratedValue
	@Id 
	int id;
	Question galdera;
	float kuota;
	String erantzunPosiblea;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	Vector<Apustua> apustuak;
	
	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}

	public ErantzunPosiblea(Question galdera, float kuo, String emaPosiblea) {
		this.galdera = galdera;
		kuota = kuo;
		erantzunPosiblea = emaPosiblea;
		apustuak = new Vector<Apustua>();
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
}
