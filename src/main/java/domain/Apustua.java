package domain;

import javax.persistence.*;

@Entity
public class Apustua {
	
	@Id
	@GeneratedValue
	private int id;
	private float dirua;
	private User erabiltzailea;
	private ErantzunPosiblea emaitzaPosiblea;
	
	public Apustua(float dirua, User erabil, ErantzunPosiblea ema) {
		this.dirua = dirua;
		this.erabiltzailea = erabil;
		this.emaitzaPosiblea = ema;
	}
	
	public float getDirua() {
		return dirua;
	}
	public void setDirua(float dirua) {
		this.dirua = dirua;
	}
	public User getErabiltzailea() {
		return erabiltzailea;
	}
	public void setErabiltzailea(User erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}
	public ErantzunPosiblea getEmaitzaPosiblea() {
		return emaitzaPosiblea;
	}
	public void setEmaitzaPosiblea(ErantzunPosiblea emaitzaPosiblea) {
		this.emaitzaPosiblea = emaitzaPosiblea;
	}
	
}
