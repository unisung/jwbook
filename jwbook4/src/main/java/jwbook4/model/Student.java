package jwbook4.model;

import java.sql.Date;

public class Student {
	 private int id;
	 private String username;
	 private String univ;
	 private Date birth;
	 private String email;
	public Student() {}
	public Student(int id, String username, String univ, Date birth, String email) {
		this.id = id;
		this.username = username;
		this.univ = univ;
		this.birth = birth;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUniv() {
		return univ;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", univ=" 
	            + univ + ", birth=" + birth + ", email=" + email + "]";
	}
}
