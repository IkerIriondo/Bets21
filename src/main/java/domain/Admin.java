package domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Admin extends User implements Serializable{

	public Admin(String izena, String abizena, String jaioDate, String email, String username, String password) {
		super(izena, abizena, jaioDate, email, username, password);
		// TODO Auto-generated constructor stub
	}

	public Admin() {
		super();
	}
	
	
}
