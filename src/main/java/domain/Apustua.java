package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Apustua {

	private float dirua;
	//@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private User erabiltzailea;
	//@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
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
