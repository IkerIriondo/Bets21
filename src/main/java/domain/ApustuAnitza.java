package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class ApustuAnitza implements Serializable {

	@GeneratedValue
	@Id
	private int id;
	private Vector<ErantzunPosiblea> erPosibleak;
	private float kuota;
	private float dirua;
	private User user;
	
	
	public ApustuAnitza() {
		super();
	}

	public ApustuAnitza(float kuota, float dirua, User user) {
		super();
		this.erPosibleak = new Vector<ErantzunPosiblea>();
		this.kuota = kuota;
		this.dirua = dirua;
		this.user = user;
	}
	
	public ApustuAnitza(Vector<ErantzunPosiblea> emak, float kuota, float dirua, User user) {
		super();
		this.erPosibleak = emak;
		this.kuota = kuota;
		this.dirua = dirua;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Vector<ErantzunPosiblea> getErPosibleak() {
		return erPosibleak;
	}
	
	public void setErPosibleak(Vector<ErantzunPosiblea> erPosibleak) {
		this.erPosibleak = erPosibleak;
	}
	
	public float getKuota() {
		return kuota;
	}
	
	public void setKuota(float kuota) {
		this.kuota = kuota;
	}
	
	public float getDirua() {
		return dirua;
	}
	
	public void setDirua(float dirua) {
		this.dirua = dirua;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addErantzunPosiblea(ErantzunPosiblea e) {
		erPosibleak.add(e);
	}
	
}
