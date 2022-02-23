package domain;

import java.util.Date;
import dataAccess.*;
import javax.persistence.Id;

public abstract class User {

	String izena;
	String abizena;
	Date jaioDate;
	@Id
	String email;
	String username;
	String password;
	
	
	
	public User(String izena, String abizena, Date jaioDate, String email, String username, String password) {
		super();
		this.izena = izena;
		this.abizena = abizena;
		this.jaioDate = jaioDate;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public Date getJaioDate() {
		return jaioDate;
	}
	public void setJaioDate(Date jaioDate) {
		this.jaioDate = jaioDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isCorrectPassword(String password) {
		return this.getPassword().contentEquals(password);
	}
	
	
	
	
}
