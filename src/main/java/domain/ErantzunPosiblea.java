package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class ErantzunPosiblea {

	Question galdera;
	float kuota;
	String erantzunPosiblea;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
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
	
}
