package jwbook2.model;

import java.io.Serializable;

//자바 Bean
public class Member implements Serializable{
	// 속성
	private int id;
	private String name;
	private String email;

	// getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
