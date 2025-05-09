package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data // @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter 
public class MyDTO {
	private String id;
	private String name;
	private String password;
	private int age;
	private String address;
	private String tel;
	
//	public MyDTO() {}
//	public MyDTO(String id, String name, String password, int age, String address, String tel) {
//		this.id = id;
//		this.name = name;
//		this.password = password;
//		this.age = age;
//		this.address = address;
//		this.tel = tel;
//	}
//	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getTel() {
//		return tel;
//	}
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//	@Override
//	public String toString() {
//		return "MyDTO [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", address=" + address
//				+ ", tel=" + tel + "]";
//	}
	
	
	
	
	

	
}
