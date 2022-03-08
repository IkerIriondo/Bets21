package domain;

import javax.persistence.Entity;

@Entity
public class Kuota {

	int galderaZenbaki;
	float kuota;
	String erantzunPosiblea;
	
	public Kuota(int galdZenb, float kuo, String emaPosiblea) {
		galderaZenbaki = galdZenb;
		kuota = kuo;
		erantzunPosiblea = emaPosiblea;
	}
	
	public int getGalderaZenbaki() {
		return galderaZenbaki;
	}
	public void setGalderaZenbaki(int galderaZenbaki) {
		this.galderaZenbaki = galderaZenbaki;
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
