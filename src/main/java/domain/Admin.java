package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

	public Admin(String izena, String abizena, String jaioDate, String email, String username, String password) {
		super(izena, abizena, jaioDate, email, username, password);
		// TODO Auto-generated constructor stub
	}

	
	
}
