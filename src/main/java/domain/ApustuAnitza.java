package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@Entity @XmlAccessorType(XmlAccessType.FIELD)
public class ApustuAnitza implements Serializable {

	@GeneratedValue @Id @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	@XmlElementWrapper
    @XmlElement(name = "erPosiblea")
	private Vector<ErantzunPosiblea> erPosibleak;
	private float kuota;
	private float dirua;
	@XmlIDREF
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

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
