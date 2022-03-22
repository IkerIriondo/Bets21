package domain;


import javax.persistence.Entity;

@Entity
public class Erabiltzailea extends User{

	public Erabiltzailea(String izena, String abizena, String jaioDate, String email, String username, String password) {
		super(izena, abizena, jaioDate, email, username, password);
		// TODO Auto-generated constructor stub
	}

	
	
}
