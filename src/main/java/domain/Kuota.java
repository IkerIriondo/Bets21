package domain;

import javax.persistence.Entity;

@Entity
public class Kuota {

	Question galdera;
	float kuota;
	String erantzunPosiblea;
	
	public Kuota(Question galdera, float kuo, String emaPosiblea) {
		this.galdera = galdera;
		kuota = kuo;
		erantzunPosiblea = emaPosiblea;
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
